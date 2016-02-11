package org.systemaudit.dao;

import java.util.List;

import org.systemaudit.model.EnumFileFolderOperationStatus;
import org.systemaudit.model.FolderOperationRequest;


public abstract interface FolderOperationRequestDAO
  extends GenericDAO<FolderOperationRequest, Integer>
{
  public abstract void updateFolderOperationRequest(FolderOperationRequest paramObjFolderOperationRequest);
  
  public abstract List<FolderOperationRequest> listFolderOperationRequests(EnumFileFolderOperationStatus paramEnumFileFolderOperationStatus);
  
  public abstract List<FolderOperationRequest> listFolderOperationRequestByDeviceInfoId(int paramIntDeviceInfoId, EnumFileFolderOperationStatus paramEnumFileFolderOperationStatus);

  public abstract FolderOperationRequest getFolderOperationRequestById(int paramIntId);
    
}
