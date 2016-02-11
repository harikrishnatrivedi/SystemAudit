package org.systemaudit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.systemaudit.dao.KeyValueDAO;
import org.systemaudit.model.KeyValue;


@Service("KeyValueServiceImpl")
@Transactional
public class KeyValueServiceImpl implements KeyValueService {
	
	@Autowired
	private KeyValueDAO objKeyValueDAO;

	public KeyValue getKeyValueByKey(String paramStrKey) {
		return this.objKeyValueDAO.getKeyValueByKey(paramStrKey);
	}
	
	public List<KeyValue> listKeyValue(){
		return this.objKeyValueDAO.listKeyValue();
	}
}
