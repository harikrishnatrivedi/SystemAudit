/**
 * 
 */
package org.systemaudit.runmain;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributes;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.filechooser.FileSystemView;

import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.systemaudit.model.DeviceInfo;
import org.systemaudit.model.DriveInfo;
import org.systemaudit.model.FileDetails;
import org.systemaudit.model.EnumFileFolderOperationStatus;
import org.systemaudit.model.EnumKeyValue;
import org.systemaudit.model.FolderOperationRequest;
import org.systemaudit.model.KeyValue;
import org.systemaudit.model.ScheduleMaster;
import org.systemaudit.model.EnumScheduleStatus;
import org.systemaudit.service.DeviceInfoService;
import org.systemaudit.service.FileDetailsService;
import org.systemaudit.service.FolderOperationRequestService;
import org.systemaudit.service.KeyValueService;
import org.systemaudit.service.ScheduleMasterService;

/**
 * @author Administrator
 *
 */
public class RunMain {

	public static FileDetails objFileDetails;
	public static List<FileDetails> lstObjFileDetails = new ArrayList<FileDetails>();

	private static ApplicationContext ctx = new ClassPathXmlApplicationContext("mvc-config.xml");

	private static DeviceInfoService objDeviceInfoService = (DeviceInfoService) ctx.getBean("DeviceInfoServiceImpl");

	private static ScheduleMasterService objScheduleMasterService = (ScheduleMasterService) ctx
			.getBean("ScheduleMasterServiceImpl");

	private static FileDetailsService objFileDetailsService = (FileDetailsService) ctx
			.getBean("FileDetailsServiceImpl");

	private static FolderOperationRequestService objFolderOperationRequestService = (FolderOperationRequestService) ctx
			.getBean("FolderOperationRequestServiceImpl");

	private static KeyValueService objKeyValueService = (KeyValueService) ctx
			.getBean("KeyValueServiceImpl");
	
	/**
	 * @param args
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String compName = "";
		try {
			compName = InetAddress.getLocalHost().getHostName();
		} catch (Exception ex) {
			return;
		}
		DeviceInfo objDeviceInfo;
		objDeviceInfo = objDeviceInfoService.getDeviceInfoByDeviceComputerName(compName);
		
		if (objDeviceInfo == null) {
			firstTimeEntry(compName);
		} else {
			runScheduleIfRequired(objDeviceInfo);
			updateSuspiciousFileDetails(objDeviceInfo.getCompId());
			moveRequestedFiles(objDeviceInfo.getCompId(),objDeviceInfo.getCompName());
			moveRequestedFolders(objDeviceInfo.getCompId(),objDeviceInfo.getCompName());
			deleteRequestedFiles(objDeviceInfo.getCompId());
			deleteRequestedFolder(objDeviceInfo.getCompId());
		}
	}

	private static void updateSuspiciousFileDetails(int paramIntCompId) {
		List<KeyValue> lstObjKeyValue = objKeyValueService.listKeyValue();
		
		
	}
	
	private static void deleteRequestedFiles(int paramIntDeviceInfoCompId) {
		List<FileDetails> lstObjDeviceInfo = objFileDetailsService
				.getSuspiciousFileDetailsByDeviceInfoIdAndStatus(paramIntDeviceInfoCompId, EnumFileFolderOperationStatus.DELETEREQUEST);
		Path objPath=null;
		for (FileDetails objFileDetails : lstObjDeviceInfo) {
			objPath = FileSystems.getDefault().getPath(objFileDetails.getFileFullPath());
			objFileDetails.setFileStatus(deleteFile(objPath));
			objFileDetailsService.updateFileDetails(objFileDetails);
		}
	}

	private static EnumFileFolderOperationStatus deleteFile(Path paramObjPathToDelete){
		try {
			System.out.println("paramObjPathToDelete :::: "+paramObjPathToDelete.toString());
			Files.delete(paramObjPathToDelete);
			return EnumFileFolderOperationStatus.DELETEED;
		} catch (NoSuchFileException x) {
			return EnumFileFolderOperationStatus.NOTEXIST;
		} catch (DirectoryNotEmptyException x) {
			return EnumFileFolderOperationStatus.NOTEXIST;
		} catch (IOException x) {
			return EnumFileFolderOperationStatus.NORIGHTS;
		}
	}
	
	private static void deleteRequestedFolder(int paramIntDeviceInfoCompId) {
		List<FolderOperationRequest> lstObjFolderOperationRequest = objFolderOperationRequestService.listFolderOperationRequestByDeviceInfoId(paramIntDeviceInfoCompId, EnumFileFolderOperationStatus.DELETEREQUEST);
		
		List<FileDetails> lstObjFileDetailsToDelete=null;
		Path objPath=null;
		for(FolderOperationRequest objFolderOperationRequest : lstObjFolderOperationRequest){
			System.out.println("objFolderOperationRequest.getFoldFullPath()="+objFolderOperationRequest.getFoldFullPath()+" :: paramIntDeviceInfoCompId="+paramIntDeviceInfoCompId+" ::lstObjFileDetailsToDelete :::: "+lstObjFileDetailsToDelete);
			
			lstObjFileDetailsToDelete=objFileDetailsService.listFileDetailsToDeleteFolderByLastSuccessScheduleData(paramIntDeviceInfoCompId, objFolderOperationRequest.getFoldFullPath());
			for(FileDetails objFileDetailsToDelete : lstObjFileDetailsToDelete){
				System.out.println("objFileDetailsToDelete :::::: "+objFileDetailsToDelete);
				objPath = FileSystems.getDefault().getPath(objFileDetailsToDelete.getFileFullPath());
				objFileDetailsToDelete.setFileStatus(deleteFile(objPath));
				objFileDetailsService.updateFileDetails(objFileDetailsToDelete);
			}
			if(new File(objFolderOperationRequest.getFoldFullPath()).exists()){
				recursiveFolderDelete(new File(objFolderOperationRequest.getFoldFullPath()));
				if(new File(objFolderOperationRequest.getFoldFullPath()).exists()){
					objFolderOperationRequest.setFoldStatus(EnumFileFolderOperationStatus.FOLDERNOTEMPTY);
				}else{
					objFolderOperationRequest.setFoldStatus(EnumFileFolderOperationStatus.DELETEED);
				}
			}else{
				objFolderOperationRequest.setFoldStatus(EnumFileFolderOperationStatus.NOTEXIST);
			}
			objFolderOperationRequestService.updateFileDetails(objFolderOperationRequest);
		}
	}
	
	/**
	 * @param file
	 * @return
	 */
	public static void recursiveFolderDelete(File file) {
        //to end the recursive loop
        if (!file.exists() || file.isFile()){
            return;
        }
         
        //if directory, go inside and call recursively
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                //call recursively
                recursiveFolderDelete(f);
            }
        }
        //call delete to delete files and empty directory
