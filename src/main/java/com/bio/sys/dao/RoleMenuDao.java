package com.bio.sys.dao;

import java.io.Serializable;
import java.util.List;

import com.bio.common.base.BaseDao;
import com.bio.sys.domain.RoleMenuDO;

/**
 * <pre>
 * 角色与菜单对应关系
 * </pre>
 * <small> 2018年3月23日 | Aron</small>
 */
public interface RoleMenuDao extends BaseDao<RoleMenuDO> {
	
	List<Long> listMenuIdByRoleId(Serializable roleId);
	
	int removeByRoleId(Serializable roleId);
	
	int batchSave(List<RoleMenuDO> list);
}
