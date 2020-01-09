package com.bio.sys.domain;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;



/**
 * 
 * <pre>
 * 
 * </pre>
 * <small> 2019-12-17 11:48:56 | chenx</small>
 */
 @TableName("placeholder")
public class PlaceholderDO implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    
    /** 主键 ID */
    @TableId
    private Long id;
    /**  */
    private String content;
    /**  */
    private String contributor;

    /**
     * 设置：主键 ID
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * 获取：主键 ID
     */
    public Long getId() {
        return id;
    }
    /**
     * 设置：
     */
    public void setContent(String content) {
        this.content = content;
    }
    /**
     * 获取：
     */
    public String getContent() {
        return content;
    }
    /**
     * 设置：
     */
    public void setContributor(String contributor) {
        this.contributor = contributor;
    }
    /**
     * 获取：
     */
    public String getContributor() {
        return contributor;
    }
}
