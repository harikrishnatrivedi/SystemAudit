package org.systemaudit.service;

import java.util.List;

import org.systemaudit.model.FileDetails;
import org.systemaudit.model.FileFolderOperationStatus;
import org.systemaudit.model.FolderOperationRequest;

public abstract interface FolderOperationRequestService
{
	public abstract void updateFileDetails(FolderOperationRequest paramObjFolderOperationRequest);
 
	public abstract List<FolderOperationRequest> listFolderOperationRequest(FileFolderOperationStatus paramEnumFileFolderOperationStatus);
	
	public abstract List<FolderOperationRequest> listFolderOperationRequestByDeviceInfoId(int paramIntDeviceInfoId, FileFolderOperationStatus paramEnumFileFolderOperationStatus);
	
	public abstract FolderOperationRequest getFolderOperationRequestById(int paramIntFileDetailsId);
}
