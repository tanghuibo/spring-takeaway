package io.github.tanghuibo.springtakeawaybaseinfo.entity;

import lombok.Data;

import java.util.List;

/**
 * @description: 自动生成代码配置
 * @author: tanghuibo aa18984850147@qq.com
 * @create: 2019-01-30 23:05
 **/
@Data
public class GenerateMybatisConfig {

    /**
     * 表格名称
     */
    private List<String> tableNames;

    /**
     * 用户名
     */
    private String url;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户名
     */
    private String author;

    /**
     * 工程路径
     */
    private String projectPath;

    /**
     * 数据库驱动
     */
    private String jdbcDrive;

    /**
     * 数据库用户名
     */
    private String userName;

    /**
     * 包名前缀
     */
    private String packageParentName;

    /**
     * 使用lombok
     */
    private Boolean entityLombokModel;

    /**
     * 是否覆盖文件
     */
    private Boolean fileOverride;

    /**
     * 表前缀
     */
    private String tablePrefix;

    /**
     * 字段前缀
     */
    private String fieldPrefix;
}
