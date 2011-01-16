package com.infoservice.global.service.impl;

import java.io.Serializable;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infoservice.global.dao.ICommonDao;
import com.infoservice.global.service.ICommonService;

@Service("commonService")
public class CommonService <T, PK extends Serializable> implements ICommonService <T, PK> {
	@Autowired
	private ICommonDao<T,PK> commonDao;
	

	public void save(T entity) {
		// TODO Auto-generated method stub
		commonDao.save(entity);
	}

	public void update(T entity) {
		// TODO Auto-generated method stub
		commonDao.update(entity);
	}

	public int updateByHQL(String hql, Object... values) {
		// TODO Auto-generated method stub
		return commonDao.updateByHQL(hql, values);
	} 
	
	public void delete(T entity) {
		// TODO Auto-generated method stub
		commonDao.delete(entity);
	}

	public void delete(PK id) {
		// TODO Auto-generated method stub
		commonDao.delete(id);
	}
	

	public int countByCriteria(DetachedCriteria dc) {
		// TODO Auto-generated method stub
		return commonDao.countByCriteria(dc);
	}

	

	public T get(PK id) {
		// TODO Auto-generated method stub
		return commonDao.get(id);
	}

	public List<T> getAll() {
		// TODO Auto-generated method stub
		return commonDao.getAll();
	}

	public List getListByCriteria(DetachedCriteria dc, int firstResult,
			int pageSize) {
		// TODO Auto-generated method stub
		return commonDao.getListByCriteria(dc, firstResult, pageSize);
	}

	public List getListByDetachedCriteria(DetachedCriteria dc) {
		// TODO Auto-generated method stub
		return commonDao.getListByDetachedCriteria(dc);
	}

	public void insert(T entity) {
		// TODO Auto-generated method stub
		commonDao.insert(entity);
	}

	public List queryByHQL(String hql, Object... values) {
		// TODO Auto-generated method stub
		return commonDao.queryByHQL(hql, values);
	}

	public List queryByHQL(String hql, int firstResult, int pageSize,
			Object... values) {
		// TODO Auto-generated method stub
		return commonDao.queryByHQL(hql, firstResult, pageSize, values);
	}

	public Object queryUniqueByDetachedCriteria(DetachedCriteria dc) {
		// TODO Auto-generated method stub
		return commonDao.queryUniqueByDetachedCriteria(dc);
	}

	public Object queryUniqueByHQL(String hql, Object... values) {
		// TODO Auto-generated method stub
		return commonDao.queryUniqueByHQL(hql, values);
	}

}
