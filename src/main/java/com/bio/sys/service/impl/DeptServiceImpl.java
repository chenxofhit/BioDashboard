package com.bio.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bio.common.domain.Tree;
import com.bio.common.utils.BuildTree;
import com.bio.sys.dao.DeptDao;
import com.bio.sys.domain.DeptDO;
import com.bio.sys.service.DeptService;

/**
 * 
 * 部门管理 Service
 * 
 * @author chenx
 *
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptDao, DeptDO> implements DeptService {

	@Override
	public Tree<DeptDO> getTree(Long deptId) {

		List<Tree<DeptDO>> trees = new ArrayList<Tree<DeptDO>>();
		List<DeptDO> sysDepts = new ArrayList<DeptDO>();
		if (deptId.intValue() == 0) { // 根 dept
			sysDepts = baseMapper.selectList(null);
		} else {
			sysDepts.add(baseMapper.selectById(deptId));
			sysDepts.addAll(getSubDepts(deptId));
		}
		if (!sysDepts.isEmpty()) {
			for (DeptDO sysDept : sysDepts) {
				Tree<DeptDO> tree = new Tree<DeptDO>();
				tree.setId(sysDept.getId().toString());
				tree.setParentId(sysDept.getParentId().toString());
				tree.setText(sysDept.getName());
				Map<String, Object> state = new HashMap<>(16);
				state.put("opened", true);
				tree.setState(state);
				trees.add(tree);
			}
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<DeptDO> t = BuildTree.build(trees);
		return t;
	}

	@Override
	public boolean checkDeptHasUser(Long deptId) {
		int result = baseMapper.getDeptUserNumber(deptId);
		return result == 0;
	}

	@Override
	public DeptDO getParentDept(Long deptId) {
		DeptDO deptDO = baseMapper.getParentDept(deptId);
		return deptDO;
	}

	@Override
	public List<DeptDO> getSubDepts(Long deptId) {
		return baseMapper.getSubDepts(deptId);
	}

}
