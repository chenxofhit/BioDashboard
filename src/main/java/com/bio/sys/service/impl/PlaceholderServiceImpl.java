package com.bio.sys.service.impl;

import org.springframework.stereotype.Service;

import com.bio.common.base.CoreServiceImpl;
import com.bio.sys.dao.PlaceholderDao;
import com.bio.sys.domain.PlaceholderDO;
import com.bio.sys.service.PlaceholderService;

/**
 * 
 * <pre>
 * 
 * </pre>
 * <small> 2019-12-17 11:48:56 | chenx</small>
 */
@Service
public class PlaceholderServiceImpl extends CoreServiceImpl<PlaceholderDao, PlaceholderDO> implements PlaceholderService {

	public  PlaceholderDO findRandomPlaceholder() {
		
		return  baseMapper.findRandomPlaceholder();
		
	}
}


