package com.infoservice.oa.action;


import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.infoservice.domain.User;
import com.infoservice.global.service.ICommonService;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
public class UsersAction extends ActionSupport {

	private static final long serialVersionUID = 5945601801103285355L;

	private User user;
	private List<User> list;
	private Integer id;
	
	@Autowired
	private ICommonService<User,Integer> commonService;

	
//	@Autowired
//	private IUserService userService;	
	
	@Action(value = "userAdd", results = { @Result(name = "success", location = "/success.jsp") })
	public String add() throws Exception {
		commonService.save(user);
		return "success";
	}

	@Action(value = "userList", results = { @Result(name = "success", location = "/list.jsp") })
	public String list() throws Exception {
		list = commonService.getAll();
		return "success";
	}

	@Action(value = "userDel", results = { @Result(name = "success", type = "redirect", location = "/userList.shtml") })
	public String delete() throws Exception {
		commonService.delete(id);
		return "success";
	}
	
	@Action(value = "userEdit", results = { @Result(name = "success", location = "/index.jsp") })
	public String edit() throws Exception {
		user=commonService.get(id);
		return "success";
	}
	

	@Action(value = "userUpdate", results = { @Result(name = "success", location = "/success.jsp") })
	public String update() throws Exception {
		commonService.update(user);
		return "success";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<User> getList() {
		return list;
	}

	public void setList(List<User> list) {
		this.list = list;
	}

}
