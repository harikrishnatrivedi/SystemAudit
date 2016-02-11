package org.systemaudit.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.systemaudit.model.FileDetails;
import org.systemaudit.model.EnumFileFolderOperationStatus;

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
	public List<FileDetails> listFileDetailsByFileFilter(FileDetails objFileDetails) {
		Criteria criteria = getCurrentSession().createCriteria(FileDetails.class, "FileDetails")
				.setFetchMode("objDeviceInfo", FetchMode.JOIN).setFetchMode("objScheduleMaster", FetchMode.JOIN)
				.add(Restrictions.eq("objDeviceInfo.compId", objFileDetails.getObjDeviceInfo().getCompId()))
				.add(Restrictions.eq("objScheduleMaster.schId", objFileDetails.getObjScheduleMaster().getSchId()))
				.add(Restrictions.eq("objScheduleMaster.schStatus", "S"));

		if (objFileDetails.getFileName() != null && !objFileDetails.getFileName().isEmpty())
			criteria.add(Restrictions.ilike("fileName", objFileDetails.getFileName(), MatchMode.ANYWHERE));

		if (objFileDetails.getFileDrive() != null && !objFileDetails.getFileDrive().isEmpty())
			criteria.add(Restrictions.ilike("fileDrive", objFileDetails.getFileDrive(), MatchMode.ANYWHERE));

		if (objFileDetails.getFileFullPath() != null && !objFileDetails.getFileFullPath().isEmpty())
			criteria.add(Restrictions.ilike("fileFullPath",
					objFileDetails.getFileFullPath().replaceAll("\\\\", "\\\\\\\\"), MatchMode.ANYWHERE));

		if (objFileDetails.getFileExtension() != null && !objFileDetails.getFileExtension().isEmpty())
			criteria.add(Restrictions.ilike("fileExtension", objFileDetails.getFileExtension(), MatchMode.ANYWHERE));

		criteria.setProjection(Projections.max("schId"));
		return criteria.list();

	}

	@SuppressWarnings("unchecked")
	public List<FileDetails> getSuspiciousFileDetailsByDeviceInfoIdAndStatus(int paramIntDeviceInfoId,
			EnumFileFolderOperationStatus paramEnumFileFolderOperationStatus) {
		return getCurrentSession()
				.createQuery("from FileDetails where objDeviceInfo.compId= :compId and fileStatus= :fileStatus")
				.setParameter("compId", paramIntDeviceInfoId)
				.setParameter("fileStatus", paramEnumFileFolderOperationStatus).list();
	}

	public void removeFileDetails(int paramIntId) {
		FileDetails ed = (FileDetails) getCurrentSession().load(FileDetails.class, new Integer(paramIntId));
		if (ed != null) {
			getCurrentSession().delete(ed);
		}
	}

	public void removeFileDetailsByDeviceInfoId(int paramIntDeviceInfoId) {
		Query query = getCurrentSession().createQuery("delete FileDetails where objDeviceInfo.compId= :compId");
		query.setInteger("compId", paramIntDeviceInfoId);
		query.executeUpdate();
	}
}
