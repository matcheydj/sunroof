package com.infoservice.global.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class BaseAction extends ActionSupport implements ServletRequestAware,
		ServletResponseAware {

	public static Logger log = Logger.getLogger(BaseAction.class);
	public HttpServletRequest request;
	public HttpServletResponse response;
	public String callback;
	// 默认返回error
	public String res = ERROR;
	// 提示信息，页面消息等
	public String message;
	// 错误信息
	public String errorMessage;
	// 访问前页
	public String oldpage;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public String getOldpage() {
		return oldpage;
	}

	public void setOldpage(String oldpage) {
		this.oldpage = oldpage;
	}

	public String getRes() {
		return res;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getCallback() {
		return callback;
	}

	public void setCallback(String callback) {
		this.callback = callback;
	}

}
