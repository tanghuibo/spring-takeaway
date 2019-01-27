package io.github.tanghuibo.springtakeawaybaseinfo.config;

import io.github.tanghuibo.springtakeawaybaseinfo.controller.DataBaseInfoController;
import io.github.tanghuibo.springtakeawaybaseinfo.controller.SpringBaseInfoController;
import io.github.tanghuibo.springtakeawaybaseinfo.controller.SystemBaseInfoController;
import io.github.tanghuibo.springtakeawaybaseinfo.service.DataBaseInfoService;
import io.github.tanghuibo.springtakeawaybaseinfo.service.SpringBaseInfoService;
import io.github.tanghuibo.springtakeawaybaseinfo.service.SystemBaseInfoService;
import io.github.tanghuibo.springtakeawaybaseinfo.service.impl.*;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.StandardEnvironment;

import javax.sql.DataSource;

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

    @Bean
    @Lazy
    public DataBaseInfoService getDataBaseInfoService(DataSource dataSource, ConfigurableListableBeanFactory configurableListableBeanFactory) {
        return new DataBaseInfoServiceImpl(dataSource, configurableListableBeanFactory);
    }

    @Bean
    @Lazy
    public DataBaseInfoController getDataBaseInfoController(DataBaseInfoService dataBaseInfoService) {
        return new DataBaseInfoController(dataBaseInfoService);
    }

    @Bean("defaultDatabasetTanslateService")
    public DefaultDatabasetTanslateServiceImpl getDefaultDatabasetTanslateServiceImpl() {
        return new DefaultDatabasetTanslateServiceImpl();
    }

    @Bean("mysqlDatabasetTanslateService")
    public MysqlDatabasetTanslateServiceImpl getMysqlDatabasetTanslateServiceImpl() {
        return new MysqlDatabasetTanslateServiceImpl();
    }
}
