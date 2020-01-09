package com.bio.job.service;

import java.io.Serializable;

import org.quartz.SchedulerException;

import com.bio.common.base.CoreService;
import com.bio.job.domain.TaskDO;


/**
 * 
 * 
 * 任务 Service
 * 
 * @author chenx
 * @since 2019-12-15 19:47:01
 *
 */
public interface JobService extends CoreService<TaskDO> {
	
	/**
	 *  立即执行一次任务
	 *  
	 * @throws SchedulerException
	 */
	boolean  runAtOnce(Serializable id) throws SchedulerException;
	
	/**
	 * 初始化
	 * 
	 * @throws SchedulerException
	 */
	void initSchedule() throws SchedulerException;

	/**
	 * 改变任务状态
	 * 
	 * @param jobId
	 * @param cmd
	 * @throws SchedulerException
	 */
	void changeStatus(Long jobId, String cmd) throws SchedulerException;

	/**
	 * 更新任务Cron 表达式
	 * 
	 * @param jobId
	 * @throws SchedulerException
	 */
	void updateCron(Long jobId) throws SchedulerException;
}
