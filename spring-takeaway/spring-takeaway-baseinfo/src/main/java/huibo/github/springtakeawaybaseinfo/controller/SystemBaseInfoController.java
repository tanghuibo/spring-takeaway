package huibo.github.springtakeawaybaseinfo.controller;

import huibo.github.result.entity.Result;
import huibo.github.result.util.ResultTool;
import huibo.github.springtakeawaybaseinfo.service.SystemBaseInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Properties;

/**
 * @description: 系统基本信息controller
 * @author: tanghuibo aa18984850147@qq.com
 * @create: 2019-01-20 23:35
 **/
@RestController
@RequestMapping("takeaway_system")
public class SystemBaseInfoController {

    SystemBaseInfoService systemBaseInfoService;

    public SystemBaseInfoController(SystemBaseInfoService systemBaseInfoService) {
        this.systemBaseInfoService = systemBaseInfoService;

    }


    /**
     * 获取系统配置信息
     * @return
     */
    @GetMapping("jvm-properties")
    public Result getProperties() {
        Map<Object, Object> result = systemBaseInfoService.getProperties();
        return ResultTool.successData(result);
    }
}