//        if(file.isDirectory()){
        	file.delete();
/*        	return true;
        }else
        	return false;*/
    }
	
	private static void moveRequestedFolders(int paramIntCompId, String paramStrCompName){
		List<FolderOperationRequest> lstObjFolderOperationRequest = objFolderOperationRequestService.listFolderOperationRequestByDeviceInfoId(paramIntCompId, EnumFileFolderOperationStatus.MOVEREQUEST);
		FileDetails objFileDetailsToDeleteFilter=new FileDetails();
		List<FileDetails> lstObjFileDetailsToMove=null;
		Path objPath=null;
		
		DateTime dateForFolder = new DateTime();
		File newDir = new File(objKeyValueService.getKeyValueByKey(EnumKeyValue.NETWORK_LOC_TO_MOVE.name()).getKvalValue().replaceAll("\\\\", "\\\\\\\\") + paramStrCompName + "\\"
				+ dateForFolder.getYear() + "-" + dateForFolder.getMonthOfYear() + "-" + dateForFolder.getDayOfMonth()
				+ "_" + dateForFolder.getHourOfDay() + "-" + dateForFolder.getMinuteOfHour());

		if (lstObjFolderOperationRequest != null && lstObjFolderOperationRequest.size() > 0)
			if (!newDir.exists()) {
				try {
					newDir.mkdir();
				} catch (Exception ex) {
					ex.printStackTrace();
					return;
				}
			}

		File objFileDestination=null;
		PrintWriter out = null;
		
		for(FolderOperationRequest objFolderOperationRequest : lstObjFolderOperationRequest){
				try {
					int i=0;

					do{
						if(i==0)
							objFileDestination=new File(newDir.getPath() + "\\" + objFolderOperationRequest.getFoldFullPath().substring(objFolderOperationRequest.getFoldFullPath().lastIndexOf("\\")));
						else
							objFileDestination=new File(newDir.getPath() + "\\" + objFolderOperationRequest.getFoldFullPath().substring(objFolderOperationRequest.getFoldFullPath().lastIndexOf("\\"))+"_"+i);
						i++;
					}while(objFileDestination.exists());
					System.out.println("objFolderOperationRequest.getFoldFullPath()"+objFolderOperationRequest.getFoldFullPath()+" ::: objFileDestination.getPath()"+objFileDestination.getPath());
					FileUtils.copyDirectory(new File(objFolderOperationRequest.getFoldFullPath()), objFileDestination, true);
					objFolderOperationRequest.setFoldStatus(EnumFileFolderOperationStatus.MOVED);

					try {
						out = new PrintWriter(new BufferedWriter(new FileWriter(newDir.getPath()+"\\FileDetails.txt", true)));
						out.println(objFileDestination.getName()+"="+objFolderOperationRequest.getFoldFullPath());
					}catch (IOException e) {
					    System.err.println(e);
					}finally{
					    if(out != null){
					        out.close();
					    }
					}
				} catch (Exception ex) {
					objFolderOperationRequest.setFoldStatus(EnumFileFolderOperationStatus.MOVEFAILED);
					ex.printStackTrace();
				}
				
			
			objFolderOperationRequestService.updateFileDetails(objFolderOperationRequest);
		}
	}
	
	private static void moveRequestedFiles(int paramIntCompId, String paramStrCompName) {
		List<FileDetails> lstObjDeviceInfo = objFileDetailsService
				.getSuspiciousFileDetailsByDeviceInfoIdAndStatus(paramIntCompId, EnumFileFolderOperationStatus.MOVEREQUEST);

		DateTime dateForFolder = new DateTime();
		File newDir = new File(objKeyValueService.getKeyValueByKey(EnumKeyValue.NETWORK_LOC_TO_MOVE.name()).getKvalValue().replaceAll("\\\\", "\\\\\\\\") + paramStrCompName + "\\"
				+ dateForFolder.getYear() + "-" + dateForFolder.getMonthOfYear() + "-" + dateForFolder.getDayOfMonth()
				+ "_" + dateForFolder.getHourOfDay() + "-" + dateForFolder.getMinuteOfHour());

		if (lstObjDeviceInfo != null && lstObjDeviceInfo.size() > 0)
			if (!newDir.exists()) {
				try {
					newDir.mkdir();
				} catch (Exception ex) {
					ex.printStackTrace();
					return;
				}
			}
		
		File objFileDestination=null;
		PrintWriter out = null;
		
		for (FileDetails objFileDetailsToMove : lstObjDeviceInfo) {
			try {
				int i=0;

				do{
					if(i==0)
						objFileDestination=new File(newDir.getPath() + "\\" + objFileDetailsToMove.getFileName());
					else
						objFileDestination=new File(newDir.getPath() + "\\" + objFileDetailsToMove.getFileName()+"_"+i);
					i++;
				}while(objFileDestination.exists());
				FileUtils.copyFile(new File(objFileDetailsToMove.getFileFullPath()), objFileDestination, true);
				objFileDetailsToMove.setFileStatus(EnumFileFolderOperationStatus.MOVED);

				try {
					out = new PrintWriter(new BufferedWriter(new FileWriter(newDir.getPath()+"\\FileDetails.txt", true)));
					out.println(objFileDestination.getName()+"="+objFileDetailsToMove.getFileFullPath());
				}catch (IOException e) {
				    System.err.println(e);
				}finally{
				    if(out != null){
				        out.close();
				    }
				}
			} catch (Exception ex) {
				objFileDetailsToMove.setFileStatus(EnumFileFolderOperationStatus.MOVEFAILED);
				ex.printStackTrace();
			}
			objFileDetailsService.updateFileDetails(objFileDetailsToMove);
		}
	}

	private static void firstTimeEntry(String paramStrComputerName) {
		DeviceInfo objDeviceInfo;
		objDeviceInfo = new DeviceInfo();
		try {
			System.out
					.println("InetAddress.getLocalHost().getHostName() : " + InetAddress.getLocalHost().getHostName());
			objDeviceInfo.setCompName(InetAddress.getLocalHost().getHostName());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		objDeviceInfo.setCompOsName(System.getProperty("os.name"));
		objDeviceInfo.setCompProcessorType(System.getProperty("sun.cpu.isalist"));
		objDeviceInfo.setCompUserName(System.getProperty("user.name"));
		try {
			System.out.println("Device Info: "+objDeviceInfo);
			objDeviceInfoService.addDeviceInfo(objDeviceInfo);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		createNewCurrentSchedule(paramStrComputerName, "FirstTime");
	}

	private static void createNewCurrentSchedule(String paramStrComputerName, String paramStrCreatedBy) {

		DeviceInfo objDeviceInfo = objDeviceInfoService.getDeviceInfoByDeviceComputerName(paramStrComputerName);
		ScheduleMaster objScheduleMaster = new ScheduleMaster();
		objScheduleMaster.setObjDeviceInfo(objDeviceInfo);
		objScheduleMaster.setSchCreatedBy(paramStrCreatedBy);
		objScheduleMaster.setSchCreatedDate(new Date());
		objScheduleMaster.setSchScheduledDateTime(new Date());
		objScheduleMaster.setSchStatus(EnumScheduleStatus.PENDING);
		try {
			objScheduleMasterService.addScheduleMaster(objScheduleMaster);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private static void runScheduleIfRequired(DeviceInfo paramObjObjDeviceInfo) {
		ScheduleMaster objScheduleMaster = objScheduleMasterService
				.getScheduleMasterByDeviceComputerId(paramObjObjDeviceInfo.getCompId());
		List<KeyValue> lstObjKeyValue = objKeyValueService.listKeyValue();
		Map<String, String> mapObjKeyValue=new HashMap<String, String>();
		
		for (KeyValue objKeyValue : lstObjKeyValue)
			mapObjKeyValue.put(objKeyValue.getKvalId(), objKeyValue.getKvalValue());
		
		System.out.println("mapObjKeyValue : " + mapObjKeyValue);
		if (objScheduleMaster != null) {
			if (objScheduleMaster.getSchScheduledDateTime().before(new Date())
					&& objScheduleMaster.getSchStatus().toString().equalsIgnoreCase(EnumScheduleStatus.PENDING.name())) {
				// objFileDetailsService.removeFileDetailsByDeviceInfoId(paramObjObjDeviceInfo.getCompId());
				paramObjObjDeviceInfo = getDriveDetails(paramObjObjDeviceInfo, objScheduleMaster, mapObjKeyValue);
				System.out.println("before list objScheduleMaster : " + objScheduleMaster);
				try {
					objDeviceInfoService.updateDeviceInfo(paramObjObjDeviceInfo);
					objScheduleMaster.setSchStatus(EnumScheduleStatus.SUCCESS);
				} catch (Exception ex) {
					objScheduleMaster.setSchStatus(EnumScheduleStatus.FAILED);
					ex.printStackTrace();
				}
				objScheduleMaster.setSchActualRunDateTime(new Date());
				if (objScheduleMaster.getSchStatus().toString().equalsIgnoreCase(EnumScheduleStatus.SUCCESS.name()))
					objScheduleMasterService.updateScheduleMaster(objScheduleMaster);
				else {
					objScheduleMasterService.updateScheduleMaster(objScheduleMaster);
					createNewCurrentSchedule(paramObjObjDeviceInfo.getCompName(), "AutoOnFail");
				}
			}
		}
	}

	private static DeviceInfo getDriveDetails(DeviceInfo paramObjDeviceInfo, ScheduleMaster paramObjScheduleMaster, Map<String, String> paramMapKeyValue) {
		FileSystemView fsv = FileSystemView.getFileSystemView();
		File drive_list[] = File.listRoots();
		DriveInfo objDriveInfo;
		paramObjDeviceInfo.setLstObjFileDetails(new ArrayList<FileDetails>());
		List<DriveInfo> lstObjDriveInfo = new ArrayList<DriveInfo>();
		String suspiciousFolderKeys[]={};
		String suspiciousExtension[]={};
		String suspiciousFilterStrDrive="";
		
		if(paramMapKeyValue!=null && paramMapKeyValue.get(EnumKeyValue.FOLDER.name())!=null){
			suspiciousFolderKeys=paramMapKeyValue.get(EnumKeyValue.FOLDER.name()).split(",");
		}
		if(paramMapKeyValue!=null && paramMapKeyValue.get(EnumKeyValue.EXTENSION.name())!=null){
			suspiciousExtension=paramMapKeyValue.get(EnumKeyValue.EXTENSION.name()).split(",");
		}
		if(paramMapKeyValue!=null && paramMapKeyValue.get(EnumKeyValue.FILT_FOLD_EXET_DRIVE.name())!=null){
			suspiciousFilterStrDrive=paramMapKeyValue.get(EnumKeyValue.FILT_FOLD_EXET_DRIVE.name());
		}
		
		for (File drive : drive_list) {
			objDriveInfo = new DriveInfo();
			objDriveInfo.setObjDeviceInfo(paramObjDeviceInfo);
			objDriveInfo.setDrvLetter(drive.getAbsolutePath());
			objDriveInfo.setDrvTotalSpace(drive.getTotalSpace());
			objDriveInfo.setDrvFreeSpace(drive.getFreeSpace());
			objDriveInfo.setDrvUsableSpace(drive.getUsableSpace());
			objDriveInfo.setDrvType(fsv.getSystemTypeDescription(drive));
			lstObjDriveInfo.add(objDriveInfo);
			if (fsv.getSystemTypeDescription(drive).equalsIgnoreCase("Local Disk"))
				list(drive, drive.getAbsolutePath(), paramObjDeviceInfo, paramObjScheduleMaster, suspiciousFolderKeys, suspiciousExtension, suspiciousFilterStrDrive);
		}
		paramObjDeviceInfo.setLstObjDriveInfo(lstObjDriveInfo);
		paramObjDeviceInfo.setLstObjFileDetails(lstObjFileDetails);
		return paramObjDeviceInfo;
	}
	
	private static void list(File f, String root, DeviceInfo paramObjDeviceInfo,
			ScheduleMaster paramObjScheduleMaster, String[] suspiciousFolderKeys, String[] suspiciousExtension, String paramStrDrive) {
		if (f.isFile()) {
			try {
				
				// System.out.println("File Name : "+f.getAbsolutePath());
				objFileDetails = new FileDetails();
				objFileDetails.setObjDeviceInfo(paramObjDeviceInfo);
				objFileDetails.setFileDrive(root);
				
				
				if (!(f.getName().lastIndexOf('.') == -1))
					if (f.getName().substring(f.getName().lastIndexOf('.')).length() < 10){
						objFileDetails.setFileExtension(f.getName().substring(f.getName().lastIndexOf('.')));
						for(String extension : suspiciousExtension)
							if(objFileDetails.getFileExtension().toUpperCase().indexOf(extension.toUpperCase())>-1 && root.toUpperCase().indexOf(paramStrDrive)>-1)
								objFileDetails.setFileStatus(EnumFileFolderOperationStatus.SUSPICIOUS);
					}
					else
						objFileDetails.setFileExtension(EnumFileFolderOperationStatus.NOTEXIST.name());
				else
					objFileDetails.setFileExtension(EnumFileFolderOperationStatus.NOTEXIST.name());// objFileDetails.setFileExtension(f.getName().substring(f.getName().lastIndexOf('.'),(f.getName().lastIndexOf('.')+10)));
				
				
				
				
				
				objFileDetails.setObjScheduleMaster(paramObjScheduleMaster);
				objFileDetails.setFileFullPath(f.getAbsolutePath());
				objFileDetails.setFileCreationDate(new DateTime(Files.readAttributes(Paths.get(f.getPath()), BasicFileAttributes.class).creationTime().toMillis()).toDate());
				objFileDetails.setFileLastAccessDate(new DateTime(Files.readAttributes(Paths.get(f.getPath()), BasicFileAttributes.class).lastAccessTime().toMillis()).toDate());
				objFileDetails.setFileLastModifiedDate(new DateTime(Files.readAttributes(Paths.get(f.getPath()), BasicFileAttributes.class).lastModifiedTime().toMillis()).toDate());
				objFileDetails.setFileFolderPath(f.getParent());
				for(String folderKey : suspiciousFolderKeys){
					if(objFileDetails.getFileFolderPath().toUpperCase().indexOf(folderKey.toUpperCase())>-1 && root.toUpperCase().indexOf(paramStrDrive)>-1)
						objFileDetails.setFileStatus(EnumFileFolderOperationStatus.SUSPICIOUS);
				}
				
				/*System.out.println("objFileDetails.getFileExtension().toUpperCase()"+objFileDetails.getFileExtension().toUpperCase());
				System.out.println("suspiciousExtension"+suspiciousExtension.toString());
				System.out.println("paramStrDrive"+paramStrDrive.toString());
				System.out.println("objFileDetails.getFileFolderPath().toUpperCase()"+objFileDetails.getFileFolderPath().toUpperCase());
				System.out.println("suspiciousFolderKeys"+suspiciousFolderKeys);*/
				
				
				objFileDetails.setFileName(f.getName());
				objFileDetails.setFileSize(f.length());
				lstObjFileDetails.add(objFileDetails);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return;
		} else {
			File dir_list[] = f.listFiles(); // lists all contents if a
												// directory
			for (File t : dir_list)
				try {

					if (!Files.readAttributes(t.toPath(), DosFileAttributes.class).isSystem())
						list(t, root, paramObjDeviceInfo, paramObjScheduleMaster, suspiciousFolderKeys, suspiciousExtension, paramStrDrive); // calls
																					// list
																					// for
																					// each
																					// content
																					// of
					// directory
				} catch (Exception e) {
				}
		}
	}

}
