package com.bio.sys.service;

import com.bio.common.base.CoreService;
import com.bio.sys.domain.PlaceholderDO;

/**
 * 
 * <pre>
 * 
 * </pre>
 * <small> 2019-12-17 11:48:56 | chenx</small>
 */
public interface PlaceholderService extends CoreService<PlaceholderDO> {
    
	public  PlaceholderDO findRandomPlaceholder();
	
}
