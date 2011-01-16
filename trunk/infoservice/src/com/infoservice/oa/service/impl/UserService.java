package com.infoservice.oa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infoservice.domain.User;
import com.infoservice.global.dao.ICommonDao;
import com.infoservice.oa.service.IUserService;
@Service("userService")
public class UserService implements IUserService{

	@Autowired
	private ICommonDao<User,Integer> commonDao;
	
	
	public void delete(User entity) throws Exception {
		// TODO Auto-generated method stub
		commonDao.delete(entity);
	}

	public void delete(Integer id) throws Exception {
		// TODO Auto-generated method stub
		commonDao.delete(id);
	}

	public void save(User entity) throws Exception {
		// TODO Auto-generated method stub
		commonDao.save(entity);
	}

	public void update(User entity) throws Exception {
		// TODO Auto-generated method stub
		commonDao.update(entity);
	}

}
