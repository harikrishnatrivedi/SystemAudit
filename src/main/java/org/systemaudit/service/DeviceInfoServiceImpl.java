package org.systemaudit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.systemaudit.dao.DeviceInfoDAO;
import org.systemaudit.model.DeviceInfo;;

@Service

@Transactional
public class DeviceInfoServiceImpl implements DeviceInfoService {
	
	@Autowired
	private DeviceInfoDAO deviceInfoDAO;

	public void addDeviceInfo(DeviceInfo paramObjDeviceInfo) throws Exception {
		try {
			this.deviceInfoDAO.addDeviceInfo(paramObjDeviceInfo);
		}catch(Exception ex){
			throw ex;
		}
	}

	public void updateDeviceInfo(DeviceInfo paramObjDeviceInfo) {
		this.deviceInfoDAO.updateDeviceInfo(paramObjDeviceInfo);
	}

	public List<DeviceInfo> listDeviceInfo() {
		return this.deviceInfoDAO.listDeviceInfo();
	}

	public DeviceInfo getDeviceInfoByDeviceComputerName(String paramStringComputerName){
		return this.deviceInfoDAO.getDeviceInfoByDeviceComputerName(paramStringComputerName);
	}
	
	public DeviceInfo getDeviceInfoById(int paramIntDeviceInfoId) {
		return this.deviceInfoDAO.getDeviceInfoById(paramIntDeviceInfoId);
	}

	public void removeDeviceInfo(int paramIntDeviceInfoId) {
		this.deviceInfoDAO.removeDeviceInfo(paramIntDeviceInfoId);
	}
	
}
