package org.systemaudit.service;

import java.util.List;

import org.systemaudit.model.ScheduleMaster;

public abstract interface ScheduleMasterService
{
  public abstract void addScheduleMaster(ScheduleMaster paramObjScheduleMaster);
  
  public abstract void updateScheduleMaster(ScheduleMaster paramObjScheduleMaster);
  
  public abstract List<ScheduleMaster> listScheduleMaster();
  
  public abstract ScheduleMaster getScheduleMasterByDeviceComputerName(String paramStringComputerName);
  
  public abstract ScheduleMaster getScheduleMasterById(int paramIntScheduleMasterId);
  
  public abstract void removeScheduleMaster(int paramIntScheduleMasterId);
  
  
}
