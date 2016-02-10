package org.systemaudit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.systemaudit.dao.FolderOperationRequestDAO;
import org.systemaudit.model.FolderOperationRequest;
import org.systemaudit.model.FileFolderOperationStatus;;

@Service("FolderOperationRequestServiceImpl")
@Transactional
public class FolderOperationRequestServiceImpl implements FolderOperationRequestService {
	
	@Autowired
	private FolderOperationRequestDAO objFolderOperationRequestDAO;

	public void updateFileDetails(FolderOperationRequest paramObjFolderOperationRequest) {
		this.objFolderOperationRequestDAO.updateFolderOperationRequest(paramObjFolderOperationRequest);
	}

	public List<FolderOperationRequest> listFolderOperationRequest(FileFolderOperationStatus paramEnumFileFolderOperationStatus) {
		return this.objFolderOperationRequestDAO.listFolderOperationRequests(paramEnumFileFolderOperationStatus);
	}

	public List<FolderOperationRequest> listFolderOperationRequestByDeviceInfoId(int paramIntDeviceInfoId, FileFolderOperationStatus paramEnumFileFolderOperationStatus){
		return this.objFolderOperationRequestDAO.listFolderOperationRequestByDeviceInfoId(paramIntDeviceInfoId, paramEnumFileFolderOperationStatus);
	}
	
	public FolderOperationRequest getFolderOperationRequestById(int paramIntFileDetailsId) {
		return this.objFolderOperationRequestDAO.getFolderOperationRequestById(paramIntFileDetailsId);
	}	
}
