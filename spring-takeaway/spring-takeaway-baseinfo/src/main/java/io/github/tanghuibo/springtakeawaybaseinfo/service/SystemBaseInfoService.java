package io.github.tanghuibo.springtakeawaybaseinfo.service;

import io.github.tanghuibo.springtakeawaybaseinfo.entity.vo.JVMInfo;

import java.util.Map;

/**
 * @description: 系统基本信息service
 * @author: tanghuibo aa18984850147@qq.com
 * @create: 2019-01-20 23:36
 **/
public interface SystemBaseInfoService {

    /**
     * 获取基础配置信息
     * @return
     */
    Map<Object, Object> getProperties();

    /**
     * 获取jvm运行状态
     * @return
     */
    JVMInfo getRunTimeInfo();
}
