package com.bio.sys.dao;

import java.io.Serializable;
import java.util.List;

import com.bio.common.base.BaseDao;
import com.bio.sys.domain.UserRoleDO;

/**
 * <pre>
 * 用户与角色对应关系
 * </pre>
 * <small> 2018年3月23日 | Aron</small>
 */
public interface UserRoleDao extends BaseDao<UserRoleDO> {

	Long findRoleId(Serializable userId);

	int removeByUserId(Serializable userId);

	int batchSave(List<UserRoleDO> list);

	int batchRemoveByUserId(Long[] ids);
}
