package io.github.tanghuibo.springtakeawaybaseinfo.entity.vo;

import lombok.Data;

/**
 * @description: 数据库信息
 * @author: tanghuibo aa18984850147@qq.com
 * @create: 2019-01-27 16:22
 **/

@Data
public class TableInfo {

    /**
     * 表名称
     */
    private String tableName;


    /**
     * 备注
     */
    private String comment;
}
