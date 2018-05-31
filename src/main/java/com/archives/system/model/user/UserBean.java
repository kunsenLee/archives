package com.archives.system.model.user;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 人员用户
 * @Author: lee
 * @Date: Create in 2018-4-18 14:12
 */
@Table(name = "DA_T_SYS_USER")
public class UserBean implements Serializable{

    @Id
    @Column(name = "USERID")
    private Integer userid;

    @Column(name = "USERNUM")
    private String usernum;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "USERPASS")
    private String userpass;

    @Column(name = "DEPTID")
    private Integer deptid;

    @Column(name = "ORGID")
    private Integer orgid;

    @Column(name = "STATE")
    private String state;

    @Column(name = "REALNAME")
    private String realname;

    @Column(name = "SEX")
    private String sex;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "MAIL")
    private String mail;

    @Column(name = "BIRTH")
    private Date birth;

    @Column(name = "MEMO")
    private String memo;

    @Transient
    private String deptname;

    /**
     * @return USERID
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * @param userid
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * @return USERNUM
     */
    public String getUsernum() {
        return usernum;
    }

    /**
     * @param usernum
     */
    public void setUsernum(String usernum) {
        this.usernum = usernum;
    }

    /**
     * @return USERNAME
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return USERPASS
     */
    public String getUserpass() {
        return userpass;
    }

    /**
     * @param userpass
     */
    public void setUserpass(String userpass) {
        this.userpass = userpass;
    }

    /**
     * @return DEPTID
     */
    public Integer getDeptid() {
        return deptid;
    }

    /**
     * @param deptid
     */
    public void setDeptid(Integer deptid) {
        this.deptid = deptid;
    }

    /**
     * @return ORGID
     */
    public Integer getOrgid() {
        return orgid;
    }

    /**
     * @param orgid
     */
    public void setOrgid(Integer orgid) {
        this.orgid = orgid;
    }

    /**
     * @return STATE
     */
    public String getState() {
        return state;
    }

    /**
     * @param state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return REALNAME
     */
    public String getRealname() {
        return realname;
    }

    /**
     * @param realname
     */
    public void setRealname(String realname) {
        this.realname = realname;
    }

    /**
     * @return SEX
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * @return PHONE
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return MAIL
     */
    public String getMail() {
        return mail;
    }

    /**
     * @param mail
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * @return BIRTH
     */
    public Date getBirth() {
        return birth;
    }

    /**
     * @param birth
     */
    public void setBirth(Date birth) {
        this.birth = birth;
    }

    /**
     * @return MEMO
     */
    public String getMemo() {
        return memo;
    }

    /**
     * @param memo
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }
}
