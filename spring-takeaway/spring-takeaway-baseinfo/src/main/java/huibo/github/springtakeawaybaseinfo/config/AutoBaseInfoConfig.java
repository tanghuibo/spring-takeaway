package huibo.github.springtakeawaybaseinfo.config;

import huibo.github.springtakeawaybaseinfo.controller.SpringBaseInfoController;
import huibo.github.springtakeawaybaseinfo.controller.SystemBaseInfoController;
import huibo.github.springtakeawaybaseinfo.service.SpringBaseInfoService;
import huibo.github.springtakeawaybaseinfo.service.SystemBaseInfoService;
import huibo.github.springtakeawaybaseinfo.service.impl.SpringBaseInfoServiceImpl;
import huibo.github.springtakeawaybaseinfo.service.impl.SystemBaseInfoServiceImpl;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;

/**
 * @description: 基础信息配置
 * @author: tanghuibo aa18984850147@qq.com
 * @create: 2019-01-20 23:47
 **/
@Configuration
public class AutoBaseInfoConfig {

    @Bean
    public SystemBaseInfoService getSystemBaseInfoService() {
        return new SystemBaseInfoServiceImpl();
    }

    @Bean
    public SystemBaseInfoController getSystemBaseInfoController(SystemBaseInfoService systemBaseInfoService) {
        return new SystemBaseInfoController(systemBaseInfoService);
    }

    @Bean
    @Lazy
    public SpringBaseInfoService getSpringBaseInfoService(StandardEnvironment environment, ConfigurableListableBeanFactory beanFactory) {
        return new SpringBaseInfoServiceImpl(environment, beanFactory);
    }

    @Bean
    @Lazy
    public SpringBaseInfoController getSpringBaseInfoController(SpringBaseInfoService springBaseInfoService) {
        return new SpringBaseInfoController(springBaseInfoService);
    }
}
