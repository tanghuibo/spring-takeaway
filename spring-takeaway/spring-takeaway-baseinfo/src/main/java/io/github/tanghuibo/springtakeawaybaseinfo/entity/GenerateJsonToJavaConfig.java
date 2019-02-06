package io.github.tanghuibo.springtakeawaybaseinfo.entity;

import lombok.Data;


/**
 * @description: json转java配置信息
 * @author: tanghuibo aa18984850147@qq.com
 * @create: 2019-02-06 22:55
 **/
@Data
public class GenerateJsonToJavaConfig {


    /**
     * 工程路径
     */
    private String projectPath;

    /**
     * 包名前缀
     */
    private String packageParentName;

    /**
     * 使用lombok
     */
    private Boolean entityLombokModel;

    /**
     * 用户名
     */
    private String author;


}
