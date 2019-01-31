package io.github.tanghuibo.springtakeawaybaseinfo.service.impl;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import io.github.tanghuibo.constant.ConstantCommand;
import io.github.tanghuibo.springtakeawaybaseinfo.entity.GenerateMybatisConfig;
import io.github.tanghuibo.springtakeawaybaseinfo.service.GeneratorCodeService;
import io.github.tanghuibo.springtakeawaybaseinfo.service.MybatisPlusAutoGeneratorConfig;
import io.github.tanghuibo.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
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

    @Value("${spring.datasource.driver-class-name:}")
    private String driverClassName;

    @Value("${spring.datasource.username:}")
    private String username;

    @Value("${spring.datasource.url:}")
    private String url;


    /**
     * bean容器
     */
    private ConfigurableListableBeanFactory configurableListableBeanFactory;

    /**
     * mybatisPlusAutoGeneratorConfig
     * 用于处理AutoGenerator
     * @see AutoGenerator
     */
    private volatile List<MybatisPlusAutoGeneratorConfig> mybatisPlusAutoGeneratorConfigs;

    public GeneratorCodeServiceImpl(ConfigurableListableBeanFactory configurableListableBeanFactory) {
        this.configurableListableBeanFactory = configurableListableBeanFactory;
    }

    @Override
    public void generatorMybatisCode(GenerateMybatisConfig generateMybatisConfig) {
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setFileOverride(generateMybatisConfig.getFileOverride());
        gc.setOutputDir(generateMybatisConfig.getProjectPath());
        gc.setAuthor(generateMybatisConfig.getAuthor());
        gc.setOpen(false);
        gc.setBaseColumnList(true);
        gc.setBaseResultMap(true);
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
        strategy.setTablePrefix(generateMybatisConfig.getTablePrefix());
        strategy.setFieldPrefix(generateMybatisConfig.getFieldPrefix());
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
        generateMybatisConfig.setAuthor(System.getProperty(ConstantCommand.USER_DIR));
        generateMybatisConfig.setPassword(password);
        generateMybatisConfig.setEntityLombokModel(true);
        generateMybatisConfig.setFileOverride(false);
        generateMybatisConfig.setTablePrefix("");
        generateMybatisConfig.setFieldPrefix("");
        generateMybatisConfig.setProjectPath(FileUtil.getProjectPath());
        String mainPath = System.getProperty(ConstantCommand.MAIN_CLASS_PATH);
        if (!StringUtils.isEmpty(mainPath)) {
            int index = mainPath.lastIndexOf(".");
            if (index > 0) {
                mainPath = mainPath.substring(0, index);
            }
            generateMybatisConfig.setPackageParentName(mainPath);
        }


        generateMybatisConfig.setUrl(url);
        generateMybatisConfig.setJdbcDrive(driverClassName);
        generateMybatisConfig.setUserName(username);


        return generateMybatisConfig;
    }

    public List<MybatisPlusAutoGeneratorConfig> getMybatisPlusAutoGeneratorConfigs() {
        if (mybatisPlusAutoGeneratorConfigs == null) {
            synchronized (MybatisPlusAutoGeneratorConfig.class) {
                if (mybatisPlusAutoGeneratorConfigs == null) {
                    Map<String, MybatisPlusAutoGeneratorConfig> beanMap = configurableListableBeanFactory.getBeansOfType(MybatisPlusAutoGeneratorConfig.class);
                    mybatisPlusAutoGeneratorConfigs = beanMap.values().stream().collect(Collectors.toList());
                }
            }
        }
        return mybatisPlusAutoGeneratorConfigs;
    }

}
