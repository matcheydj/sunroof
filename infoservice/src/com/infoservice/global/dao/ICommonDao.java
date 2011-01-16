package com.infoservice.global.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
/**
 * 定义一个参考协议，能够统领dao 和 manage 
 */
public interface ICommonDao<T, PK extends Serializable>
{

    public void save(final T entity);

    public void insert(final T entity);

    public void update(final T entity);

    public void delete(final T entity);

    public void delete(final PK id);

    public int updateByHQL(String hql, final Object... values);

    public T get(final PK id);

    public List<T> getAll();

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
