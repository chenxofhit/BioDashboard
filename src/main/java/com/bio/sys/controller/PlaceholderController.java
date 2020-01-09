package com.bio.sys.controller;


import java.util.Arrays;

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
import com.bio.common.utils.Result;
import com.bio.sys.domain.PlaceholderDO;
import com.bio.sys.service.PlaceholderService;

/**
 * 
 * <pre>
 * 
 * </pre>
 * <small> 2019-12-17 11:48:56 | chenx</small>
 */
@Controller
@RequestMapping("/bio/placeholder")
public class PlaceholderController {
	@Autowired
	private PlaceholderService placeholderService;
	
	@GetMapping()
	@RequiresPermissions("bio:placeholder:placeholder")
	String Placeholder(){
	    return "bio/placeholder/placeholder";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("bio:placeholder:placeholder")
	public Result<Page<PlaceholderDO>> list(Integer pageNumber, Integer pageSize, PlaceholderDO placeholderDTO){
		// 查询列表数据
        Page<PlaceholderDO> page = new Page<>(pageNumber, pageSize);
        
        Wrapper<PlaceholderDO> wrapper = new EntityWrapper<PlaceholderDO>(placeholderDTO);
        page = placeholderService.selectPage(page, wrapper);
        int total = placeholderService.selectCount(wrapper);
        page.setTotal(total);
        return Result.ok(page);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("bio:placeholder:add")
	String add(){
	    return "bio/placeholder/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("bio:placeholder:edit")
	String edit(@PathVariable("id") Long id,Model model){
		PlaceholderDO placeholder = placeholderService.selectById(id);
		model.addAttribute("placeholder", placeholder);
	    return "bio/placeholder/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("bio:placeholder:add")
	public Result<String> save( PlaceholderDO placeholder){
		if(placeholderService.insert(placeholder)){
			return Result.ok();
		}
		return Result.fail();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("bio:placeholder:edit")
	public Result<String>  update( PlaceholderDO placeholder){
		placeholderService.updateById(placeholder);
		return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("bio:placeholder:remove")
	public Result<String>  remove( Long id){
		if(placeholderService.deleteById(id)){
            return Result.ok();
		}
		return Result.fail();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("bio:placeholder:batchRemove")
	public Result<String>  remove(@RequestParam("ids[]") Long[] ids){
		placeholderService.deleteBatchIds(Arrays.asList(ids));
		return Result.ok();
	}
	
}
