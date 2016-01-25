package org.systemaudit.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.mapping.PersistentClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@SuppressWarnings("unused")
public class GenericDAOImpl<E, PK extends Serializable> implements GenericDAO<E, PK> {
	private final Class<? extends E> persistentClass;

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory objSessionFactory;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public GenericDAOImpl() {
		this.persistentClass = ((Class) ((java.lang.reflect.ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[1]);
	}

	/*
	 * protected Session getSession() { System.out.println(
	 * "Session created?? 33 :" + this.sessionFactory); return
	 * this.sessionFactory.openSession(); }
	 */
	protected Session getCurrentSession() {
		System.out.println("current Primary Session :" + (this.objSessionFactory.getCurrentSession()).hashCode());
		return this.objSessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public E getByKey(PK key, Session session) {
		return (E) session.get(this.persistentClass, key);
	}

	@SuppressWarnings("unchecked")
	public E find(PK key, Session session) {
		return (E) session.get(this.persistentClass, key);
	}

	public void persist(E entity, Session session) {
		session.persist(entity);
	}

	public void add(E entity, Session session) {
		session.save(entity);
	}

	public void delete(E entity, Session session) {
		session.delete(entity);
	}

	public void remove(E entity, Session session) {
		session.delete(entity);
	}

	public void saveOrUpdate(E entity, Session session) {
		session.saveOrUpdate(entity);
	}

	public void update(E entity, Session session) {
		session.saveOrUpdate(entity);
	}

	protected Criteria createEntityCriteria(Session session) {
		return session.createCriteria(this.persistentClass);
	}

	@SuppressWarnings("unchecked")
	public List<E> getAll(Session session) {
		return session.createCriteria(this.persistentClass).list();
	}
}
