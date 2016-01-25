package org.systemaudit.service;

import java.util.List;

import org.systemaudit.model.DeviceInfo;

public abstract interface DeviceInfoService
{
  public abstract void addDeviceInfo(DeviceInfo paramObjDeviceInfo) throws Exception;
  
  public abstract void updateDeviceInfo(DeviceInfo paramObjDeviceInfo);
  
  public abstract List<DeviceInfo> listDeviceInfo();
  
  public abstract DeviceInfo getDeviceInfoByDeviceComputerName(String paramStringComputerName);
  
  public abstract DeviceInfo getDeviceInfoById(int paramIntDeviceInfoId);
  
  public abstract void removeDeviceInfo(int paramIntDeviceInfoId);
  
  
}
