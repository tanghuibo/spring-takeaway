package io.github.tanghuibo.springtakeawaybaseinfo.service;

import io.github.tanghuibo.springtakeawaybaseinfo.entity.vo.BeanInfo;

import java.util.List;
import java.util.Map;

/**
 * @description: spring基本信息service
 * @author: tanghuibo aa18984850147@qq.com
 * @create: 2019-01-21 22:38
 **/
public interface SpringBaseInfoService {

    /**
     * 获取bean基本信息
     * @return
     */
    List<BeanInfo> getBeans();

    /**
     * 获取配置信息
     * @return
     */
    Map<String, Object> getProperties();
}
