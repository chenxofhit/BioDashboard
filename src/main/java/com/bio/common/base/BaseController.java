package com.bio.common.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.bio.common.utils.ShiroUtils;
import com.bio.sys.domain.UserDO;

/**
 * 
 * <pre>
 * </pre>
 * <small> 2018年2月25日 | Aron</small>
 */
@Controller
public abstract class BaseController {
    
    protected Logger log = LoggerFactory.getLogger(this.getClass());
    
    public UserDO getUser() {
        return ShiroUtils.getUser();
    }

    public Long getUserId() {
        return getUser().getId();
    }

    public String getUsername() {
        return getUser().getUsername();
    }
}