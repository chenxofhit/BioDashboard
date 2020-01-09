package com.bio.sys.dao;

import com.bio.common.base.BaseDao;
import com.bio.sys.domain.PlaceholderDO;

/**
 * 
 * <pre>
 * 
 * </pre>
 * <small> 2019-12-17 11:48:56 | chenx</small>
 */
public interface PlaceholderDao extends BaseDao<PlaceholderDO> {

	public PlaceholderDO findRandomPlaceholder();
	
}
