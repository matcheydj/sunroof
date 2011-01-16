package com.infoservice.oa.service;

import com.infoservice.domain.User;

public interface IUserService{
	
	
	 	public void save(final User entity) throws Exception ;

	    public void update(final User entity) throws Exception ;

	    public void delete(final User entity) throws Exception ;

	    public void delete(final Integer id) throws Exception ;

	   
}
