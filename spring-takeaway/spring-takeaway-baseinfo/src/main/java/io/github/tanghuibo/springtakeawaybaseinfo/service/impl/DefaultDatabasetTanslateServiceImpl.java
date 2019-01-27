package io.github.tanghuibo.springtakeawaybaseinfo.service.impl;

import java.sql.Connection;

/**
 * @description: 默认数据库翻译
 * @author: tanghuibo aa18984850147@qq.com
 * @create: 2019-01-27 18:47
 **/
public class DefaultDatabasetTanslateServiceImpl extends MysqlDatabasetTanslateServiceImpl {

    public static final int ORDER = Integer.MAX_VALUE;

    @Override
    public int getOrder() {
        return ORDER;
    }

    @Override
    public boolean isMatching(Connection connection) {
        return true;
    }
}
