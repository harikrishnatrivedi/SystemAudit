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
		
		DeviceInfo objDeviceInfo;
		ScheduleMaster objScheduleMaster;
		objDeviceInfo = objDeviceInfoService.getDeviceInfoByDeviceComputerName(System.getProperty("user"));
		if(objDeviceInfo==null){
			objDeviceInfo=new DeviceInfo();
			try{
				objDeviceInfo.setCompName(System.getProperty(InetAddress.getLocalHost().getHostName()));
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
			objScheduleMaster=new ScheduleMaster();
			objScheduleMaster.setObjDeviceInfo(objDeviceInfo);
			objScheduleMaster.setSchCreatedBy("FirstTime");
			objScheduleMaster.setSchCreatedDate(new Date());
			objScheduleMaster.setSchRunDateTime(new DateTime());
			objScheduleMaster.setSchStatus("P");
			objScheduleMasterService.addScheduleMaster(objScheduleMaster);
		}else{
			objScheduleMaster=objScheduleMasterService.getScheduleMasterByDeviceComputerName(objDeviceInfo.getCompName());
			if(objScheduleMaster!=null){
				if(objScheduleMaster.getSchRunDateTime().isBeforeNow() && (objScheduleMaster.getSchStatus().compareToIgnoreCase("P")==0||objScheduleMaster.getSchStatus().compareToIgnoreCase("F")==0)){
					objDeviceInfo=getDriveDetails(objDeviceInfo);
					try{
						objDeviceInfoService.addDeviceInfo(objDeviceInfo);
						objScheduleMaster.setSchStatus("S");
					}catch(Exception ex){
						objScheduleMaster.setSchStatus("F");
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
				objFileDetails=new FileDetails();
				objFileDetails.setObjDeviceInfo(paramObjDeviceInfo);
				objFileDetails.setFileDrive(root);
				if (!(f.getName().lastIndexOf('.') == -1))
					objFileDetails.setFileExtension(f.getName().substring(f.getName().lastIndexOf('.')));
				objFileDetails.setFileFullPath(f.getAbsolutePath());
				objFileDetails.setFileName(f.getName().substring(f.getName().lastIndexOf("/")));
				objFileDetails.setFileSize(f.length());
				lstObjFileDetails.add(objFileDetails);
			} catch (Exception e) {
				System.out.println("in LIST method start : ");
				e.printStackTrace();
				System.out.println("in LIST method end :");
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
					System.out.println("exception of else calling list start :");
					// System.out.println("Error:"+fsv.getSystemTypeDescription(t));
					e.printStackTrace();
					System.out.println("exception  of else calling list end :");
				}
		}
	}

	

}
