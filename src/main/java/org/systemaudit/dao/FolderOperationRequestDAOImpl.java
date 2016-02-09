package org.systemaudit.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.systemaudit.model.FileFolderOperationStatus;
import org.systemaudit.model.FolderOperationRequest;

@Repository("FolderOperationRequestDAOImpl")
public class FolderOperationRequestDAOImpl extends GenericDAOImpl<FileDetails, Integer> implements FolderOperationRequestDAO {
	
	
	public void updateFolderOperationRequest(FolderOperationRequest paramObjFolderOperationRequest) {
		getCurrentSession().update(paramObjFolderOperationRequest);
	}

	@SuppressWarnings("unchecked")
	public List<FolderOperationRequest> listFolderOperationRequests(FileFolderOperationStatus paramEnumFileFolderOperationStatus) {
		Criteria criteria=getCurrentSession().createCriteria(FolderOperationRequest.class);
		if(!paramFileFolderOperationStatus.equals(FileFolderOperationStatus.ALL))
			criteria.add(Restrictions.eq("foldStatus", paramFileFolderOperationStatus));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<FolderOperationRequest> listFolderOperationRequestByDeviceInfoId(int paramIntDeviceInfoId,FileFolderOperationStatus paramEnumFileFolderOperationStatus) {
		return getCurrentSession().createQuery("from FileDetails where objDeviceInfo.compId= :compId")
				.setParameter("compId", paramIntDeviceInfoId).list();
	}

	public FolderOperationRequest getFolderOperationRequestById(int paramIntId) {
		return (FolderOperationRequest) getCurrentSession().load(FolderOperationRequest.class, new Integer(paramIntId));
	}

	public void removeFileDetails(int paramIntId) {
		FileDetails ed = (FileDetails) getCurrentSession().load(FileDetails.class, new Integer(paramIntId));
		if (ed != null) {
			getCurrentSession().delete(ed);
		}
	}

	public void removeFileDetailsByDeviceInfoId(int paramIntDeviceInfoId) {
		Query query = getCurrentSession()
				.createQuery("delete FileDetails where objDeviceInfo.compId= :compId");
		query.setInteger("compId", paramIntDeviceInfoId);
		query.executeUpdate();
	}
}
