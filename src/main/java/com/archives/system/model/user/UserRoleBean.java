package com.archives.system.model.user;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @Author: lee
 * @Date: Create in 2018-5-28 13:18
 */
@Table(name = "DA_T_SYS_USERROLE")
public class UserRoleBean {
    @Id
    @Column(name = "USERROLEID")
    private BigDecimal userroleid;

    @Column(name = "USERID")
    private BigDecimal userid;

    @Column(name = "ROLEID")
    private BigDecimal roleid;

    /**
     * @return USERROLEID
     */
    public BigDecimal getUserroleid() {
        return userroleid;
    }

    /**
     * @param userroleid
     */
    public void setUserroleid(BigDecimal userroleid) {
        this.userroleid = userroleid;
    }

    /**
     * @return USERID
     */
    public BigDecimal getUserid() {
        return userid;
    }

    /**
     * @param userid
     */
    public void setUserid(BigDecimal userid) {
        this.userid = userid;
    }

    /**
     * @return ROLEID
     */
    public BigDecimal getRoleid() {
        return roleid;
    }

    /**
     * @param roleid
     */
    public void setRoleid(BigDecimal roleid) {
        this.roleid = roleid;
    }
}
