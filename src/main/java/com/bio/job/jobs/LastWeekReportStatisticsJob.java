package com.bio.job.jobs;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.bio.common.domain.MailBean;
import com.bio.common.utils.DateUtils;
import com.bio.common.utils.ThreadPool;
import com.bio.sys.domain.DeptDO;
import com.bio.sys.domain.ReportCountDO;
import com.bio.sys.domain.UserDO;
import com.bio.sys.service.DeptService;
import com.bio.sys.service.MailService;
import com.bio.sys.service.ReportService;
import com.bio.sys.service.RoleService;
import com.bio.sys.service.UserService;

/**
 * 
 * 每周一早七点执行，给 PI发统计邮件
 * 
 * 上周的周报统计
 * 
 * 
 * @author chenx
 *
 */
@Component
public class LastWeekReportStatisticsJob implements Job {

	private Logger logger = LoggerFactory.getLogger(MailService.class);

	@Value("${bio.projectRootURL}")
	private String url;

	@Autowired
	private UserService userService;

	@Autowired
	private DeptService deptService;

	@Autowired
	private RoleService userRoleService;

	@Autowired
	private ReportService reportService;

	@Autowired
	private MailService mailService;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {

		Date fromDate = DateUtils.getLastWeekMondayStart(new Date());
		Date toDate = DateUtils.getLastWeekSundayEnd(new Date());
		
		Map<String, Object> columnMap = new HashMap<>();
		columnMap.put("status", "1"); // 正常用户

		List<UserDO> users = userService.selectByMap(columnMap);

		// Step 0，准备数据
		for (UserDO userDO : users) {
			Long roleDOs = userRoleService.findRoleIdByUserId(userDO.getId());

			List<ReportCountDO> reportCountDOs = reportService.getReportsCount(fromDate, toDate, 0);

			HashMap<Long, ReportCountDO> reportCountDOsMap = new HashMap<Long, ReportCountDO>();
			if(null != reportCountDOs && !reportCountDOs.isEmpty()) {
				for (ReportCountDO reportCountDO : reportCountDOs) {
					reportCountDOsMap.put(reportCountDO.getDeptId(), reportCountDO);
				}
			}
			
			if (null != roleDOs && roleDOs.intValue() == 2) { // PI,只针对 PI 发邮件

				Long deptId = userDO.getDeptId();

				HashMap<String, Integer> reportDONotFinishedCountsMap = new LinkedHashMap<String, Integer>();

				List<DeptDO> deptDOs = deptService.getSubDepts(deptId);
				
				// 针对每个小组
				for (DeptDO deptDO : deptDOs) {
					if (null == reportCountDOsMap.get(deptDO.getId())) {
						reportDONotFinishedCountsMap.put(deptDO.getName(), -1);
					}else {
						reportDONotFinishedCountsMap.put(deptDO.getName(), reportCountDOsMap.get(deptDO.getId()).getCountNumber());
					}
				}

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

				String title = sdf.format(fromDate) + "-" + sdf.format(toDate) + "-"
						+ deptService.selectById(deptId).getName() + " 周报";

				Map<String, Object> parameters = new HashMap<>();
				
				parameters.put("reportTitle", title);
				parameters.put("reportDONotFinishedCountsMap", reportDONotFinishedCountsMap);

				ThreadPool.getThreadPool().addRunnable(new ThreadPool.Runnable() {
					@Override
					public void run() {
						try {
							MailBean mailBean = new MailBean();

							String recipient = userDO.getEmail();
							mailBean.setSubject("【BioDashboard】上周的周报统计已经为您生成！");
							mailBean.setRecipient(recipient);
							
							parameters.put("name", userDO.getName());
							parameters.put("url", url);

							mailService.sendTemplateMail(mailBean, "summaryreport.html", parameters);

						} catch (Exception e) {
							e.printStackTrace();
							logger.error("邮件发送失败", e.getMessage());
						}
					}
				});
			}else {
				continue;
			}
		}
	}
}
