package com.bio.sys.service;

import com.bio.common.base.CoreService;
import com.bio.sys.domain.AnnireportDO;

/**
 * 
 * <pre>
 * 年报
 * </pre>
 * <small> 2019-12-31 10:19:10 | chenx</small>
 */
public interface AnnireportService extends CoreService<AnnireportDO> {
    
	public  Integer getReportCountByTitle(String title);

}
