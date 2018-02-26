package cn.lumiaj.questionnaire.dao;

import java.util.List;

public interface BaseDao<T> {
	public void saveEntity(T t);
	public void saveOrUpdateEntity(T t);
	public void updateEntity(T t);
	public void deleteEntity(T t);
	public void batchEntityByHQL(String hql, Object ... objs);
	
	public T loadEntity(Integer id);
	public T getEntity(Integer id);
	public List<T> findEntityByHQL(String hql, Object ... objs);
	
	public Object uniqueResult(String hql, Object ... objs);
}
