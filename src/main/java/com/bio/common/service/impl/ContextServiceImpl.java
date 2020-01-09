package com.bio.common.service.impl;


import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bio.common.service.ContextService;
import com.bio.sys.domain.UserDO;
import com.bio.sys.service.UserService;


@Service
public class ContextServiceImpl  implements ContextService{
	
	@Autowired
	private UserService  userService;

	@Override
	public UserDO getCurrentLoginUser(Subject subject) {
		UserDO userDO =  (UserDO)subject.getPrincipal();
		UserDO userDO2 = userService.selectById(userDO.getId());
		return userDO2;
	}

}
