package io.github.tanghuibo.springtakeawaybaseinfo.entity.vo;

import lombok.Data;

/**
 * @description: 数据库字段
 * @author: tanghuibo aa18984850147@qq.com
 * @create: 2019-01-29 00:03
 **/
@Data
public class SqlFieldInfo {

    /**
     * 字段名称
     */
    private String fieldName;

    /**
     * 备注
     */
    private String comment;

    /**
     * 数据类型
     */
    private String dataType;


}
