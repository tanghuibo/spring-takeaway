package io.github.tanghuibo.springtakeawaybaseinfo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @description: 基础信息配置
 * @author: tanghuibo aa18984850147@qq.com
 * @create: 2019-01-20 23:47
 **/
@Configuration
@ComponentScan(value = {"io.github.tanghuibo.springtakeawaybaseinfo"})
@EnableSwagger2
public class AutoBaseInfoConfig {

    /**
     * 生成freemark config
     * @return
     */
    @Bean("freemarkerConfiguration")
    public freemarker.template.Configuration getFreemarkerConfiguration() {
        freemarker.template.Configuration configuration
                = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_28);


        configuration.setClassForTemplateLoading(AutoBaseInfoConfig.class, "/META-INF/templates");


        return configuration;

    }

}
