package org.systemaudit.dao;

import java.util.List;

import org.systemaudit.model.FileDetails;
import org.systemaudit.model.FileFolderOperationStatus;


public abstract interface FileDetailsDAO
  extends GenericDAO<FileDetails, Integer>
{
  public abstract void addFileDetails(FileDetails paramObjFileDetails);
  
  public abstract void updateFileDetails(FileDetails paramObjFileDetails);
  
  public abstract List<FileDetails> listFileDetails();
  
  public abstract List<FileDetails> listFileDetailsByDeviceInfoId(int paramIntDeviceInfoId);

  public abstract List<FileDetails> getSuspiciousFileDetailsByDeviceInfoIdAndStatus(int paramIntDeviceInfoId,FileFolderOperationStatus paramEnumFileFolderOperationStatus);
	
  public abstract FileDetails getFileDetailsById(int paramIntId);
  
  public abstract void removeFileDetails(int paramIntId);
  
  public abstract void removeFileDetailsByDeviceInfoId(int paramIntDeviceInfoId);
  
}
