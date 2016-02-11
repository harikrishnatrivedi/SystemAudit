package org.systemaudit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.systemaudit.dao.FileDetailsDAO;
import org.systemaudit.model.FileDetails;
import org.systemaudit.model.EnumFileFolderOperationStatus;;

@Service("FileDetailsServiceImpl")
@Transactional
public class FileDetailsServiceImpl implements FileDetailsService {
	
	@Autowired
	private FileDetailsDAO fileDetailsDAO;

	public void addFileDetails(FileDetails paramObjFileDetails) {
		this.fileDetailsDAO.addFileDetails(paramObjFileDetails);
	}

	public void updateFileDetails(FileDetails paramObjFileDetails) {
		this.fileDetailsDAO.updateFileDetails(paramObjFileDetails);
	}

	public List<FileDetails> listFileDetails() {
		return this.fileDetailsDAO.listFileDetails();
	}

	public List<FileDetails> listFileDetailsByDeviceInfoId(int paramIntDeviceInfoId){
		return this.fileDetailsDAO.listFileDetailsByDeviceInfoId(paramIntDeviceInfoId);
	}
	
	public List<FileDetails> listFileDetailsByFileFilter(FileDetails objFileDetails){
		return this.fileDetailsDAO.listFileDetailsByFileFilter(objFileDetails);
	}
	
	public FileDetails getFileDetailsById(int paramIntFileDetailsId) {
		return this.fileDetailsDAO.getFileDetailsById(paramIntFileDetailsId);
	}

	public void removeFileDetails(int paramIntFileDetailsId) {
		this.fileDetailsDAO.removeFileDetails(paramIntFileDetailsId);
	}
	
	public void removeFileDetailsByDeviceInfoId(int paramIntDeviceInfoId) {
		this.fileDetailsDAO.removeFileDetailsByDeviceInfoId(paramIntDeviceInfoId);
	}
	
	public List<FileDetails> getSuspiciousFileDetailsByDeviceInfoIdAndStatus(int paramIntDeviceInfoCompId,EnumFileFolderOperationStatus paramEnumFileFolderOperationStatus){
		return this.fileDetailsDAO.getSuspiciousFileDetailsByDeviceInfoIdAndStatus(paramIntDeviceInfoCompId, paramEnumFileFolderOperationStatus);
	}
	
}
