package com.bio.job.jobs;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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
import com.bio.sys.domain.AnnireportDO;
import com.bio.sys.domain.UserDO;
import com.bio.sys.service.AnnireportService;
import com.bio.sys.service.DeptService;
import com.bio.sys.service.MailService;
import com.bio.sys.service.RoleService;
import com.bio.sys.service.UserService;

/**
 * 
 * 每年执行一次，12月份的最后一天
 * 
 * Cron 表达式：  0 0 8 L 12 ? *
 * 
 * @author chenx
 *
 */
@Component
public class ThisYearReportTemplateGenerateJob implements Job {

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
	private AnnireportService annireportService;

	@Autowired
	private MailService mailService;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		Map<String, Object> columnMap = new HashMap<>();
		columnMap.put("status", "1"); // 正常用户

		List<UserDO> users = userService.selectByMap(columnMap);

		List<AnnireportDO> annireportDOs = new ArrayList<AnnireportDO>();

		// Step 0，准备数据
		for (UserDO userDO : users) {
			Long roleDOs = userRoleService.findRoleIdByUserId(userDO.getId());

			if (null != roleDOs && roleDOs.intValue() == 3) { // 学生

				AnnireportDO reportDO = new AnnireportDO();

				reportDO.setAuthorId(userDO.getId());
				reportDO.setStatus(0); // 标识 Bot 生成的

				reportDO.setAuthorName(userDO.getName());
				reportDO.setContent("暂无");

				Calendar cal = Calendar.getInstance();
				int year = cal.get(Calendar.YEAR);
				
				String deptName = deptService.selectById(userDO.getDeptId()).getName();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				String title =  year + "年-" + deptName + "-" + userDO.getName()
						+ " 年度总结";

				if (annireportService.getReportCountByTitle(title) > 0) {
					continue;
				}

				reportDO.setRFromDate(new Date());
				reportDO.setRToDate(new Date());
				reportDO.setRCreateDate(new Date());
				reportDO.setDeptId(userDO.getDeptId());

				reportDO.setDeptName(deptName);
				Long deptId = deptService.getParentDept(userDO.getDeptId()).getId();
				reportDO.setParentDeptId(deptId);

				reportDO.setTitle(title);

				annireportDOs.add(reportDO);
			}

		}
		// Step 1, 批量插入周报
		if (annireportDOs.size() > 0) {
			boolean b = annireportService.insertBatch(annireportDOs);
			if (b) {
				for (AnnireportDO reportDO : annireportDOs) {
					ThreadPool.getThreadPool().addRunnable(new ThreadPool.Runnable() {
						@Override
						public void run() {
							try {
								MailBean mailBean = new MailBean();

								String recipient=  userService.selectById(reportDO.getAuthorId()).getEmail();
								mailBean.setSubject("【BioDashboard】本年年度总结草稿已经为您生成！");
								mailBean.setRecipient(recipient);

								Map<String, Object> parameters = new HashMap<>();
								parameters.put("name", reportDO.getAuthorName());
								parameters.put("url", url);

								mailService.sendTemplateMail(mailBean, "newannireport.html", parameters);
								
							} catch (Exception e) {
								e.printStackTrace();
					            logger.error("邮件发送失败", e.getMessage());
							}
						}
					});
				}
			}

			System.out.println("生成年度总结任务执行 | " + DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN_19));
		}
	}
}
