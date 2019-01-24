package io.github.tanghuibo.springtakeawaybaseinfo.controller;
import io.github.tanghuibo.result.entity.Result;
import io.github.tanghuibo.result.util.ResultTool;
import io.github.tanghuibo.springtakeawaybaseinfo.entity.vo.JVMInfo;
import io.github.tanghuibo.springtakeawaybaseinfo.service.SystemBaseInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @description: 系统基本信息controller
 * @author: tanghuibo aa18984850147@qq.com
 * @create: 2019-01-20 23:35
 **/
@RestController
@RequestMapping("takeaway/jvm")
public class SystemBaseInfoController {


    SystemBaseInfoService systemBaseInfoService;

    public SystemBaseInfoController(SystemBaseInfoService systemBaseInfoService) {
        this.systemBaseInfoService = systemBaseInfoService;

    }

    /**
     * 获取系统配置信息
     * @return
     */
    @GetMapping("runtime-info")
    public Result getRunTimeInfo() {
        JVMInfo jVMInfo = systemBaseInfoService.getRunTimeInfo();
        return ResultTool.successData(jVMInfo);
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
