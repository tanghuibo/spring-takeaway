package io.github.tanghuibo.springtakeawaybaseinfo.service.impl;

import io.github.tanghuibo.springtakeawaybaseinfo.entity.vo.SqlFieldInfo;
import io.github.tanghuibo.springtakeawaybaseinfo.entity.vo.TableInfo;
import io.github.tanghuibo.springtakeawaybaseinfo.service.DatabasetTanslateService;
import io.github.tanghuibo.util.ClassUtil;
import io.github.tanghuibo.util.DataBaseUtil;
import io.github.tanghuibo.util.StringUtil;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @description:mysql数据库翻译
 * @author: tanghuibo aa18984850147@qq.com
 * @create: 2019-01-27 18:40
 **/
@Service("mysqlDatabasetTanslateService")
@Lazy
public class MysqlDatabasetTanslateServiceImpl implements DatabasetTanslateService {


    /**
     * 默认排序优先级
     */
    public static final int ORDER = 10;

    /**
     * mysql
     */
    private static final String MYSQL_DB_NAME = "MYSQL";

    /**
     * 数据库名称
     */
    private String dbName;

    /**
     * 查询数据库表格sql
     */
    private static final String SELECT_TABLE_SQL = "SHOW TABLE STATUS FROM `%s`";

    private static final String SELECT_FIELD_SQL = "SELECT \n" +
            "  column_name,\n" +
            "  column_comment,\n" +
            "  data_type \n" +
            "FROM\n" +
            "  information_schema.columns \n" +
            "WHERE table_name = ? \n" +
            "  AND table_schema = ? ";

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
    public List<TableInfo> getTableList(Connection connection) {
        List<TableInfo> tableInfos = new ArrayList<>();
        try {

            String dbName = getDbName(connection);
            PreparedStatement preparedStatement = connection.prepareStatement(String.format(SELECT_TABLE_SQL, dbName));
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Map<String, Object>> tableMaps = DataBaseUtil.resultSetToListMap(resultSet);


            for (Map<String, Object> map : tableMaps) {

                TableInfo tableInfo = new TableInfo();
                tableInfo.setTableName(StringUtil.toString(map.get("tableName")));
                tableInfo.setType(map.get("engine") == null ? "VIEW" : "TABLE");
                tableInfo.setComment(StringUtil.toString(map.get("tableComment")));
                tableInfos.add(tableInfo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tableInfos;
    }

    @Override
    public List<SqlFieldInfo> getFields(Connection connection, String tableName) {
        List<SqlFieldInfo> sqlFieldInfos = new ArrayList<>();
        try {
            String dbName = getDbName(connection);
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FIELD_SQL);
            preparedStatement.setString(1, tableName);
            preparedStatement.setString(2, dbName);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Map<String, Object>> tableMaps = DataBaseUtil.resultSetToListMap(resultSet);
            for (Map<String, Object> map : tableMaps) {

                SqlFieldInfo sqlFieldInfo = new SqlFieldInfo();
                sqlFieldInfo.setComment(StringUtil.toString(map.get("columnComment")));
                sqlFieldInfo.setDataType(StringUtil.toString(map.get("dataType")));
                sqlFieldInfo.setFieldName(StringUtil.toString(map.get("columnName")));

                sqlFieldInfos.add(sqlFieldInfo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sqlFieldInfos;
    }

    public String getDbName(Connection connection) {
        if (dbName == null) {
            synchronized (this.getClass()) {
                if (dbName == null) {
                    synchronized (this.getClass()) {
                        try {
                            DatabaseMetaData metaData = connection.getMetaData();
                            dbName = (String) ClassUtil.getPropertyByFiledName(metaData, metaData.getClass(), "database");

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                    }
                }

            }
        }
        return dbName;
    }
}
