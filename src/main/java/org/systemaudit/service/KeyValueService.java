package org.systemaudit.service;

import java.util.List;

import org.systemaudit.model.FileDetails;
import org.systemaudit.model.EnumFileFolderOperationStatus;
import org.systemaudit.model.FolderOperationRequest;
import org.systemaudit.model.KeyValue;

public abstract interface KeyValueService
{
	public abstract KeyValue getKeyValueByKey(String paramStrKey);
	
	public abstract List<KeyValue> listKeyValue();
}
