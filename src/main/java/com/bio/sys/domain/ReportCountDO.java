package com.bio.sys.domain;

public class ReportCountDO {

    /** 所属部门ID */
    private Long deptId;
    
    private String deptName;
    
    private Integer  countNumber;

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Integer getCountNumber() {
		return countNumber;
	}

	public void setCountNumber(Integer countNumber) {
		this.countNumber = countNumber;
	}
    
    
}
