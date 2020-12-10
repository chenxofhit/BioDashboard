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

	/**
	 * 获取指定的部门在某个时间段的周报列表
	 * 
	 * @param deptId
	 * @param fromDate
	 * @param toDate
	 * @return
	 */
	List<ReportDO> selectListReportDOs(@Param("deptId") Long deptId, @Param("fromDate") Date fromDate, @Param("toDate") Date toDate); 

	/**
	 * 根据title获取summary的数目
	 * 
	 * @param title
	 * @return
	 */
	public Integer getSummaryCountByTitle(String title);

}
