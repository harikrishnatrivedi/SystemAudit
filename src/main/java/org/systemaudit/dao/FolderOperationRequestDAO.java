package org.systemaudit.dao;

import java.util.List;

import org.systemaudit.model.FileFolderOperationStatus;
import org.systemaudit.model.FolderOperationRequest;


public abstract interface FolderOperationRequestDAO
  extends GenericDAO<FolderOperationRequest, Integer>
{
  public abstract void updateFolderOperationRequest(FolderOperationRequest paramObjFolderOperationRequest);
  
  public abstract List<FolderOperationRequest> listFolderOperationRequests(FileFolderOperationStatus paramEnumFileFolderOperationStatus);
  
  public abstract List<FolderOperationRequest> listFolderOperationRequestByDeviceInfoId(int paramIntDeviceInfoId, FileFolderOperationStatus paramEnumFileFolderOperationStatus);

  public abstract FolderOperationRequest getFolderOperationRequestById(int paramIntId);
    
}
