package io.github.tanghuibo.springtakeawaybaseinfo.service;

import io.github.tanghuibo.springtakeawaybaseinfo.entity.GenerateMybatisConfig;

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

}
