package com.bio.sys.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.bio.common.domain.Tree;
import com.bio.sys.domain.DeptDO;

/**
 * <pre>
 * 部门管理
 * </pre>
 * <small> 2018年3月23日 | Aron</small>
 */
public interface DeptService extends IService<DeptDO> {
    
	/**
	 * 查询部门以及此部门的下级部门
	 * 
	 * @param deptId
	 * @return
	 */
	
	Tree<DeptDO> getTree(Long deptId);
	

	boolean checkDeptHasUser(Long deptId);
	
	/**
	 * 查询上级部门
	 * 
	 * @param deptId
	 * @return
	 */
	DeptDO getParentDept(Long deptId);
	
	List<DeptDO> getSubDepts(Long deptId);
	
}
