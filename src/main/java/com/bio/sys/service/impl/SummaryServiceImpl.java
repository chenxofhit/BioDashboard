package com.bio.sys.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bio.common.base.CoreServiceImpl;
import com.bio.sys.dao.SummaryDao;
import com.bio.sys.domain.ReportDO;
import com.bio.sys.domain.SummaryDO;
import com.bio.sys.service.SummaryService;

/**
 * 
 * <pre>
 * 周报汇总
 * </pre>
 * <small> 2019-12-18 15:03:07 | chenx</small>
 */
@Service
public class SummaryServiceImpl extends CoreServiceImpl<SummaryDao, SummaryDO> implements SummaryService {

	@Override
	public List<ReportDO> selectListReportDOs(Long deptId, Date fromDate, Date toDate) {
		
		return baseMapper.selectListReportDOs(deptId, fromDate, toDate);
	
	}

	@Override
	public Integer getSummaryCountByTitle(String title) {
		
		return baseMapper.getSummaryCountByTitle(title);
	
	}

}
