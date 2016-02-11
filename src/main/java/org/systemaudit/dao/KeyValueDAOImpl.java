package org.systemaudit.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.systemaudit.model.KeyValue;

@Repository("KeyValueDAOImpl")
public class KeyValueDAOImpl extends GenericDAOImpl<KeyValue, Integer> implements KeyValueDAO {
	
	public KeyValue getKeyValueByKey(String paramStrKey) {
		//return (KeyValue) getCurrentSession().load(KeyValue.class, new String(paramStrKey));
		return (KeyValue) getCurrentSession().createCriteria(KeyValue.class).add(Restrictions.eq("kvalId", paramStrKey)).uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<KeyValue> listKeyValue() {
		return getCurrentSession().createCriteria(KeyValue.class).list();
	}

}
