package io.github.tanghuibo.springtakeawaybootui.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @description: 配置信息类
 * @author: tanghuibo aa18984850147@qq.com
 * @create: 2019-01-30 00:08
 **/

@Configuration
public class AutoUiConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/spring-takeaway-ui/**").addResourceLocations("classpath:/META-INF/spring-takeaway-ui/");
    }
}
