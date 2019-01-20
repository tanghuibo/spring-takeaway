package huibo.github.springtakeawaybaseinfo.service.impl;

import huibo.github.springtakeawaybaseinfo.service.SystemBaseInfoService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Properties;

/**
 * @description:
 * @author: tanghuibo aa18984850147@qq.com
 * @create: 2019-01-20 23:49
 **/
@Service("systemBaseInfoService")
public class SystemBaseInfoServiceImpl implements SystemBaseInfoService {

    /**
     * 获取基础配置信息
     * @return
     */
    @Override
    public Map<Object, Object> getProperties() {
        Properties properties = System.getProperties();
        return properties;
    }
}
