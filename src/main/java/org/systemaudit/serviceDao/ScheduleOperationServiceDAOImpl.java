package org.systemaudit.serviceDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.systemaudit.dao.GenericDAOImpl;
import org.systemaudit.dao.ScheduleMasterDAO;
import org.systemaudit.model.ScheduleMaster;

@Service
public class ScheduleOperationServiceDAOImpl
  extends GenericDAOImpl<Object, Integer>
  implements ScheduleOperationServiceDAO
{
	  @Autowired
	  private ScheduleMasterDAO objScheduleMasterDAO;
	  
	  
  public void saveAutoRunSchedule() throws Exception
  {
	  try {
		  
	  }catch(Exception ex){
		  throw ex;
	  }
  }
}
