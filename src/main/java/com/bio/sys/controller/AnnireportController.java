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
import com.bio.sys.domain.AnnireportDO;
import com.bio.sys.domain.UserDO;
import com.bio.sys.service.AnnireportService;

/**
 * 
 * <pre>
 * 年报
 * </pre>
 * <small> 2019-12-31 10:19:10 | chenx</small>
 */
@Controller
@RequestMapping("/bio/annireport")
public class AnnireportController {
	@Autowired
	private AnnireportService annireportService;

	@Autowired
	private ContextService contextService;
	
	@GetMapping()
	@RequiresPermissions("bio:annireport:annireport")
	String Annireport(){
	    return "bio/annireport/annireport";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("bio:annireport:annireport")
	public Result<Page<AnnireportDO>> list(Integer pageNumber, Integer pageSize, AnnireportDO annireportDTO){
		
		UserDO userDO =  contextService.getCurrentLoginUser(SecurityUtils.getSubject());
		
		if(userDO.getroleId().intValue() == 1) { // 超级管理员，默认所有的报告
			//TODO: 不做处理
		}
		
		if(userDO.getroleId().intValue() == 2) { // PI，只显示PI负责的下属dept 的报告
			annireportDTO.setParentDeptId(userDO.getDeptId());
		}
		
		if(userDO.getroleId().intValue() == 3) { // 学生，只显示自己的报告
			annireportDTO.setAuthorId(userDO.getId());
		}
		
		// 查询列表数据
        Page<AnnireportDO> page = new Page<>(pageNumber, pageSize);
        
        Wrapper<AnnireportDO> wrapper = new EntityWrapper<AnnireportDO>(annireportDTO);
        wrapper.orderBy("r_create_date", false);
        page = annireportService.selectPage(page, wrapper);
        int total = annireportService.selectCount(wrapper);
        page.setTotal(total);
        return Result.ok(page);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("bio:annireport:add")
	String add(){
	    return "bio/annireport/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("bio:annireport:edit")
	String edit(@PathVariable("id") Long id,Model model){
		AnnireportDO annireport = annireportService.selectById(id);
		model.addAttribute("annireport", annireport);
	    return "bio/annireport/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("bio:annireport:add")
	public Result<String> save( AnnireportDO annireport){
		if(annireportService.insert(annireport)){
			return Result.ok();
		}
		return Result.fail();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("bio:annireport:edit")
	public Result<String>  update( AnnireportDO annireport){

		//判断如果有修改，则修改最后时间和 Status
		annireport.setRChgDate(new Date());
		annireport.setStatus(1);
		
		annireportService.updateById(annireport);
		return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("bio:annireport:remove")
	public Result<String>  remove( Long id){
		if(annireportService.deleteById(id)){
            return Result.ok();
		}
		return Result.fail();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("bio:annireport:batchRemove")
	public Result<String>  remove(@RequestParam("ids[]") Long[] ids){
		annireportService.deleteBatchIds(Arrays.asList(ids));
		return Result.ok();
	}
	
}
