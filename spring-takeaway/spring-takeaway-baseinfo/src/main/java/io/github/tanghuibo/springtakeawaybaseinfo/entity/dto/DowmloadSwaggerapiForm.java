package io.github.tanghuibo.springtakeawaybaseinfo.entity.dto;

import lombok.Data;

/**
 * @description: 下载接口文档form表单
 * @author: tanghuibo aa18984850147@qq.com
 * @create: 2019-02-03 03:23
 **/
@Data
public class DowmloadSwaggerapiForm {

    /**
     * 前端url
     */
    private String hostUrl;

    /**
     * 语言
     */
    private String language;

    /**
     * 文件类型
     */
    private String fileType;
}
