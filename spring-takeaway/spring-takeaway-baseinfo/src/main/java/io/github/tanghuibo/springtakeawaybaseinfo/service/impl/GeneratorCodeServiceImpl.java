package io.github.tanghuibo.springtakeawaybaseinfo.service.impl;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import io.github.tanghuibo.springtakeawaybaseinfo.entity.GenerateMybatisConfig;
import io.github.tanghuibo.springtakeawaybaseinfo.service.GeneratorCodeService;
import io.github.tanghuibo.springtakeawaybaseinfo.service.MybatisPlusAutoGeneratorConfig;
import io.github.tanghuibo.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: tanghuibo aa18984850147@qq.com
 * @create: 2019-01-30 23:07
 **/
@Service("generatorCodeService")
public class GeneratorCodeServiceImpl implements GeneratorCodeService {

    @Value("${spring.datasource.password:}")
    private String password;

    /**
     * bean容器
     */
    private ConfigurableListableBeanFactory configurableListableBeanFactory;

    private volatile List<MybatisPlusAutoGeneratorConfig> mybatisPlusAutoGeneratorConfigs;

    public GeneratorCodeServiceImpl(ConfigurableListableBeanFactory configurableListableBeanFactory) {
        this.configurableListableBeanFactory = configurableListableBeanFactory;
    }

    @Override
    public void generatorMybatisCode(GenerateMybatisConfig generateMybatisConfig) {
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(generateMybatisConfig.getProjectPath());
        gc.setAuthor(generateMybatisConfig.getAuthor());
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(generateMybatisConfig.getUrl());
        dsc.setDriverName(generateMybatisConfig.getJdbcDrive());
        dsc.setUsername(generateMybatisConfig.getUserName());
        dsc.setPassword(generateMybatisConfig.getPassword());
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(generateMybatisConfig.getPackageParentName());
        mpg.setPackageInfo(pc);

        TemplateConfig templateConfig = new TemplateConfig();
        mpg.setTemplate(templateConfig);
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(generateMybatisConfig.getEntityLombokModel());
        strategy.setRestControllerStyle(true);
        List<String> tableNames = generateMybatisConfig.getTableNames();
        strategy.setInclude(tableNames.toArray(new String[tableNames.size()]));

        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        List<MybatisPlusAutoGeneratorConfig> mybatisPlusAutoGeneratorConfigs = getMybatisPlusAutoGeneratorConfigs();
        mybatisPlusAutoGeneratorConfigs.forEach(mybatisPlusAutoGeneratorConfig -> mybatisPlusAutoGeneratorConfig.handleAutoGenerator(mpg));
        mpg.execute();
    }

    @Override
    public GenerateMybatisConfig getDefaultGenerateMybatisConfig() {
        GenerateMybatisConfig generateMybatisConfig = new GenerateMybatisConfig();
        generateMybatisConfig.setAuthor(System.getProperty("user.name"));
        generateMybatisConfig.setPassword(password);
        generateMybatisConfig.setEntityLombokModel(true);
        generateMybatisConfig.setProjectPath(FileUtil.getProjectPath());
        String mainPath = System.getProperty("sun.java.command");
        if(!StringUtils.isEmpty(mainPath)) {
            int index = mainPath.lastIndexOf(".");
            if(index > 0) {
                mainPath = mainPath.substring(0, index);
            }
            generateMybatisConfig.setPackageParentName(mainPath);
        }

        Connection connection = null;
        try {
            DataSource bean = configurableListableBeanFactory.getBean(DataSource.class);
            connection = bean.getConnection();
            DatabaseMetaData metaData = connection.getMetaData();
            generateMybatisConfig.setUrl(metaData.getURL());
            generateMybatisConfig.setJdbcDrive("com.mysql.jdbc.Driver");
            generateMybatisConfig.setUserName(metaData.getUserName());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


        return generateMybatisConfig;
    }



    public List<MybatisPlusAutoGeneratorConfig> getMybatisPlusAutoGeneratorConfigs() {
        if(mybatisPlusAutoGeneratorConfigs == null) {
            synchronized (MybatisPlusAutoGeneratorConfig.class) {
                if(mybatisPlusAutoGeneratorConfigs == null) {
                    Map<String, MybatisPlusAutoGeneratorConfig> beanMap = configurableListableBeanFactory.getBeansOfType(MybatisPlusAutoGeneratorConfig.class);
                    mybatisPlusAutoGeneratorConfigs = beanMap.values().stream().collect(Collectors.toList());
                }
            }
        }
        return mybatisPlusAutoGeneratorConfigs;
    }

}
