package io.github.tanghuibo.springtakeawaybaseinfo.entity.vo;

import lombok.Data;

/**
 * @description: bean基本信息
 * @author: tanghuibo aa18984850147@qq.com
 * @create: 2019-01-21 22:44
 **/
@Data
public class SpringBeanInfo {

    /**
     * bean名称
     */
    private String beanName;

    /**
     * bean className
     */
    private String className;

    /**
     * bean simpleClassName
     */
    private String simpleClassName;
}
