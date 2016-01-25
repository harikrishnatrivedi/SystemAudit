package org.systemaudit.dao;

import java.util.List;

import org.systemaudit.model.EmployeeDetails;

public abstract interface EmployeeDetailsDAO
  extends GenericDAO<EmployeeDetails, Integer>
{
  public abstract void addEmployeeDetails(EmployeeDetails paramObjEmployeeDetails);
  
  public abstract void updateEmployeeDetails(EmployeeDetails paramObjEmployeeDetails);
  
  public abstract List<EmployeeDetails> listEmployeeDetails();
  
  public abstract EmployeeDetails getEmployeeDetailsById(int paramIntId);
  
  public abstract void removeEmployeeDetails(int paramIntId);
  
}
