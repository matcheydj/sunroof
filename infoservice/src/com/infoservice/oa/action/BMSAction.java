package com.infoservice.oa.action;

import java.io.IOException;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.infoservice.global.action.BaseAction;
import com.infoservice.util.RequestUtil;

@Controller
@Scope("prototype")
public class BMSAction extends BaseAction {

	private static final long serialVersionUID = 620335253755314887L;
	
	@Action(value = "adminLogin", results = { @Result(name = "success", location = "/success.jsp") })
	public String login() throws IOException {
		String userName = RequestUtil.getParam(request, "username", "");
		String password = RequestUtil.getParam(request, "password", "");
		response.reset();
		response.setCharacterEncoding("utf-8");
		if("admin".equals(userName)&&"admin".equals(password)){
			response.getWriter().print("{success:'true',msg:'登录成功'}");
			return null;
		}
		response.getWriter().print("{success:'false',msg:'登录失败'}");
		return null;
	}
}
