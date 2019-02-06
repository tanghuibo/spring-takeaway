package io.github.tanghuibo.springtakeawaybaseinfo.service;

import freemarker.template.TemplateException;
import io.github.tanghuibo.springtakeawaybaseinfo.entity.GenerateJsonToJavaConfig;
import io.github.tanghuibo.springtakeawaybaseinfo.entity.GenerateMybatisConfig;
import io.github.tanghuibo.springtakeawaybaseinfo.entity.dto.JavaEntityInfo;

import java.io.IOException;
import java.util.List;

/**
 * @description:
 * @author: tanghuibo aa18984850147@qq.com
 * @create: 2019-01-30 23:06
 **/
public interface GeneratorCodeService {

    /**
     * 生成mybatis代码
     * @param generateMybatisConfig
     */
    void generatorMybatisCode(GenerateMybatisConfig generateMybatisConfig);

    /**
     * 获取mybatis代码生成器默认参数
     * @return
     */
    GenerateMybatisConfig getDefaultGenerateMybatisConfig();

    /**
     * 获取json转java默认配置信息
     * @return
     */
    GenerateJsonToJavaConfig getDefaultGenerateJsonToJavaConfig();

    /**
     * json转java代码
     * @param javaEntityInfoList
     */
    void generatoJsonToJavaCode(List<JavaEntityInfo> javaEntityInfoList);
}
