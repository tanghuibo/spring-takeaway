package io.github.tanghuibo.springtakeawaybaseinfo.entity.vo;

import lombok.Data;

/**
 * @description: class信息
 * @author: tanghuibo aa18984850147@qq.com
 * @create: 2019-01-27 02:37
 **/
@Data
public class ClassInfo {

    /**
     * 全名称
     */
    private String className;

    /**
     * 简单名称
     */
    private String simpleClassName;

    /**
     * 类加载器名称
     */
    private String classLoaderName;

}
