package io.github.tanghuibo.springtakeawaybaseinfo.service.impl;

import io.github.tanghuibo.springtakeawaybaseinfo.entity.vo.SpringBeanInfo;
import io.github.tanghuibo.springtakeawaybaseinfo.service.SpringBaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.*;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @description: spring基本信息service
 * @author: tanghuibo aa18984850147@qq.com
 * @create: 2019-01-21 22:39
 **/
@Service("springBaseInfoService")
@Lazy
public class SpringBaseInfoServiceImpl implements SpringBaseInfoService {

    private ConfigurableListableBeanFactory beanFactory;

    private StandardEnvironment environment;

    public SpringBaseInfoServiceImpl(StandardEnvironment standardEnvironment,@Autowired(required = false) ConfigurableListableBeanFactory beanFactory) {
        this.environment = standardEnvironment;
        this.beanFactory = beanFactory;
    }

    @Override
    public List<SpringBeanInfo> getSpringBeans() {
        Iterator<String> beanNamesIterator = beanFactory.getBeanNamesIterator();
        List<SpringBeanInfo> list = new ArrayList<>(50);
        while (beanNamesIterator.hasNext()) {
            String beanName = beanNamesIterator.next();
            Object data = beanFactory.getBean(beanName);
            Class<?> beanClass = data.getClass();
            SpringBeanInfo springBeanInfo = new SpringBeanInfo();
            springBeanInfo.setBeanName(beanName);
            springBeanInfo.setClassName(beanClass.getName());
            springBeanInfo.setSimpleClassName(beanClass.getSimpleName());
            list.add(springBeanInfo);
        }
        return list;
    }

    @Override
    public Map<String, Object> getProperties() {
        Map<String, Object> result = new HashMap<>(32);
        MutablePropertySources propertySources = environment.getPropertySources();
        Iterator<PropertySource<?>> iterator = propertySources.iterator();

        while (iterator.hasNext()) {
            PropertySource<?> next = iterator.next();
            Object source = next.getSource();
            if (source instanceof Map) {
                Map<String, Object> map = (Map) source;
                Set<String> keySet = map.keySet();
                for (String key : keySet) {
                    Object value = map.get(key);
                    result.put(key, value.toString());
                }
            }
        }
        Set<String> keys = System.getProperties().stringPropertyNames();
        for (String key: keys) {
            result.remove(key);
        }
        return result;
    }
}
