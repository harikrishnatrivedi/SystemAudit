package org.systemaudit.runmain;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;
import org.systemaudit.model.EnumFileFolderOperationStatus;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

		
				File file = new File("D:\\JAVADevelopment\\Projects\\SystemAuditWeb\\src\\main\\java\\org\\systemaudit\\model\\FileDetails.java");
					
				
				
				
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
					BasicFileAttributes view = Files.readAttributes(Paths.get(file.getPath()), BasicFileAttributes.class);
					System.out.println("view.creationTime() : "+sdf.format(view.creationTime().toMillis()));
					System.out.println("view.lastAccessTime() new : "+ sdf.format(view.lastAccessTime().toMillis()));
					System.out.println("view.lastModifiedTime() : "+ sdf.format(view.lastModifiedTime().toMillis()));
					System.out.println("view.toString() : "+ view.toString());
					System.out.println("view.size() : "+ view.size());
					System.out.println("view.fileKey() : "+ view.fileKey());
					System.out.println("view.isDirectory() : "+ view.isDirectory());
					System.out.println("view.isRegularFile() : "+ view.isRegularFile());
					System.out.println("view.isSymbolicLink() : "+ view.isSymbolicLink());
				
					EnumFileFolderOperationStatus enumFileFolderOperationStatus=EnumFileFolderOperationStatus.MOVED;
					System.out.println("Enum"+enumFileFolderOperationStatus.toString());
					System.out.println("Enum"+enumFileFolderOperationStatus.name());
					System.out.println("Enum"+enumFileFolderOperationStatus);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
						
				
					File objFile = new File("D:\\TestDelete");
					recursiveDelete(objFile);
					
				
	}
	
	public static void recursiveDelete(File file) {
        //to end the recursive loop
        if (!file.exists()){
        	
            return;
        }
         
        //if directory, go inside and call recursively
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                //call recursively
                recursiveDelete(f);
            }
        }
        //call delete to delete files and empty directory
        file.delete();
        
    }

}
