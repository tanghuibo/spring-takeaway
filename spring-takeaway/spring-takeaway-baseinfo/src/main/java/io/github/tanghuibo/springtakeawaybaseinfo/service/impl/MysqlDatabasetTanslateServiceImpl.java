package io.github.tanghuibo.springtakeawaybaseinfo.service.impl;

import io.github.tanghuibo.springtakeawaybaseinfo.entity.vo.TableInfo;
import io.github.tanghuibo.springtakeawaybaseinfo.service.DatabasetTanslateService;
import io.github.tanghuibo.util.DataBaseUtil;
import io.github.tanghuibo.util.StringUtil;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @description:mysql数据库翻译
 * @author: tanghuibo aa18984850147@qq.com
 * @create: 2019-01-27 18:40
 **/
public class MysqlDatabasetTanslateServiceImpl implements DatabasetTanslateService {

    /**
     * 默认排序优先级
     */
    public static final int ORDER = 10;

    private static final String MYSQL_DB_NAME = "MYSQL";

    /**
     * 排序优先级
     *
     * @return
     */
    public int order() {
        return ORDER;
    }

    @Override
    public boolean isMatching(Connection connection) {
        try {
            String driverName = connection.getMetaData().getDriverName();
            if (driverName.toUpperCase().contains(MYSQL_DB_NAME)) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<TableInfo> getTableList(Connection connection){
        List<TableInfo> tableInfos = new ArrayList<>();
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet resultSet = metaData.getTables(null, null, null, null);

            List<Map<String, Object>> tableMaps = DataBaseUtil.resultSetToListMap(resultSet);


            for (Map<String, Object> map : tableMaps) {

                TableInfo tableInfo = new TableInfo();
                tableInfo.setTableName(StringUtil.toString(map.get("tableName")));
                tableInfos.add(tableInfo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tableInfos;
    }
}
