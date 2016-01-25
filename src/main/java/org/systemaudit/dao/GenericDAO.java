package org.systemaudit.dao;

import java.util.List;

import org.hibernate.Session;

public abstract interface GenericDAO<E, PK>
{
  public abstract void add(E paramE, Session session);
  
  public abstract void saveOrUpdate(E paramE, Session session);
  
  public abstract void update(E paramE, Session session);
  
  public abstract void remove(E paramE, Session session);
  
  public abstract E find(PK paramPK, Session session);
  
  public abstract List<E> getAll(Session session);
}
