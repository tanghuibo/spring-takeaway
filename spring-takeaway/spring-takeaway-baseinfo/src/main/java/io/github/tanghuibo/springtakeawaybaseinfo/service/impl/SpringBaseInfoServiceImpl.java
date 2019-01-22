package io.github.tanghuibo.springtakeawaybaseinfo.service.impl;

import io.github.tanghuibo.springtakeawaybaseinfo.entity.vo.BeanInfo;
import io.github.tanghuibo.springtakeawaybaseinfo.service.SpringBaseInfoService;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.env.*;

import java.util.*;

/**
 * @description: spring基本信息service
 * @author: tanghuibo aa18984850147@qq.com
 * @create: 2019-01-21 22:39
 **/
public class SpringBaseInfoServiceImpl implements SpringBaseInfoService {

    private ConfigurableListableBeanFactory beanFactory;

    private StandardEnvironment environment;

    public SpringBaseInfoServiceImpl(StandardEnvironment standardEnvironment, ConfigurableListableBeanFactory beanFactory) {
        this.environment = standardEnvironment;
        this.beanFactory = beanFactory;
    }

    @Override
    public List<BeanInfo> getBeans() {
        Iterator<String> beanNamesIterator = beanFactory.getBeanNamesIterator();
        List<BeanInfo> list = new ArrayList<>(50);
        while (beanNamesIterator.hasNext()) {
            String beanName = beanNamesIterator.next();
            Object data = beanFactory.getBean(beanName);
            Class<?> beanClass = data.getClass();
            BeanInfo beanInfo = new BeanInfo();
            beanInfo.setBeanName(beanName);
            beanInfo.setClassName(beanClass.getName());
            beanInfo.setSimpleClassName(beanClass.getSimpleName());
            list.add(beanInfo);
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
            if(source instanceof LinkedHashMap) {
                Map<String, Object> map = (Map) source;
                Set<String> keySet = map.keySet();
                for (String key: keySet) {
                    Object value = map.get(key);
                    result.put(key,value.toString());

                }
            }
        }
        return result;
    }
}
