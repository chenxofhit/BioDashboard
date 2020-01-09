package com.bio.sys.service;

import java.util.Date;
import java.util.List;

import com.bio.common.base.CoreService;
import com.bio.sys.domain.ReportDO;
import com.bio.sys.domain.SummaryDO;

/**
 * 
 * <pre>
 * 周报汇总
 * </pre>
 * <small> 2019-12-18 15:03:07 | chenx</small>
 */
public interface SummaryService extends CoreService<SummaryDO> {
    
	/**
	 * 查询某个部门的所有员工在某周的周报列表
	 * 
	 * @param deptId
	 * @param fromDate
	 * @param toDate
	 * @return
	 */
	public List<ReportDO> selectListReportDOs(Long deptId, Date fromDate, Date toDate);
	
	/**
	 * 
	 * 
	 * @param title
	 * @return
	 */
	public  Integer getSummaryCountByTitle(String title);

	
}
