package com.bio.common.service;

import com.bio.common.base.CoreService;
import com.bio.common.domain.ConfigDO;

/**
 * <pre>
 * </pre>
 * 
 * <small> 2018年4月6日 | Aron</small>
 */
public interface ConfigService extends CoreService<ConfigDO> {
    ConfigDO getByKey(String k);

    String getValuByKey(String k);
}
