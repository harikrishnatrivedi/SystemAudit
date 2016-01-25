package org.systemaudit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.systemaudit.dao.ScheduleMasterDAO;
import org.systemaudit.model.ScheduleMaster;

@Service

@Transactional
public class ScheduleMasterServiceImpl implements ScheduleMasterService {
	
	@Autowired
	private ScheduleMasterDAO scheduleMasterDAO;

	public void addScheduleMaster(ScheduleMaster paramObjScheduleMaster) {
		this.scheduleMasterDAO.addScheduleMaster(paramObjScheduleMaster);
	}

	public void updateScheduleMaster(ScheduleMaster paramObjScheduleMaster) {
		this.scheduleMasterDAO.updateScheduleMaster(paramObjScheduleMaster);
	}

	public List<ScheduleMaster> listScheduleMaster() {
		return this.scheduleMasterDAO.listScheduleMaster();
	}

	public ScheduleMaster getScheduleMasterByDeviceComputerName(String paramStringComputerName){
		return this.scheduleMasterDAO.getScheduleMasterByDeviceComputerName(paramStringComputerName);
	}
	
	public ScheduleMaster getScheduleMasterById(int paramIntScheduleMasterId) {
		return this.scheduleMasterDAO.getScheduleMasterById(paramIntScheduleMasterId);
	}

	public void removeScheduleMaster(int paramIntScheduleMasterId) {
		this.scheduleMasterDAO.removeScheduleMaster(paramIntScheduleMasterId);
	}
	
}
