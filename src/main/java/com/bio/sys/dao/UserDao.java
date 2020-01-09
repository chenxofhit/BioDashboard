package com.bio.sys.dao;

import com.bio.common.base.BaseDao;
import com.bio.sys.domain.UserDO;

/**
 * <pre>
 * </pre>
 * <small> 2018年3月23日 | Aron</small>
 */
public interface UserDao extends BaseDao<UserDO> {
	
	Long[] listAllDept();
	
	
}
