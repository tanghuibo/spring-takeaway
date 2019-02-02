package io.github.tanghuibo.springtakeawaybaseinfo.controller;

import io.github.tanghuibo.result.entity.Result;
import io.github.tanghuibo.result.util.ResultTool;
import io.github.tanghuibo.springtakeawaybaseinfo.config.ConstantConfig;
import io.github.tanghuibo.springtakeawaybaseinfo.entity.vo.SpringBeanInfo;
import io.github.tanghuibo.springtakeawaybaseinfo.service.SpringBaseInfoService;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

/**
 * @description: spring基本信息controller
 * @author: tanghuibo aa18984850147@qq.com
 * @create: 2019-01-21 22:37
 **/
@RestController
@RequestMapping(ConstantConfig.CONTENT_PATH + "/spring")
@Lazy
@ApiIgnore
public class SpringBaseInfoController {

    SpringBaseInfoService springBaseInfoService;

    public SpringBaseInfoController(@Lazy SpringBaseInfoService springBaseInfoService) {
        this.springBaseInfoService = springBaseInfoService;
    }

    /**
     * 获取容器中bean的信息
     *
     * @return
     */
    @GetMapping("spring-beans")
    public Result<List<SpringBeanInfo>> getBeans() {
        List<SpringBeanInfo> beans = springBaseInfoService.getSpringBeans();
        return ResultTool.successData(beans);
    }

    /**
     * 获取spring配置文件信息
     *
     * @return
     */
    @GetMapping("spring-properties")
    public Result<Map<String, Object>> getProperties() {
        Map<String, Object> map = springBaseInfoService.getProperties();
        return ResultTool.successData(map);
    }
}
