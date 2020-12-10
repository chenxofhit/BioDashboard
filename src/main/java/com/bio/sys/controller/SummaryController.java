package com.bio.sys.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
import com.bio.sys.domain.DeptDO;
import com.bio.sys.domain.ReportDO;
import com.bio.sys.domain.SummaryDO;
import com.bio.sys.domain.UserDO;
import com.bio.sys.service.DeptService;
import com.bio.sys.service.SummaryService;

/**
 * 
 * <pre>
 * 周报汇总
 * </pre>
 * <small> 2019-12-18 15:03:07 | chenx</small>
 * 
 */

@Controller
@RequestMapping("/bio/summary")
public class SummaryController {
	
	@Autowired
	private SummaryService summaryService;
	
	@Autowired
	private DeptService deptService;
	
	@Autowired
	private ContextService contextService;
	
	@GetMapping()
	@RequiresPermissions("bio:summary:summary")
	String Summary(){
	    return "bio/summary/summary";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("bio:summary:summary")
	public Result<Page<SummaryDO>> list(Integer pageNumber, Integer pageSize, SummaryDO summaryDTO){
		
		UserDO userDO =  contextService.getCurrentLoginUser(SecurityUtils.getSubject());

		if(userDO.getroleId().intValue() == 1) { // 超级管理员，默认所有的报告
		}
		
		if(userDO.getroleId().intValue() == 2) { // PI，显示PI负责的下属dept 的报告
			summaryDTO.setDeptId(userDO.getDeptId());
		}
		
		if(userDO.getroleId().intValue() == 3) { // 学生，显示自己的PI下属所有dept的报告(开放性设计，这个地方针对企业和研究组的逻辑可能不一样)
			summaryDTO.setDeptId(deptService.selectById(userDO.getDeptId()).getParentId());
		}
		
		// 查询列表数据
        Page<SummaryDO> page = new Page<>(pageNumber, pageSize);
        
        Wrapper<SummaryDO> wrapper = new EntityWrapper<SummaryDO>(summaryDTO);
        wrapper.orderBy("r_create_date", false);
        page = summaryService.selectPage(page, wrapper);
        int total = summaryService.selectCount(wrapper);
        page.setTotal(total);
        return Result.ok(page);
	}
	
	@GetMapping("/review/{id}")
	String reiview(@PathVariable("id") Long id,Model model){
		
		SummaryDO summary = summaryService.selectById(id);
		model.addAttribute("summary", summary);
		
		UserDO userDO =  contextService.getCurrentLoginUser(SecurityUtils.getSubject());
		
		HashMap<String, List<ReportDO>> reportDOMap = new HashMap<String, List<ReportDO>>();
		
		HashMap<String, Integer> reportDONotFinishedCountsMap = new HashMap<String, Integer>();
		HashMap<String, List<String>> reportDONotFinishedStaffsMap = new HashMap<String, List<String>>();
		HashMap<String, Integer> reportDOShouldFinishedCountsMap = new HashMap<String, Integer>();

	
		int notFinishedStaffNumber = 0;
		int yesFinishedStaffNumber = 0;
		
		Long deptId = summary.getDeptId();

		List<DeptDO> deptDOs = deptService.getSubDepts(deptId);

		Date fromDate = summary.getRFromDate();
		Date toDate = summary.getRToDate();

		//针对每个小组
		for (DeptDO deptDO : deptDOs) {
			
			int notFinishedStaffNumberDept = 0;
			int yesFinishedStaffNumberDept = 0;
			
			List<String> notFinishedStaffNamesDept = new ArrayList<String>();

			List<ReportDO> reportDOs = summaryService.selectListReportDOs(deptDO.getId(), fromDate, toDate);
			List<ReportDO> reportDORets = new ArrayList<ReportDO>();
			
			for (ReportDO reportDO : reportDOs) {
				
				if(reportDO.getStatus() == 0) { //未填写
					notFinishedStaffNumberDept++;
					notFinishedStaffNamesDept.add(reportDO.getAuthorName());
					
				}else {//已填写
					reportDORets.add(reportDO);
					yesFinishedStaffNumberDept ++;
				}
			}
			
			//针对每个部门，设置这五个变量！
			
			reportDONotFinishedCountsMap.put(deptDO.getName(), notFinishedStaffNumberDept);
			reportDONotFinishedStaffsMap.put(deptDO.getName(), notFinishedStaffNamesDept);
			reportDOShouldFinishedCountsMap.put(deptDO.getName(), reportDOs.size());
			
			yesFinishedStaffNumber += yesFinishedStaffNumberDept;
			notFinishedStaffNumber += notFinishedStaffNumberDept;
			
			reportDOMap.put(deptDO.getName(), reportDORets);
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		
		String title = sdf.format(fromDate) + "-" + sdf.format(toDate) + "-" + deptService.selectById(deptId).getName()
				+ " 周报";
		model.addAttribute("reportTitle", title);
		model.addAttribute("reportDOMap", reportDOMap);
		
		
		model.addAttribute("reportDONotFinishedCountsMap", reportDONotFinishedCountsMap);
		model.addAttribute("reportDONotFinishedStaffsMap", reportDONotFinishedStaffsMap);
		
		//老师和管理员角色返回统计信息：
		if (userDO.getroleId().intValue() == 2 || userDO.getroleId().intValue() == 1) {
			model.addAttribute("showStatistics", 1); //返回标识

			model.addAttribute("reportDOShouldFinishedCountsMap", reportDOShouldFinishedCountsMap);
			model.addAttribute("yesFinishedStaffNumber", yesFinishedStaffNumber);
			model.addAttribute("notFinishedStaffNumber", notFinishedStaffNumber);
		}
		
		//更新浏览次数
		summary.setCount(summary.getCount() + 1);
		summaryService.updateById(summary);
		
	    return "bio/summary/review";
	}
	
	
	@GetMapping("/add")
	@RequiresPermissions("bio:summary:add")
	String add(){
	    return "bio/summary/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("bio:summary:edit")
	String edit(@PathVariable("id") Long id,Model model){
		SummaryDO summary = summaryService.selectById(id);
		model.addAttribute("summary", summary);
	    return "bio/summary/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("bio:summary:add")
	public Result<String> save( SummaryDO summary){
		if(summaryService.insert(summary)){
			return Result.ok();
		}
		return Result.fail();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("bio:summary:edit")
	public Result<String>  update( SummaryDO summary){
		summaryService.updateById(summary);
		return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("bio:summary:remove")
	public Result<String>  remove( Long id){
		if(summaryService.deleteById(id)){
            return Result.ok();
		}
		return Result.fail();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("bio:summary:batchRemove")
	public Result<String>  remove(@RequestParam("ids[]") Long[] ids){
		summaryService.deleteBatchIds(Arrays.asList(ids));
		return Result.ok();
	}
	
}
