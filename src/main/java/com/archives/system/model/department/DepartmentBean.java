package com.archives.system.model.department;

/**
 * 组织机构
 * @Author: lee
 * @Date: Create in 2018-5-3 11:47
 */
public class DepartmentBean {

    private Integer deptid;

    private Integer parentid;

    private String fondid;

    private String deptnum;

    private String deptname;

    private String memo;

    private String jgwt;

    private String deptorder;

    public Integer getDeptid() {
        return deptid;
    }

    public void setDeptid(Integer deptid) {
        this.deptid = deptid;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public String getFondid() {
        return fondid;
    }

    public void setFondid(String fondid) {
        this.fondid = fondid;
    }

    public String getDeptnum() {
        return deptnum;
    }

    public void setDeptnum(String deptnum) {
        this.deptnum = deptnum;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getJgwt() {
        return jgwt;
    }

    public void setJgwt(String jgwt) {
        this.jgwt = jgwt;
    }

    public String getDeptorder() {
        return deptorder;
    }

    public void setDeptorder(String deptorder) {
        this.deptorder = deptorder;
    }

}
