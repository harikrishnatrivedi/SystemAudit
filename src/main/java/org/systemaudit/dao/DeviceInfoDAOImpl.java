package org.systemaudit.dao;

import java.util.List;

import org.springframework.stereotype.Service;
import org.systemaudit.model.DeviceInfo;

@Service
public class DeviceInfoDAOImpl extends GenericDAOImpl<DeviceInfo, Integer> implements DeviceInfoDAO {
	public void addDeviceInfo(DeviceInfo paramObjDeviceInfo) {
		getCurrentSession().persist(paramObjDeviceInfo);
	}

	public void updateDeviceInfo(DeviceInfo paramObjDeviceInfo) {
		getCurrentSession().update(paramObjDeviceInfo);
	}

	@SuppressWarnings("unchecked")
	public List<DeviceInfo> listDeviceInfo() {
		return getCurrentSession().createQuery("from DeviceInfo").list();
	}

	public DeviceInfo getDeviceInfoByDeviceComputerName(String paramStringComputerName) {
		return (DeviceInfo) getCurrentSession().createQuery("from DeviceInfo where compName = :compName")
				.setParameter("compName", paramStringComputerName).uniqueResult();
	}

	public DeviceInfo getDeviceInfoById(int paramIntId) {
		return (DeviceInfo) getCurrentSession().load(DeviceInfo.class, new Integer(paramIntId));
	}

	public void removeDeviceInfo(int paramIntId) {
		DeviceInfo ed = (DeviceInfo) getCurrentSession().load(DeviceInfo.class, new Integer(paramIntId));
		if (ed != null) {
			getCurrentSession().delete(ed);
		}
	}
}
