package com.bio.sys.controller;


import java.util.Arrays;
import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.bio.common.service.ContextService;
import com.bio.common.utils.Result;
import com.bio.sys.domain.PlaceholderDO;
import com.bio.sys.domain.ReportDO;
import com.bio.sys.domain.UserDO;
import com.bio.sys.service.PlaceholderService;
import com.bio.sys.service.ReportService;

/**
 * 
 * <pre>
 * 周报
 * </pre>
 * <small> 2019-12-14 21:13:15 | chenx</small>
 */
@Controller
@RequestMapping("/bio/report")
public class ReportController {
	@Autowired
	private ReportService reportService;
	
	@Autowired
	private ContextService contextService;
	
	@Autowired
	private PlaceholderService placeholderService;
	
	@GetMapping()
	@RequiresPermissions("bio:report:report")
	String Report(){
	    return "bio/report/report";
	}
	
	@ResponseBody
	@GetMapping("/latest/{id}")
//	@RequiresPermissions("bio:report:latest")
	public Result<ReportDO>  getLatestReport(@PathVariable("id") Integer id){
		
		UserDO userDO =  contextService.getCurrentLoginUser(SecurityUtils.getSubject());
		
		ReportDO report = reportService.selectById(id);
		ReportDO reportDO = null;
		if(null != report) {
			reportDO = reportService.getLatestReport(userDO.getId(), report.getRFromDate());
		}
		if(null != reportDO){
			return Result.ok(reportDO);
		}
		return Result.fail();
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("bio:report:report")
	public Result<Page<ReportDO>> list(Integer pageNumber, Integer pageSize, ReportDO reportDTO){
		
		UserDO userDO =  contextService.getCurrentLoginUser(SecurityUtils.getSubject());
		
		if(userDO.getroleId().intValue() == 1) { // 超级管理员，默认所有的报告
			//TODO:不做处理
		}
		
		if(userDO.getroleId().intValue() == 2) { // PI，只显示PI负责的下属dept 的报告
			reportDTO.setParentDeptId(userDO.getDeptId());
		}
		
		if(userDO.getroleId().intValue() == 3) { // 学生，只显示自己的报告
			reportDTO.setAuthorId(userDO.getId());
		}
		
		//查询列表数据
        Page<ReportDO> page = new Page<>(pageNumber, pageSize);
        
        Wrapper<ReportDO> wrapper = new EntityWrapper<ReportDO>(reportDTO);
        wrapper.orderBy("r_create_date", false);
        page = reportService.selectPage(page, wrapper);
        int total = reportService.selectCount(wrapper);
        page.setTotal(total);
        return Result.ok(page);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("bio:report:add")
	String add(){
	    return "bio/report/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("bio:report:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		ReportDO report = reportService.selectById(id);
		model.addAttribute("report", report);
		model.addAttribute("dynamicPlaceholder", "default");
		PlaceholderDO placeholderDO = placeholderService.findRandomPlaceholder();
		if(null != placeholderDO) {
			model.addAttribute("dynamicPlaceholder", placeholderDO.getContent() +" (来源:" + placeholderDO.getContributor() + "，欢迎在 意见反馈 投稿!)" );
		}
	    return "bio/report/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("bio:report:add")
	public Result<String> save( ReportDO report){
		if(reportService.insert(report)){
			return Result.ok();
		}
		return Result.fail();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("bio:report:edit")
	public Result<String>  update( ReportDO report){
		
		//判断如果有修改，则修改最后时间和 Status
		report.setRChgDate(new Date());
		report.setStatus(1);
		
		reportService.updateById(report);
		return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("bio:report:remove")
	public Result<String>  remove(Long id){
		if(reportService.deleteById(id)){
            return Result.ok();
		}
		return Result.fail();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("bio:report:batchRemove")
	public Result<String>  remove(@RequestParam("ids[]") Integer[] ids){
		reportService.deleteBatchIds(Arrays.asList(ids));
		return Result.ok();
	}
	
}
