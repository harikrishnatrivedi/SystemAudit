package org.systemaudit.service;

import java.util.List;

import org.systemaudit.model.FileDetails;

public abstract interface FileDetailsService
{
  public abstract void addFileDetails(FileDetails paramObjFileDetails);
  
  public abstract void updateFileDetails(FileDetails paramObjFileDetails);
  
  public abstract List<FileDetails> listFileDetails();
  
  public abstract List<FileDetails> listFileDetailsByDeviceInfoId(int paramIntDeviceInfoId);
  
  public abstract List<FileDetails> getSuspiciousFileDetailsByDeviceInfoIdAndStatus(int paramIntDeviceInfoCompId,String paramStrFileStatus);
  
  public abstract FileDetails getFileDetailsById(int paramIntFileDetailsId);
  
  public abstract void removeFileDetails(int paramIntFileDetailsId);
  
  public abstract void removeFileDetailsByDeviceInfoId(int paramIntDeviceInfoId);
 
}
