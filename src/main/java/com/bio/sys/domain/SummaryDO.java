package com.bio.sys.domain;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;



/**
 * 
 * <pre>
 * 周报汇总
 * </pre>
 * <small> 2019-12-18 15:03:07 | chenx</small>
 */
 @TableName("report_summary")
public class SummaryDO implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    
    /** ID */
    @TableId
    private Long id;
    /** 部门ID */
    private Long deptId;
    /** 部门名称 */
    private String deptName;
    /** 起始时间 */
    private Date rFromDate;
    /** 终止时间 */
    private Date rToDate;
    /** 周报题目 */
    private String title;
    /** 浏览次数 */
    private Long count;
    /** 创建时间 */
    private Date rCreateDate;

    /**
     * 设置：ID
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * 获取：ID
     */
    public Long getId() {
        return id;
    }
    /**
     * 设置：部门ID
     */
    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }
    /**
     * 获取：部门ID
     */
    public Long getDeptId() {
        return deptId;
    }
    /**
     * 设置：部门名称
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
    /**
     * 获取：部门名称
     */
    public String getDeptName() {
        return deptName;
    }
    /**
     * 设置：起始时间
     */
    public void setRFromDate(Date rFromDate) {
        this.rFromDate = rFromDate;
    }
    /**
     * 获取：起始时间
     */
    public Date getRFromDate() {
        return rFromDate;
    }
    /**
     * 设置：终止时间
     */
    public void setRToDate(Date rToDate) {
        this.rToDate = rToDate;
    }
    /**
     * 获取：终止时间
     */
    public Date getRToDate() {
        return rToDate;
    }
    /**
     * 设置：周报题目
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * 获取：周报题目
     */
    public String getTitle() {
        return title;
    }
    /**
     * 设置：浏览次数
     */
    public void setCount(Long count) {
        this.count = count;
    }
    /**
     * 获取：浏览次数
     */
    public Long getCount() {
        return count;
    }
    /**
     * 设置：创建时间
     */
    public void setRCreateDate(Date rCreateDate) {
        this.rCreateDate = rCreateDate;
    }
    /**
     * 获取：创建时间
     */
    public Date getRCreateDate() {
        return rCreateDate;
    }
}
