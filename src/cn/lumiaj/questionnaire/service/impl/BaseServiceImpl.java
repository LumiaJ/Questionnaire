package cn.lumiaj.questionnaire.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.lumiaj.questionnaire.dao.BaseDao;
import cn.lumiaj.questionnaire.service.BaseService;

@Service
@Transactional
public abstract class BaseServiceImpl<T> implements BaseService<T>{
	@Autowired
	private BaseDao<T> dao;
	
	@Override
	public void saveEntity(T t) {
		dao.saveEntity(t);
	}

	@Override
	public void saveOrUpdateEntity(T t) {
		dao.saveOrUpdateEntity(t);
	}

	@Override
	public void updateEntity(T t) {
		dao.updateEntity(t);
	}

	@Override
	public void deleteEntity(T t) {
		dao.deleteEntity(t);
	}

	@Override
	public void batchEntityByHQL(String hql, Object... objs) {
		dao.batchEntityByHQL(hql, objs);
	}

	@Override
	public T loadEntity(Integer id) {
		return dao.loadEntity(id);
	}

	@Override
	public T getEntity(Integer id) {
		return dao.getEntity(id);
	}

	@Override
	public List<T> findEntityByHQL(String hql, Object... objs) {
		return dao.findEntityByHQL(hql, objs);
	}

	@Override
	public Object uniqueResult(String hql, Object... objs) {
		return dao.uniqueResult(hql, objs);
	}
	
}
