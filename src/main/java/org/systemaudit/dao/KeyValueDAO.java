package org.systemaudit.dao;

import java.util.List;

import org.systemaudit.model.KeyValue;


public abstract interface KeyValueDAO
  extends GenericDAO<KeyValue, Integer>
{
	public abstract KeyValue getKeyValueByKey(String paramStrKey);
	
	public abstract List<KeyValue> listKeyValue();
}
