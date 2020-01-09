package com.bio.common.service;

import org.apache.shiro.subject.Subject;

import com.bio.sys.domain.UserDO;

/**
 *  Context 相关的 Service
 * 
 * 
 * @author chenx
 *
 */
public interface ContextService {

	public UserDO getCurrentLoginUser(Subject subject);

}
