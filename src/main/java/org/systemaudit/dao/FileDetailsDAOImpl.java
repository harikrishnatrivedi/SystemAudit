package org.systemaudit.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.systemaudit.model.FileDetails;

@Repository("FileDetailsDAOImpl")
public class FileDetailsDAOImpl extends GenericDAOImpl<FileDetails, Integer> implements FileDetailsDAO {
	public void addFileDetails(FileDetails paramObjFileDetails) {
		getCurrentSession().persist(paramObjFileDetails);
	}

	public void updateFileDetails(FileDetails paramObjFileDetails) {
		getCurrentSession().update(paramObjFileDetails);
	}

	@SuppressWarnings("unchecked")
	public List<FileDetails> listFileDetails() {
		return getCurrentSession().createQuery("from FileDetails").list();
	}

	@SuppressWarnings("unchecked")
	public List<FileDetails> listFileDetailsByDeviceInfoId(int paramIntDeviceInfoId) {
		return getCurrentSession().createQuery("from FileDetails where objDeviceInfo.compId= :compId")
				.setParameter("compId", paramIntDeviceInfoId).list();
	}

	public FileDetails getFileDetailsById(int paramIntId) {
		return (FileDetails) getCurrentSession().load(FileDetails.class, new Integer(paramIntId));
	}

	@SuppressWarnings("unchecked")
	public List<FileDetails> getSuspiciousFileDetailsByDeviceInfoIdAndStatus(int paramIntDeviceInfoId, String paramStrFileStatus){
		return getCurrentSession().createQuery("from FileDetails where objDeviceInfo.compId= :compId and fileStatus= :fileStatus")
				.setParameter("compId", paramIntDeviceInfoId)
				.setParameter("fileStatus", paramStrFileStatus)
				.list();
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
