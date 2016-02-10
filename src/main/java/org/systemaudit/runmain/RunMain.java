/**
 * 
 */
package org.systemaudit.runmain;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.attribute.DosFileAttributes;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.filechooser.FileSystemView;

import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.systemaudit.model.DeviceInfo;
import org.systemaudit.model.DriveInfo;
import org.systemaudit.model.FileDetails;
import org.systemaudit.model.FileFolderOperationStatus;
import org.systemaudit.model.FolderOperationRequest;
import org.systemaudit.model.KeyValue;
import org.systemaudit.model.ScheduleMaster;
import org.systemaudit.model.ScheduleStatus;
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
		KeyValue objKeyValue = objKeyValueService.getKeyValueByKey("FOLDER");
		if (objDeviceInfo == null) {
			firstTimeEntry(compName);
		} else {
			runScheduleIfRequired(objDeviceInfo);
			moveRequestedFiles(objDeviceInfo);
			deleteRequestedFiles(objDeviceInfo.getCompId());
			deleteRequestedFolder(objDeviceInfo.getCompId());
			
		}
	}

	private static void deleteRequestedFiles(int paramIntDeviceInfoCompId) {
		List<FileDetails> lstObjDeviceInfo = objFileDetailsService
				.getSuspiciousFileDetailsByDeviceInfoIdAndStatus(paramIntDeviceInfoCompId, FileFolderOperationStatus.DELETEREQUEST);
		Path objPath=null;
		for (FileDetails objFileDetails : lstObjDeviceInfo) {
			objPath = FileSystems.getDefault().getPath(objFileDetails.getFileFullPath());
			objFileDetails.setFileStatus(deleteFile(objPath));
			objFileDetailsService.updateFileDetails(objFileDetails);
		}
	}

	private static FileFolderOperationStatus deleteFile(Path paramObjPathToDelete){
		try {
			Files.delete(paramObjPathToDelete);
			return FileFolderOperationStatus.DELETEED;
		} catch (NoSuchFileException x) {
			return FileFolderOperationStatus.NOTEXIST;
		} catch (DirectoryNotEmptyException x) {
			return FileFolderOperationStatus.NOTEXIST;
		} catch (IOException x) {
			return FileFolderOperationStatus.NORIGHTS;
		}
	}
	
	private static void deleteRequestedFolder(int paramIntDeviceInfoCompId) {
		List<FolderOperationRequest> lstObjFolderOperationRequest = objFolderOperationRequestService.listFolderOperationRequestByDeviceInfoId(paramIntDeviceInfoCompId, FileFolderOperationStatus.DELETEREQUEST);
		FileDetails objFileDetailsToDeleteFilter=new FileDetails();
		List<FileDetails> lstObjFileDetailsToDelete=null;
		Path objPath=null;
		for(FolderOperationRequest objFolderOperationRequest : lstObjFolderOperationRequest){
			objFileDetailsToDeleteFilter.setFileFolderPath(objFolderOperationRequest.getFoldFullPath());
			lstObjFileDetailsToDelete=objFileDetailsService.listFileDetailsByFileFilter(objFileDetailsToDeleteFilter);
			for(FileDetails objFileDetailsToDelete : lstObjFileDetailsToDelete){
				objPath = FileSystems.getDefault().getPath(objFileDetailsToDelete.getFileFullPath());
				objFileDetailsToDelete.setFileStatus(deleteFile(objPath));
				objFileDetailsService.updateFileDetails(objFileDetailsToDelete);
			}
			if(new File(objFolderOperationRequest.getFoldFullPath()).exists()){
				recursiveFolderDelete(new File(objFolderOperationRequest.getFoldFullPath()));
				if(new File(objFolderOperationRequest.getFoldFullPath()).exists()){
					objFolderOperationRequest.setFoldStatus(FileFolderOperationStatus.FOLDERNOTEMPTY);
				}else{
					objFolderOperationRequest.setFoldStatus(FileFolderOperationStatus.DELETEED);
				}
			}else{
				objFolderOperationRequest.setFoldStatus(FileFolderOperationStatus.NOTEXIST);
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
	
	private static void moveRequestedFiles(DeviceInfo paramObjDeviceInfo) {
		List<FileDetails> lstObjDeviceInfo = objFileDetailsService
				.getSuspiciousFileDetailsByDeviceInfoIdAndStatus(paramObjDeviceInfo.getCompId(), FileFolderOperationStatus.MOVEREQUEST);

		DateTime dateForFolder = new DateTime();

		File newDir = new File("\\\\192.168.0.214\\SystemAudit\\" + paramObjDeviceInfo.getCompName() + "\\"
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
		for (FileDetails objFileDetails : lstObjDeviceInfo) {
			try {
				FileUtils.copyFile(new File(objFileDetails.getFileFullPath()),
						new File(newDir.getPath() + "\\" + objFileDetails.getFileName()));
				objFileDetails.setFileStatus(FileFolderOperationStatus.MOVED);
				objFileDetailsService.updateFileDetails(objFileDetails);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
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
		objScheduleMaster.setSchStatus(ScheduleStatus.PENDING);
		try {
			objScheduleMasterService.addScheduleMaster(objScheduleMaster);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private static void runScheduleIfRequired(DeviceInfo paramObjObjDeviceInfo) {
		ScheduleMaster objScheduleMaster = objScheduleMasterService
				.getScheduleMasterByDeviceComputerId(paramObjObjDeviceInfo.getCompId());
		System.out.println("Else objScheduleMaster : " + objScheduleMaster);
		if (objScheduleMaster != null) {
			if (objScheduleMaster.getSchScheduledDateTime().before(new Date())
					&& objScheduleMaster.getSchStatus().toString().equalsIgnoreCase(ScheduleStatus.PENDING.name())) {
				// objFileDetailsService.removeFileDetailsByDeviceInfoId(paramObjObjDeviceInfo.getCompId());
				paramObjObjDeviceInfo = getDriveDetails(paramObjObjDeviceInfo, objScheduleMaster);
				System.out.println("before list objScheduleMaster : " + objScheduleMaster);
				try {
					objDeviceInfoService.updateDeviceInfo(paramObjObjDeviceInfo);
					objScheduleMaster.setSchStatus(ScheduleStatus.SUCCESS);
				} catch (Exception ex) {
					objScheduleMaster.setSchStatus(ScheduleStatus.FAILED);
					ex.printStackTrace();
				}
				objScheduleMaster.setSchActualRunDateTime(new Date());
				if (objScheduleMaster.getSchStatus().toString().equalsIgnoreCase(ScheduleStatus.SUCCESS.name()))
					objScheduleMasterService.updateScheduleMaster(objScheduleMaster);
				else {
					objScheduleMasterService.updateScheduleMaster(objScheduleMaster);
					createNewCurrentSchedule(paramObjObjDeviceInfo.getCompName(), "AutoOnFail");
				}
			}
		}
	}

	private static DeviceInfo getDriveDetails(DeviceInfo paramObjDeviceInfo, ScheduleMaster paramObjScheduleMaster) {
		FileSystemView fsv = FileSystemView.getFileSystemView();
		File drive_list[] = File.listRoots();
		DriveInfo objDriveInfo;
		paramObjDeviceInfo.setLstObjFileDetails(new ArrayList<FileDetails>());
		List<DriveInfo> lstObjDriveInfo = new ArrayList<DriveInfo>();
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
				list(drive, drive.getAbsolutePath(), paramObjDeviceInfo, paramObjScheduleMaster);
		}
		paramObjDeviceInfo.setLstObjDriveInfo(lstObjDriveInfo);
		paramObjDeviceInfo.setLstObjFileDetails(lstObjFileDetails);
		return paramObjDeviceInfo;
	}

	private static void list(File f, String root, DeviceInfo paramObjDeviceInfo,
			ScheduleMaster paramObjScheduleMaster) {
		if (f.isFile()) {
			try {
				// System.out.println("File Name : "+f.getAbsolutePath());
				objFileDetails = new FileDetails();
				objFileDetails.setObjDeviceInfo(paramObjDeviceInfo);
				objFileDetails.setFileDrive(root);

				if (!(f.getName().lastIndexOf('.') == -1))
					if (f.getName().substring(f.getName().lastIndexOf('.')).length() < 10)
						objFileDetails.setFileExtension(f.getName().substring(f.getName().lastIndexOf('.')));
					else
						objFileDetails.setFileExtension("NotAvail");
				else
					objFileDetails.setFileExtension("NotAvail");// objFileDetails.setFileExtension(f.getName().substring(f.getName().lastIndexOf('.'),(f.getName().lastIndexOf('.')+10)));
				objFileDetails.setObjScheduleMaster(paramObjScheduleMaster);
				objFileDetails.setFileFullPath(f.getAbsolutePath());
				objFileDetails.setFileFolderPath(f.getParent());
				objFileDetails.setFileName(f.getName());
				objFileDetails.setFileSize(f.length());
				lstObjFileDetails.add(objFileDetails);
			} catch (Exception e) {
			}
			return;
		} else {
			File dir_list[] = f.listFiles(); // lists all contents if a
												// directory
			for (File t : dir_list)
				try {

					if (!Files.readAttributes(t.toPath(), DosFileAttributes.class).isSystem())
						list(t, root, paramObjDeviceInfo, paramObjScheduleMaster); // calls
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
