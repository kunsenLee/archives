package com.archives.config.shiro;

import com.jagregory.shiro.freemarker.ShiroTags;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.PostConstruct;

/**
 * @Author: lee
 * @Date: Create in 2018-4-23 17:29
 */
@Component
class ShiroFreeMarkerConf {
    @Autowired
    private  FreeMarkerConfigurer freeMarkerConfigurer;


    @PostConstruct
    public void setSharedVariable() throws TemplateException {
        freeMarkerConfigurer.getConfiguration().setSharedVariable("shiro",new ShiroTags());
    }
}
