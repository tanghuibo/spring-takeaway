package huibo.github.springtakeawaybaseinfo.config;

import huibo.github.springtakeawaybaseinfo.controller.SystemBaseInfoController;
import huibo.github.springtakeawaybaseinfo.service.SystemBaseInfoService;
import huibo.github.springtakeawaybaseinfo.service.impl.SystemBaseInfoServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

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
}
