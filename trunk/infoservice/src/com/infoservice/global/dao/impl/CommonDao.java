package com.infoservice.global.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.metadata.ClassMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.infoservice.global.dao.ICommonDao;
import com.infoservice.util.ObjectUtil;
import com.infoservice.util.ReflectionUtils;

/**
 * 封装Hibernate原生API的CRUD范型基类. 可在Service层直接使用,也可以扩展范型DAO子类使用.
 * 参考Spring2.5自带的Petlinc例子,取消了HibernateTemplate,直接使用Hibernate原生API.
 * @param <T> DAO操作的对象类型
 * @param <PK> 主键类型
 */
@SuppressWarnings("unchecked")
@Repository("commonDao")
public class CommonDao<T, PK extends Serializable> implements ICommonDao<T, PK>
{

    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected SessionFactory sessionFactory;

    protected Class<T> entityClass;

    /**
     * 用于扩展的DAO子类使用的构造函数. 通过子类的范型定义取得对象类型Class. eg. public class UserDao extends
     * SimpleHibernateDao<User, Long>
     */
    public CommonDao()
    {
        this.entityClass = ReflectionUtils.getSuperClassGenricType(getClass());
    }

    /**
     * 用于Service层直接使用SimpleHibernateDAO的构造函数. eg. SimpleHibernateDao<User,
     * Long> userDao = new SimpleHibernateDao<User, Long>(sessionFactory,
     * User.class);
     */
    public CommonDao(final SessionFactory sessionFactory,
            final Class<T> entityClass)
    {
        this.sessionFactory = sessionFactory;
        this.entityClass = entityClass;
    }

    public SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }

    /**
     * 采用@Autowired按类型注入SessionFactory,当有多个SesionFactory的时候Override本函数.
     * 
     * @param sessionFactory
     */
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession()
    {
        return sessionFactory.getCurrentSession();
    }  
    

    /**
     * 保存新增或修改的具体对象.
     */
    public void save(final T entity)
    {
        Assert.notNull(entity);
        getSession().saveOrUpdate(entity);
        logger.debug("save entity: {}", entity);
    }

    /**
     * 保存新增对象.
     */
    public void insert(final T entity)
    {
        Assert.notNull(entity);
        getSession().save(entity);
        logger.debug("insert entity: {}", entity);
    }
 
    /**
     * 修改对象.
     */
    public void update(final T entity)
    {
        Assert.notNull(entity);
        getSession().update(entity);
        logger.debug("insert entity: {}", entity);
    }
    
    
    /**
     * 删除对象.
     * 
     * @param entity 对象必须是session中的对象或含id属性的transient对象.
     */
    public void delete(final T entity)
    {
        Assert.notNull(entity);
        getSession().delete(entity);
        logger.debug("delete entity: {}", entity);
    }

    /**
     * 按id删除对象.
     */
    public void delete(final PK id)
    {
        Assert.notNull(id);
        delete(get(id));
        logger.debug("delete entity {},id is {}", entityClass.getSimpleName(),
                id);
    }
  
    public int updateByHQL(String hql, final Object... values)
    {
        
        Query query = createQuery(hql,values);
        return query.executeUpdate();
    }
    

    /**
     * 按id获取对象.
     */
    public T get(final PK id)
    {
        Assert.notNull(id);
        return (T) getSession().get(entityClass, id);
    }

    /**
     * 获取全部对象.
     */
    public List<T> getAll()
    {
        Criteria c=getSession().createCriteria(entityClass);
        return c.list();
    }


    /**
     * 按HQL查询对象列表.
     * 
     * @param values 数量可变的参数
     */
    public List queryByHQL(final String hql, final Object... values)
    {
        return createQuery(hql, values).list();
    }
    
    
    /**
     * 按HQL查询对象列表.
     * 
     * @param values 数量可变的参数 
     * @param firstResult  开始记录
     * @param pageSize  分页量
     */
    public List queryByHQL(final String hql,int firstResult,int pageSize, final Object... values )
    {
        Query c= createQuery(hql, values);
        c.setFirstResult(firstResult);
        c.setMaxResults(pageSize);
        return c.list();
    }

    /**
     * 按HQL查询唯一对象.
     */
    public Object queryUniqueByHQL(final String hql, final Object... values)
    {
        return createQuery(hql, values).uniqueResult();
    }
 
    
    /**
     * 按HQL查询唯一对象.
     */
    public Object queryUniqueByDetachedCriteria(DetachedCriteria dc)
    {
        return createCriteria(dc).uniqueResult();        
    } 

    public int countByCriteria(final DetachedCriteria dc)
    {        
        DetachedCriteria tdc=(DetachedCriteria) ObjectUtil.deepCopy(dc);
        tdc.setProjection(Projections.rowCount());
        return (Integer)queryUniqueByDetachedCriteria(tdc);    

    }


    public List getListByDetachedCriteria(final DetachedCriteria dc)
    {
        return createCriteria(dc).list();
    }


    public List getListByCriteria(final DetachedCriteria dc,final int firstResult, final int pageSize)
    {        
        Criteria c= createCriteria(dc);
        c.setFirstResult(firstResult);
        c.setMaxResults(pageSize);
        return c.list();
    }
    
    
    /**
     * 取得对象的主键名.
     */
    public String getIdName()
    {
        ClassMetadata meta = getSessionFactory().getClassMetadata(entityClass);
        Assert.notNull(meta, "Class " + entityClass.getSimpleName()
                + " not define in HibernateSessionFactory.");
        return meta.getIdentifierPropertyName();
    }
    
    /**
     * 
      * 获得无会话的Criteria
     */
    
    protected  DetachedCriteria createDetachedCriteria ()
    {
        return DetachedCriteria.forClass(entityClass);
    }
    
    
    
    protected  Criteria createCriteria(final DetachedCriteria dc)
    {
        return dc.getExecutableCriteria(getSession());       
    }
    
    
    /**
     * 根据查询HQL与参数列表创建Query对象. 返回对象类型不是Entity时可用此函数灵活查询.
     * 
     * @param values 数量可变的参数
     */
    protected Query createQuery(final String queryString, final Object... values)
    {
        Assert.hasText(queryString);
        Query query = getSession().createQuery(queryString);
        if (values != null)
        {
            for (int i = 0; i < values.length; i++)
            {
                query.setParameter(i, values[i]);
            }
        }
        return query;
    }
    
}