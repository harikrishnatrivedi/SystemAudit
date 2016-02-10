package org.systemaudit.dao;

import org.springframework.stereotype.Repository;
import org.systemaudit.model.KeyValue;

@Repository("KeyValueDAOImpl")
public class KeyValueDAOImpl extends GenericDAOImpl<KeyValue, Integer> implements KeyValueDAO {
	
	public KeyValue getKeyValueByKey(String paramStrKey) {
		return (KeyValue) getCurrentSession().load(KeyValue.class, new String(paramStrKey));
	}

}
