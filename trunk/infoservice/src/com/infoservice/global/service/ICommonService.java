package com.infoservice.global.service;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public interface ICommonService <T, PK extends Serializable>{
	
	
	 	public void save(final T entity) throws Exception ;

	    public void insert(final T entity) throws Exception ;

	    public void update(final T entity) throws Exception ;

	    public void delete(final T entity) throws Exception ;

	    public void delete(final PK id) throws Exception ;

	    public int updateByHQL(String hql, final Object... values) throws Exception ;

	    public T get(final PK id) throws Exception ;

	    public List<T> getAll() throws Exception ;

	    @SuppressWarnings("unchecked")
	    public List queryByHQL(final String hql, final Object... values);

	    @SuppressWarnings("unchecked")
	    public List queryByHQL(final String hql, int firstResult, int pageSize, final Object... values);

	    public Object queryUniqueByHQL(final String hql, final Object... values);

	    public Object queryUniqueByDetachedCriteria(final DetachedCriteria dc);

	    public int countByCriteria(final DetachedCriteria dc);

	    @SuppressWarnings("unchecked")
	    public List  getListByDetachedCriteria(final DetachedCriteria dc);

	    @SuppressWarnings("unchecked")
	    public List  getListByCriteria(final DetachedCriteria dc,final int  firstResult, final int pageSize);

}
