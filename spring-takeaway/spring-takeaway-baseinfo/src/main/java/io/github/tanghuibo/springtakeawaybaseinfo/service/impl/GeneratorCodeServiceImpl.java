package io.github.tanghuibo.springtakeawaybaseinfo.service.impl;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import io.github.tanghuibo.constant.ConstantCommand;
import io.github.tanghuibo.springtakeawaybaseinfo.entity.GenerateJsonToJavaConfig;
import io.github.tanghuibo.springtakeawaybaseinfo.entity.GenerateMybatisConfig;
import io.github.tanghuibo.springtakeawaybaseinfo.entity.dto.JavaEntityInfo;
import io.github.tanghuibo.springtakeawaybaseinfo.exception.SpringTakeawayException;
import io.github.tanghuibo.springtakeawaybaseinfo.service.GeneratorCodeService;
import io.github.tanghuibo.springtakeawaybaseinfo.service.MybatisPlusAutoGeneratorConfig;
import io.github.tanghuibo.util.FileUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
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



    private Map<String, MybatisPlusAutoGeneratorConfig> mybatisPlusAutoGeneratorConfigMap;
    private Configuration configuration;

    /**
     * mybatisPlusAutoGeneratorConfig
     * 用于处理AutoGenerator
     *
     * @see AutoGenerator
     */
    private volatile List<MybatisPlusAutoGeneratorConfig> mybatisPlusAutoGeneratorConfigs;

    public GeneratorCodeServiceImpl(Map<String, MybatisPlusAutoGeneratorConfig> mybatisPlusAutoGeneratorConfigMap,
                                    @Qualifier("javaCodeGeneratorConfiguration") Configuration configuration) {
        this.mybatisPlusAutoGeneratorConfigMap = mybatisPlusAutoGeneratorConfigMap;
        this.configuration = configuration;
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
        generateMybatisConfig.setAuthor(System.getProperty(ConstantCommand.USER_NAME));
        generateMybatisConfig.setPassword(password);
        generateMybatisConfig.setEntityLombokModel(true);
        generateMybatisConfig.setFileOverride(false);
        generateMybatisConfig.setTablePrefix("");
        generateMybatisConfig.setFieldPrefix("");
        generateMybatisConfig.setProjectPath(FileUtil.getProjectPath());
        generateMybatisConfig.setPackageParentName(getMainClassPath());


        generateMybatisConfig.setUrl(url);
        generateMybatisConfig.setJdbcDrive(driverClassName);
        generateMybatisConfig.setUserName(username);


        return generateMybatisConfig;
    }

    @Override
    public GenerateJsonToJavaConfig getDefaultGenerateJsonToJavaConfig() {
        GenerateJsonToJavaConfig generateJsonToJavaConfig = new GenerateJsonToJavaConfig();
        generateJsonToJavaConfig.setEntityLombokModel(true);

        generateJsonToJavaConfig.setPackageParentName(getMainClassPath());

        generateJsonToJavaConfig.setProjectPath(FileUtil.getProjectPath());
        generateJsonToJavaConfig.setAuthor(System.getProperty(ConstantCommand.USER_NAME));

        return generateJsonToJavaConfig;

    }

    @Override
    public void generatoJsonToJavaCode(List<JavaEntityInfo> javaEntityInfoList) {
        for (JavaEntityInfo javaEntityInfo:javaEntityInfoList) {
            generatoJsonToJavaCode(javaEntityInfo);

        }
    }

    private void generatoJsonToJavaCode(JavaEntityInfo javaEntityInfo) {
        String packageName = javaEntityInfo.getPackageName();
        String dirName = javaEntityInfo.getProjectPath() + File.separatorChar + packageName.replace('.', File.separatorCharChar);
        String fileName = dirName + File.separatorChar + javaEntityInfo.getClassName() + ".java";
        FileUtil.mkDirAndParentDir(dirName);
        Template template = null;
        try {
            template = configuration.getTemplate("entity.java.ftl");
        } catch (IOException e) {
            e.printStackTrace();
            throw new SpringTakeawayException("freemarker模板获取失败");
        }
        Writer out = null;
        try {
            out = new FileWriter(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            throw new SpringTakeawayException("写入文件失败");
        }
        try {
            template.process(javaEntityInfo, out);
        } catch (TemplateException e) {
            e.printStackTrace();
            throw new SpringTakeawayException("freemarker模板格式有误");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }

    }

    private String getMainClassPath() {
        String mainPath = System.getProperty(ConstantCommand.MAIN_CLASS_PATH);
        if (!StringUtils.isEmpty(mainPath)) {
            int index = mainPath.lastIndexOf(".");
            if (index > 0) {
                return mainPath.substring(0, index);
            }
        }
        return null;
    }

    public List<MybatisPlusAutoGeneratorConfig> getMybatisPlusAutoGeneratorConfigs() {
        if (mybatisPlusAutoGeneratorConfigs == null) {
            synchronized (MybatisPlusAutoGeneratorConfig.class) {
                if (mybatisPlusAutoGeneratorConfigs == null) {
                    mybatisPlusAutoGeneratorConfigs = mybatisPlusAutoGeneratorConfigMap.values().stream().collect(Collectors.toList());
                }
            }
        }
        return mybatisPlusAutoGeneratorConfigs;
    }

}
