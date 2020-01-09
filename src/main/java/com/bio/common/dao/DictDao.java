package com.bio.common.dao;

import java.util.List;

import com.bio.common.base.BaseDao;
import com.bio.common.domain.DictDO;

/**
 * 字典表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-03 15:45:42
 */
public interface DictDao extends BaseDao<DictDO>{

    List<DictDO> listType();
    
}
