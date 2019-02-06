package io.github.tanghuibo.springtakeawaybaseinfo.entity.dto;

import lombok.Data;

import java.util.List;

/**
 * @description: java实体信息
 * @author: tanghuibo aa18984850147@qq.com
 * @create: 2019-02-06 22:58
 **/
@Data
public class JavaEntityInfo {

    /**
     * 类名信息
     */
    private String className;

    /**
     * 包名信息
     */
    private String packageName;

    /**
     * 字段信息
     */
    private List<JavaFieldInfo> javaFieldInfoList;

    /**
     * 导入列表
     */
    private List<String> importList;


    /**
     * 工程路径
     */
    private String projectPath;


    /**
     * 使用lombok
     */
    private Boolean entityLombokModel;

    /**
     * 用户名
     */
    private String author;


}
