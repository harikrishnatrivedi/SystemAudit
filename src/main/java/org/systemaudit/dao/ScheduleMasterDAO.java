package org.systemaudit.dao;

import java.util.List;

import org.systemaudit.model.ScheduleMaster;


public abstract interface ScheduleMasterDAO
  extends GenericDAO<ScheduleMaster, Integer>
{
  public abstract void addScheduleMaster(ScheduleMaster paramObjScheduleMaster);
  
  public abstract void updateScheduleMaster(ScheduleMaster paramObjScheduleMaster);
  
  public abstract List<ScheduleMaster> listScheduleMaster();

  public abstract ScheduleMaster getScheduleMasterByDeviceComputerName(String paramStringComputerName);
  
  public abstract ScheduleMaster getScheduleMasterById(int paramIntId);
  
  public abstract void removeScheduleMaster(int paramIntId);
  
}
