package io.github.tanghuibo.springtakeawaybaseinfo.service;

import io.github.tanghuibo.springtakeawaybaseinfo.entity.vo.ClassInfo;
import io.github.tanghuibo.springtakeawaybaseinfo.entity.vo.JvmInfo;

import java.util.List;
import java.util.Map;

/**
 * @description: 系统基本信息service
 * @author: tanghuibo aa18984850147@qq.com
 * @create: 2019-01-20 23:36
 **/
public interface SystemBaseInfoService {

    /**
     * 获取基础配置信息
     *
     * @return
     */
    Map<Object, Object> getProperties();

    /**
     * 获取jvm运行状态
     *
     * @return
     */
    JvmInfo getRunTimeInfo();

    /**
     * 获取已装载到类加载器中的class信息
     *
     * @return
     */
    List<ClassInfo> getClassByClassLoader();
}
