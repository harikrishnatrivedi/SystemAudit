package org.systemaudit.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.systemaudit.model.ScheduleMaster;
import org.systemaudit.model.EnumScheduleStatus;

@Repository("ScheduleMasterDAOImpl")
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

	public ScheduleMaster getScheduleMasterByDeviceComputerId(int paramIntComputerId) {
		
		Criteria criteria=getCurrentSession().createCriteria(ScheduleMaster.class).setFetchMode("objDeviceInfo", FetchMode.JOIN);
		criteria.add(Restrictions.eq("objDeviceInfo.id", paramIntComputerId));
		criteria.add(Restrictions.eq("schStatus", EnumScheduleStatus.PENDING));
		return (ScheduleMaster)criteria.uniqueResult();
		/*criteria.setProjection(
				Projections.projectionList()
				.add(Projections.groupProperty("schId").as("schId"))
				.add(Projections.groupProperty("schRunDateTime").as("schRunDateTime"))
				.add(Projections.groupProperty("schCreatedDate").as("schCreatedDate"))
				.add(Projections.groupProperty("schCreatedBy").as("schCreatedBy"))
				.add(Projections.groupProperty("objDeviceInfo").as("objDeviceInfo"))
				.add(Projections.groupProperty("objDeviceGroup").as("objDeviceGroup"))
				.add(Projections.groupProperty("schStatus").as("schStatus"))
		  		.add(Projections.max("schId"))
				);
		return (ScheduleMaster)criteria.setResultTransformer(Transformers.aliasToBean(ScheduleMaster.class)).uniqueResult();*/
/*		return (ScheduleMaster) getCurrentSession().createQuery("from ScheduleMaster where objDeviceInfo = :compId")
				.setParameter("compId", paramIntComputerId)
				.uniqueResult();*/
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
