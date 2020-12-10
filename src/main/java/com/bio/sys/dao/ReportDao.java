package com.bio.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bio.common.base.BaseDao;
import com.bio.sys.domain.ReportCountDO;
import com.bio.sys.domain.ReportDO;

/**
 * 
 * <pre>
 * 周报
 * </pre>
 * <small> 2019-12-14 21:13:15 | chenx</small>
 */
public interface ReportDao extends BaseDao<ReportDO> {

	/**
	 * 获取最近周的周报
	 * @param authorid
	 * @param rToDate
	 * @return
	 */
	public  ReportDO getLatestReport(@Param("authorid") Long authorid, @Param("rfromdate") String rToDate);

	/**
	 * 根据title获取周报的数目
	 * 
	 * @param title
	 * @return
	 */
	public Integer getReportCountByTitle(String title);
	
	/**
	 * 根据指定的区间和状态获取周报列表
	 * 
	 * @param fromDate
	 * @param toDate
	 * @param status
	 * @return
	 */
	public List<ReportDO> getReports(@Param("fromdate") String fromDate,@Param("todate") String toDate, @Param("status") Integer status);
	
	/**
	 * 根据指定的区间和状态获取周报数目
	 * 
	 * @param fromDate
	 * @param toDate
	 * @param status
	 * @return
	 */
	 List<ReportCountDO> getReportsCount(@Param("fromdate") String fromDate,@Param("todate") String toDate, @Param("status") Integer status);
}
