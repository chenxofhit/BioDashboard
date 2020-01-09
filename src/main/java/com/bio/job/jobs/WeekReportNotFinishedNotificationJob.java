package com.bio.job.jobs;

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
import com.bio.sys.domain.ReportDO;
import com.bio.sys.service.MailService;
import com.bio.sys.service.ReportService;
import com.bio.sys.service.UserService;

/**
 * 每周日晚 7 点执行 
 * 
 * @author chenx
 *
 */
@Component
public class WeekReportNotFinishedNotificationJob implements Job {

    private Logger logger = LoggerFactory.getLogger(MailService.class);

    @Value("${bio.projectRootURL}")
    private String url;
    
	@Autowired
	private ReportService reportService;
	
	@Autowired 
	private UserService userService;
	
	@Autowired
	private MailService mailService;
	
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {        
		Date mon = DateUtils.getThisWeekMondayStart(new Date());
		Date sun = DateUtils.getThisWeekSundayEnd(new Date());
		
		List<ReportDO> reportDOs = reportService.getReports(mon, sun, 0);
		
		for (ReportDO reportDO : reportDOs) {
			
			ThreadPool.getThreadPool().addRunnable(new ThreadPool.Runnable() {
				@Override
				public void run() {
					try {
						MailBean mailBean = new MailBean();

						String recipient=  userService.selectById(reportDO.getAuthorId()).getEmail();
						mailBean.setSubject("【BioDashboard】请及时填写本周周报！");
						mailBean.setRecipient(recipient);

						Map<String, Object> parameters = new HashMap<>();
						parameters.put("name", reportDO.getAuthorName());
						parameters.put("url", url);

						mailService.sendTemplateMail(mailBean, "pushreport.html", parameters);
						
					} catch (Exception e) {
						e.printStackTrace();
			            logger.error("邮件发送失败", e.getMessage());
					}
				}
			});
		}
    }
}
