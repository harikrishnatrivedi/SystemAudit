package org.systemaudit.dao;

import java.util.List;

import org.systemaudit.model.DeviceInfo;


public abstract interface DeviceInfoDAO
  extends GenericDAO<DeviceInfo, Integer>
{
  public abstract void addDeviceInfo(DeviceInfo paramObjDeviceInfo);
  
  public abstract void updateDeviceInfo(DeviceInfo paramObjDeviceInfo);
  
  public abstract List<DeviceInfo> listDeviceInfo();

  public abstract DeviceInfo getDeviceInfoByDeviceComputerName(String paramStringComputerName);
  
  public abstract DeviceInfo getDeviceInfoById(int paramIntId);
  
  public abstract void removeDeviceInfo(int paramIntId);
  
}
