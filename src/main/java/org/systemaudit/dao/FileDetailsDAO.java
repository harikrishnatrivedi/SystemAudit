package org.systemaudit.dao;

import java.util.List;

import org.systemaudit.model.FileDetails;


public abstract interface FileDetailsDAO
  extends GenericDAO<FileDetails, Integer>
{
  public abstract void addFileDetails(FileDetails paramObjFileDetails);
  
  public abstract void updateFileDetails(FileDetails paramObjFileDetails);
  
  public abstract List<FileDetails> listFileDetails();
  
  public List<FileDetails> listFileDetailsByDeviceInfoId(int paramIntDeviceInfoId);
  
  public abstract FileDetails getFileDetailsById(int paramIntId);
  
  public abstract void removeFileDetails(int paramIntId);
  
  public abstract void removeFileDetailsByDeviceInfoId(int paramIntDeviceInfoId);
  
}
