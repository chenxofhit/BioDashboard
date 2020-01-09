package com.bio.sys.dao;

import com.bio.common.base.BaseDao;
import com.bio.sys.domain.AnnireportDO;

/**
 * 
 * <pre>
 * 年报
 * </pre>
 * <small> 2019-12-31 10:19:10 | chenx</small>
 */
public interface AnnireportDao extends BaseDao<AnnireportDO> {

	public Integer getReportCountByTitle(String title);

}
