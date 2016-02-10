package org.systemaudit.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.systemaudit.model.FileFolderOperationStatus;
import org.systemaudit.model.FolderOperationRequest;

@Repository("FolderOperationRequestDAOImpl")
public class FolderOperationRequestDAOImpl extends GenericDAOImpl<FolderOperationRequest, Integer> implements FolderOperationRequestDAO {
	
	
	public void updateFolderOperationRequest(FolderOperationRequest paramObjFolderOperationRequest) {
		getCurrentSession().update(paramObjFolderOperationRequest);
	}

	@SuppressWarnings("unchecked")
	public List<FolderOperationRequest> listFolderOperationRequests(FileFolderOperationStatus paramEnumFileFolderOperationStatus) {
		Criteria criteria=getCurrentSession().createCriteria(FolderOperationRequest.class);
		if(!paramEnumFileFolderOperationStatus.equals(FileFolderOperationStatus.ALL))
			criteria.add(Restrictions.eq("foldStatus", paramEnumFileFolderOperationStatus));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<FolderOperationRequest> listFolderOperationRequestByDeviceInfoId(int paramIntDeviceInfoId,FileFolderOperationStatus paramEnumFileFolderOperationStatus) {
		Criteria criteria = getCurrentSession().createCriteria(FolderOperationRequest.class).add(Restrictions.eq("compId", paramIntDeviceInfoId));
		if(!paramEnumFileFolderOperationStatus.equals(FileFolderOperationStatus.ALL))
			criteria.add(Restrictions.eq("foldStatus", paramEnumFileFolderOperationStatus));
		return criteria.list();
	}

	public FolderOperationRequest getFolderOperationRequestById(int paramIntId) {
		return (FolderOperationRequest) getCurrentSession().load(FolderOperationRequest.class, new Integer(paramIntId));
	}

}
