package org.systemaudit.dao;

import java.util.List;

import org.springframework.stereotype.Service;
import org.systemaudit.model.ScheduleMaster;

@Service
public class ScheduleMasterDAOImpl extends GenericDAOImpl<ScheduleMaster, Integer> implements ScheduleMasterDAO {
	public void addScheduleMaster(ScheduleMaster paramObjScheduleMaster) {
		getCurrentSession().persist(paramObjScheduleMaster);
	}

	public void updateScheduleMaster(ScheduleMaster paramObjScheduleMaster) {
		getCurrentSession().update(paramObjScheduleMaster);
	}

	@SuppressWarnings("unchecked")
	public List<ScheduleMaster> listScheduleMaster() {
		return getCurrentSession().createQuery("from ScheduleMaster").list();
	}

	public ScheduleMaster getScheduleMasterByDeviceComputerName(String paramStringComputerName) {
		return (ScheduleMaster) getCurrentSession()
				.createQuery("from ScheduleMaster where objDeviceInfo.compName = :compName and schId=max(schId)")
				.setParameter("compName", paramStringComputerName).uniqueResult();
	}

	public ScheduleMaster getScheduleMasterById(int paramIntId) {
		return (ScheduleMaster) getCurrentSession().load(ScheduleMaster.class, new Integer(paramIntId));
	}

	public void removeScheduleMaster(int paramIntId) {
		ScheduleMaster ed = (ScheduleMaster) getCurrentSession().load(ScheduleMaster.class, new Integer(paramIntId));
		if (ed != null) {
			getCurrentSession().delete(ed);
		}
	}
}
