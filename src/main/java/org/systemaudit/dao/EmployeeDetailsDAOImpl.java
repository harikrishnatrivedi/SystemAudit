package org.systemaudit.dao;

import java.util.List;

import org.springframework.stereotype.Service;
import org.systemaudit.model.EmployeeDetails;

@Service
public class EmployeeDetailsDAOImpl
  extends GenericDAOImpl<EmployeeDetails, Integer>
  implements EmployeeDetailsDAO
{
  public void addEmployeeDetails(EmployeeDetails paramObjEmployeeDetails)
  {
	  getCurrentSession().persist(paramObjEmployeeDetails);
  }
  
  public void updateEmployeeDetails(EmployeeDetails paramObjEmployeeDetails)
  {
	  getCurrentSession().update(paramObjEmployeeDetails);
  }
  
  @SuppressWarnings("unchecked")
  public List<EmployeeDetails> listEmployeeDetails()
  {
    return getCurrentSession().createQuery("from EmployeeDetails").list();
  }
  
  public EmployeeDetails getEmployeeDetailsById(int paramIntId)
  {
	  return (EmployeeDetails)getCurrentSession().load(EmployeeDetails.class, new Integer(paramIntId));
  }
  
  public void removeEmployeeDetails(int paramIntId)
  {
	EmployeeDetails ed = (EmployeeDetails)getCurrentSession().load(EmployeeDetails.class, new Integer(paramIntId));
    if (ed != null) {
    	getCurrentSession().delete(ed);
    }
  }
}
