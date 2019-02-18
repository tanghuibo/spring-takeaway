package io.github.tanghuibo.springtakeawaytestweb;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 测试controller
 * @author: tanghuibo aa18984850147@qq.com
 * @create: 2019-02-03 00:57
 **/
@RestController
public class TestController {

    @GetMapping("test")
    public String test() {
        return "success";
    }
}
