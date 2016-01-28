package org.systemaudit.service;

import org.systemaudit.model.DeviceInfo;
import org.systemaudit.serviceDao.ScheduleOperationServiceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;;

@Service("ScheduleOperationServiceImpl")
@Transactional
public class ScheduleOperationServiceImpl implements ScheduleOperationService {
	
	@Autowired
	private ScheduleOperationServiceDAO objScheduleOperationServiceDAO;

	public void saveAutoRunSchedule(DeviceInfo paramObjDeviceInfo) {
		
	}
}
