package org.systemaudit.dao;

import java.util.List;

import org.systemaudit.model.FileFolderOperationStatus;
import org.systemaudit.model.FolderOperationRequest;


public abstract interface FolderOperationRequestDAO
  extends GenericDAO<FolderOperationRequest, Integer>
{
  public abstract void updateFolderOperationRequest(FolderOperationRequest paramObjFolderOperationRequest);
  
  public abstract List<FolderOperationRequest> listFolderOperationRequest(FileFolderOperationStatus paramEnumFileFolderOperationStatus);
  
  public abstract List<FolderOperationRequest> listFolderOperationRequestByDeviceInfoId(int paramIntDeviceInfoId, FileFolderOperationStatus paramEnumFileFolderOperationStatus);

  public abstract FolderOperationRequest getFolderOperationById(int paramIntId);
  
  public abstract void removeFolderOperationRequest(int paramIntId);
  
  public abstract void removeFolderOperationRequestByDeviceInfoId(int paramIntDeviceInfoId);
  
}
