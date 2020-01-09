package com.bio.sys.dao;

import java.util.List;

import com.bio.common.base.BaseDao;
import com.bio.sys.domain.MenuDO;

/**
 * <pre>
 * 菜单管理
 * </pre>
 * <small> 2018年3月23日 | Aron</small>
 */
public interface MenuDao extends BaseDao<MenuDO> {
	
	List<MenuDO> listMenuByUserId(Long id);
	
	List<String> listUserPerms(Long id);
}
