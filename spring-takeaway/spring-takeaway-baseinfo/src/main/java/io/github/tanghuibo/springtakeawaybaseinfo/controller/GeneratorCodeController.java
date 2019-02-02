package io.github.tanghuibo.springtakeawaybaseinfo.controller;

import io.github.tanghuibo.result.entity.Result;
import io.github.tanghuibo.result.util.ResultTool;
import io.github.tanghuibo.springtakeawaybaseinfo.config.ConstantConfig;
import io.github.tanghuibo.springtakeawaybaseinfo.entity.GenerateMybatisConfig;
import io.github.tanghuibo.springtakeawaybaseinfo.service.GeneratorCodeService;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @description: mybatis代码自动生成controller
 * @author: tanghuibo aa18984850147@qq.com
 * @create: 2019-01-30 23:03
 **/
@RestController
@RequestMapping(ConstantConfig.CONTENT_PATH + "/mybatis-generator")
@ApiIgnore
public class GeneratorCodeController {

    private GeneratorCodeService generatorCodeService;

    public GeneratorCodeController(GeneratorCodeService generatorCodeService) {
        this.generatorCodeService = generatorCodeService;
    }

    /**
     * 获取默认配置信息
     * @return
     */
    @GetMapping("default-generate-mybatis-config")
    public Result<GenerateMybatisConfig> getDefaultGenerateMybatisConfig() {
        GenerateMybatisConfig generateMybatisConfig = generatorCodeService.getDefaultGenerateMybatisConfig();
        return ResultTool.successData(generateMybatisConfig);
    }

    /**
     * 生成mybatis代码
     * @param generateMybatisConfig
     * @return
     */
    @PostMapping("mybatis-code")
    public Result generatorMybatisCode(@RequestBody GenerateMybatisConfig generateMybatisConfig) {
        generatorCodeService.generatorMybatisCode(generateMybatisConfig);
        return ResultTool.success();
    }
}
