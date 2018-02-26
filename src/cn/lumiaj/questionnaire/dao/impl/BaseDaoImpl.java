package cn.lumiaj.questionnaire.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import cn.lumiaj.questionnaire.dao.BaseDao;

@SuppressWarnings({ "rawtypes", "unchecked" })
public abstract class BaseDaoImpl<T> implements BaseDao<T>{
	
	@Autowired
	private SessionFactory sf;
	
	private Class<T> clazz;
	
	public BaseDaoImpl() {
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		this.clazz = (Class<T>) type.getActualTypeArguments()[0];
	}
	
	private Session getSession() {
		return sf.getCurrentSession();
	}
	
	@Override
	public void saveEntity(T t) {
		getSession().save(t);
	}

	@Override
	public void saveOrUpdateEntity(T t) {
		getSession().saveOrUpdate(t);
	}

	@Override
	public void updateEntity(T t) {
		getSession().update(t);
	}

	@Override
	public void deleteEntity(T t) {
		getSession().delete(t);
	}

	@Override
	public void batchEntityByHQL(String hql, Object... objs) {
		Query query = getSession().createQuery(hql);
		for(int i=0;i<objs.length;i++) {
			query.setParameter(i, objs[i]);
		}
		query.executeUpdate();
	}

	@Override
	public T loadEntity(Integer id) {
		return getSession().load(clazz, id);
	}

	@Override
	public T getEntity(Integer id) {
		return getSession().get(clazz, id);
	}

	@Override
	public List<T> findEntityByHQL(String hql, Object... objs) {
		Query query = getSession().createQuery(hql);
		for(int i=0;i<objs.length;i++) {
			query.setParameter(i, objs[i]);
		}
		return query.list();
	}

	@Override
	public Object uniqueResult(String hql, Object... objs) {
		Query query = getSession().createQuery(hql);
		for(int i=0;i<objs.length;i++) {
			query.setParameter(i, objs[i]);
		}
		return query.uniqueResult();
	}

}
