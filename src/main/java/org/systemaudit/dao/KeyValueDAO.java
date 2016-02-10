package org.systemaudit.dao;

import org.systemaudit.model.KeyValue;


public abstract interface KeyValueDAO
  extends GenericDAO<KeyValue, Integer>
{
	public abstract KeyValue getKeyValueByKey(String paramStrKey);  
}
