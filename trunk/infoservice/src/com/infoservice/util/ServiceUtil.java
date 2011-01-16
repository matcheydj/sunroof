package com.infoservice.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
/**
 * 
 * 从外部获得 spring 初始化容器的对象
 * 从外部获取strut2 的 session 和　web 容器
 * @author sunzj
 */
public abstract class ServiceUtil
{

    public static final ApplicationContext ctx = getWebApplicationContext();

    /**
     * 
      * 函数介绍：获得 spring 初始化容器
      * 参数：
      * 返回值：
     */
    private static WebApplicationContext getWebApplicationContext()
    {
        return WebApplicationContextUtils
                .getWebApplicationContext(getServletContext());
    }
    /**
     * 
      * 函数介绍：获得web容器
      * 参数：
      * 返回值：
     */
    private static ServletContext getServletContext()
    {
        return ServletActionContext.getServletContext();
    }

    public static Object getBean(String beanName)
    {

        return ctx.getBean(beanName);
    }

    public static Object getSessionValue(String name)
    {
        HttpSession session = ServletActionContext.getRequest().getSession();
        return session.getAttribute(name);
    }

    public static void setSessionValue(String key, Object value)
    {
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.setAttribute(key, value);
    }

    public static String getSessionId()
    {
        HttpSession session = ServletActionContext.getRequest().getSession();
        return session.getId();
    }

    public static String getRequestURL()
    {
        HttpServletRequest request = ServletActionContext.getRequest();
        return request.getRequestURL().toString();
    }

}
