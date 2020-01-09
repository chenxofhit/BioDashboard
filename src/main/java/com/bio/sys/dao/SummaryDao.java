package com.bio.sys.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bio.common.base.BaseDao;
import com.bio.sys.domain.ReportDO;
import com.bio.sys.domain.SummaryDO;

/**
 * 
 * <pre>
 * 周报汇总
 * </pre>
 * <small> 2019-12-18 15:03:07 | chenx</small>
 */
public interface SummaryDao extends BaseDao<SummaryDO> {

	List<ReportDO> selectListReportDOs(@Param("deptId") Long deptId, @Param("fromDate") Date fromDate, @Param("toDate") Date toDate); 

	public Integer getSummaryCountByTitle(String title);

}
