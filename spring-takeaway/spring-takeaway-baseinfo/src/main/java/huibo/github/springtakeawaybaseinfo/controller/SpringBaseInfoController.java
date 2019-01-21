package huibo.github.springtakeawaybaseinfo.controller;

import huibo.github.result.entity.Result;
import huibo.github.result.util.ResultTool;
import huibo.github.springtakeawaybaseinfo.entity.vo.BeanInfo;
import huibo.github.springtakeawaybaseinfo.service.SpringBaseInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @description: spring基本信息controller
 * @author: tanghuibo aa18984850147@qq.com
 * @create: 2019-01-21 22:37
 **/
@RestController
@RequestMapping("takeaway_system")
public class SpringBaseInfoController {

    SpringBaseInfoService springBaseInfoService;

    public SpringBaseInfoController(SpringBaseInfoService springBaseInfoService) {
        this.springBaseInfoService = springBaseInfoService;
    }

    /**
     * 获取容器中bean的信息
     * @return
     */
    @GetMapping("beans")
    public Result<List<BeanInfo>> getBeans() {
        List<BeanInfo> beans = springBaseInfoService.getBeans();
        return ResultTool.successData(beans);
    }

    /**
     * 获取spring配置文件信息
     * @return
     */
    @GetMapping("spring-properties")
    public Result<Map<String, Object>> getProperties() {
        Map<String, Object> map = springBaseInfoService.getProperties();
        return ResultTool.successData(map);
    }
}
