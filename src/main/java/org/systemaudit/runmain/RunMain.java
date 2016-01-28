/**
 * 
 */
package org.systemaudit.runmain;

import java.io.File;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.attribute.DosFileAttributes;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.ManyToOne;
import javax.swing.filechooser.FileSystemView;

import org.joda.time.DateTime;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.systemaudit.model.DeviceInfo;
import org.systemaudit.model.ScheduleMaster;
import org.systemaudit.model.DriveInfo;
import org.systemaudit.model.FileDetails;
import org.systemaudit.service.DeviceInfoService;
import org.systemaudit.service.FileDetailsService;
import org.systemaudit.service.ScheduleMasterService;
import org.systemaudit.service.ScheduleOperationService;

/**
 * @author Administrator
 *
 */
public class RunMain {

	public static FileDetails objFileDetails;
	public static List<FileDetails> lstObjFileDetails = new ArrayList<FileDetails>();
	
	/**
	 * @param args
	 */
	@SuppressWarnings({ "resource" })
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ApplicationContext ctx = 
			      new ClassPathXmlApplicationContext("mvc-config.xml");
		
		DeviceInfoService objDeviceInfoService = 
			      (DeviceInfoService)ctx.getBean("DeviceInfoServiceImpl");
		
		ScheduleMasterService objScheduleMasterService = 
			      (ScheduleMasterService)ctx.getBean("ScheduleMasterServiceImpl");
		
		FileDetailsService objFileDetailsService = 
			      (FileDetailsService)ctx.getBean("FileDetailsServiceImpl");
		
		ScheduleOperationService objScheduleOperationService = 
				(ScheduleOperationService)ctx.getBean("ScheduleOperationServiceImpl");
		String compName="";
		try{compName=InetAddress.getLocalHost().getHostName();}catch(Exception ex){return;}
		DeviceInfo objDeviceInfo;
		ScheduleMaster objScheduleMaster;
		objDeviceInfo = objDeviceInfoService.getDeviceInfoByDeviceComputerName(compName);
		if(objDeviceInfo==null){
			objDeviceInfo=new DeviceInfo();
			try{
				System.out.println("InetAddress.getLocalHost().getHostName() : "+InetAddress.getLocalHost().getHostName());
				objDeviceInfo.setCompName(InetAddress.getLocalHost().getHostName());
			}catch(Exception ex){
				ex.printStackTrace();
			}
			objDeviceInfo.setCompOsName(System.getProperty("os.name"));
			objDeviceInfo.setCompProcessorType(System.getProperty("sun.cpu.isalist"));
			objDeviceInfo.setCompUserName(System.getProperty("user.name"));
			try {
				objDeviceInfoService.addDeviceInfo(objDeviceInfo);
			}catch(Exception ex){
				ex.printStackTrace();
			}
			System.out.println("after Save : objDeviceInfo : "+objDeviceInfo);
			objScheduleMaster=new ScheduleMaster();
			objDeviceInfo = objDeviceInfoService.getDeviceInfoByDeviceComputerName(compName);
			objScheduleMaster.setObjDeviceInfo(objDeviceInfo);
			objScheduleMaster.setSchCreatedBy("FirstTime");
			objScheduleMaster.setSchCreatedDate(new Date());
			objScheduleMaster.setSchRunDateTime(new Date());
			objScheduleMaster.setSchStatus("P");
			System.out.println("beforeInsert : objScheduleMaster : "+objScheduleMaster);
			objScheduleMasterService.addScheduleMaster(objScheduleMaster);
		}else{
			objScheduleMaster=objScheduleMasterService.getScheduleMasterByDeviceComputerId(objDeviceInfo.getCompId());
			System.out.println("Else objScheduleMaster : "+objScheduleMaster);
			if(objScheduleMaster!=null){
				if(objScheduleMaster.getSchRunDateTime().before(new Date()) && (objScheduleMaster.getSchStatus().compareToIgnoreCase("P")==0||objScheduleMaster.getSchStatus().compareToIgnoreCase("F")==0)){
					objFileDetailsService.removeFileDetailsByDeviceInfoId(objDeviceInfo.getCompId());
					objDeviceInfo=getDriveDetails(objDeviceInfo);
					System.out.println("before list objScheduleMaster : "+objScheduleMaster);
					try{
						objDeviceInfoService.updateDeviceInfo(objDeviceInfo);
						objScheduleMaster.setSchStatus("S");
					}catch(Exception ex){
						objScheduleMaster.setSchStatus("F");
						ex.printStackTrace();
					}
					
					objScheduleMasterService.updateScheduleMaster(objScheduleMaster);
				}
			}
		}
	}
	
	private static DeviceInfo getDriveDetails(DeviceInfo paramObjDeviceInfo){
			FileSystemView fsv = FileSystemView.getFileSystemView();
			File drive_list[] = File.listRoots();
			DriveInfo objDriveInfo;
			paramObjDeviceInfo.setLstObjFileDetails(new ArrayList<FileDetails>());
			List<DriveInfo> lstObjDriveInfo=new ArrayList<DriveInfo>();
			for (File drive : drive_list) {
				objDriveInfo=new DriveInfo();
				objDriveInfo.setObjDeviceInfo(paramObjDeviceInfo);
				objDriveInfo.setDrvLetter(drive.getAbsolutePath());
				objDriveInfo.setDrvTotalSpace(drive.getTotalSpace());
				objDriveInfo.setDrvFreeSpace(drive.getFreeSpace());
				objDriveInfo.setDrvUsableSpace(drive.getUsableSpace());
				objDriveInfo.setDrvType(fsv.getSystemTypeDescription(drive));
				lstObjDriveInfo.add(objDriveInfo);
				if (fsv.getSystemTypeDescription(drive).equalsIgnoreCase("Local Disk"))
					list(drive, drive.getAbsolutePath(),paramObjDeviceInfo);
			}
			paramObjDeviceInfo.setLstObjDriveInfo(lstObjDriveInfo);
			paramObjDeviceInfo.setLstObjFileDetails(lstObjFileDetails);
			return paramObjDeviceInfo;
		}
	
	static void list(File f, String root, DeviceInfo paramObjDeviceInfo) {
		if (f.isFile()) {
			try {
				//System.out.println("File Name : "+f.getAbsolutePath());
				objFileDetails=new FileDetails();
				objFileDetails.setObjDeviceInfo(paramObjDeviceInfo);
				objFileDetails.setFileDrive(root);
				
				if (!(f.getName().lastIndexOf('.') == -1))
					if(f.getName().substring(f.getName().lastIndexOf('.')).length()>10)
						objFileDetails.setFileExtension(f.getName().substring(f.getName().lastIndexOf('.'),(f.getName().lastIndexOf('.')+10)));
					else
						objFileDetails.setFileExtension(f.getName().substring(f.getName().lastIndexOf('.')));
				
				objFileDetails.setFileFullPath(f.getAbsolutePath());
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
						list(t, root, paramObjDeviceInfo); // calls list for each content of
										// directory
				} catch (Exception e) {
				}
		}
	}

	

}
