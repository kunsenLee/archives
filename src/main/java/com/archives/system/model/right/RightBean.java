package com.archives.system.model.right;

/**
 * 权限资源
 * @Author: lee
 * @Date: Create in 2018-4-18 18:02
 */
public class RightBean {

    private Integer rightid;

    private Integer parentid;

    private String rightcode;

    private String title;

    private Integer rightclassify;

    private String icon;

    private String href;

    private Integer state;

    private Integer creater;

    private String rightorder;

    public Integer getRightid() {
        return rightid;
    }

    public void setRightid(Integer rightid) {
        this.rightid = rightid;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public String getRightcode() {
        return rightcode;
    }

    public void setRightcode(String rightcode) {
        this.rightcode = rightcode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getRightclassify() {
        return rightclassify;
    }

    public void setRightclassify(Integer rightclassify) {
        this.rightclassify = rightclassify;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getCreater() {
        return creater;
    }

    public void setCreater(Integer creater) {
        this.creater = creater;
    }

    public String getRightorder() {
        return rightorder;
    }

    public void setRightorder(String rightorder) {
        this.rightorder = rightorder;
    }
}
