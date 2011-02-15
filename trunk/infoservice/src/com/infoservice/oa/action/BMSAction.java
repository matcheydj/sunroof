package com.infoservice.oa.action;

import java.io.IOException;

import net.sf.json.JSONArray;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.infoservice.domain.LoginUser;
import com.infoservice.global.action.BaseAction;
import com.infoservice.util.Contants;
import com.infoservice.util.OnlineUser;
import com.infoservice.util.RequestUtil;

@Controller
@Scope("prototype")
public class BMSAction extends BaseAction {

	private static final long serialVersionUID = 620335253755314887L;

	private LoginUser user;
	private String jsonString;

	/**
	 * just for test session monitor will add after table is created
	 * 
	 * @return
	 * @throws IOException
	 */
	@Action(value = "adminLogin", results = { @Result(name = "success", location = "/success.jsp") })
	public String login() throws IOException {
		String userName = RequestUtil.getParam(request, "username", "");
		String password = RequestUtil.getParam(request, "password", "");
		response.reset();
		response.setCharacterEncoding("utf-8");
		if ("admin".equals(userName) && "admin".equals(password)) {
			response.getWriter().print("{success:'true',msg:'登录成功'}");
			if (null == user)
				user = new LoginUser();
			user.setUsername(userName);
			user.setPassword(password);
			request.getSession()
					.setAttribute(Contants.SESSION_USER_ADMIN, user);
			OnlineUser.getInstance().addUser(user, 1);
			return null;
		}
		response.getWriter().print("{success:'false',msg:'登录失败'}");
		return null;
	}

	
	/**
	 * getOnlineUserList
	 * @return
	 */
	@SuppressWarnings("static-access")
	@Action(value = "onlineUser", results = { @Result(name = "jsonStringPage", location = "/jsonString.jsp") })
	public String getOnlineUserList() {
		try {
			JSONArray jsonObject = JSONArray.fromObject(OnlineUser
					.getInstance().getTreeObject(1));
			this.jsonString = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
			this.jsonString = "[]";
		}
		return Contants.JSONSTRING_RESULT_NAME;
	}
	
	
	

	public LoginUser getUser() {
		return user;
	}

	public void setUser(LoginUser user) {
		this.user = user;
	}

	public String getJsonString() {
		return jsonString;
	}

	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}
}
