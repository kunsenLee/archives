package com.archives.system.model.role;

import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "DA_T_SYS_ROLE")
public class RoleBean {
    @Id
    @Column(name = "ROLEID")
    private BigDecimal roleid;

    @Column(name = "ROLECODE")
    private String rolecode;

    @Column(name = "ROLENAME")
    private String rolename;

    @Column(name = "ROLENUM")
    private String rolenum;

    @Column(name = "ROLEMEMO")
    private String rolememo;

    @Transient
    private Integer userid;

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

    /**
     * @return ROLECODE
     */
    public String getRolecode() {
        return rolecode;
    }

    /**
     * @param rolecode
     */
    public void setRolecode(String rolecode) {
        this.rolecode = rolecode;
    }

    /**
     * @return ROLENAME
     */
    public String getRolename() {
        return rolename;
    }

    /**
     * @param rolename
     */
    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    /**
     * @return ROLENUM
     */
    public String getRolenum() {
        return rolenum;
    }

    /**
     * @param rolenum
     */
    public void setRolenum(String rolenum) {
        this.rolenum = rolenum;
    }

    /**
     * @return ROLEMEMO
     */
    public String getRolememo() {
        return rolememo;
    }

    /**
     * @param rolememo
     */
    public void setRolememo(String rolememo) {
        this.rolememo = rolememo;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}