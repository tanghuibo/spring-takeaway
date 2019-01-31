package io.github.tanghuibo.springtakeawaybaseinfo.service.impl;

import com.baomidou.mybatisplus.generator.config.IDbQuery;
import static com.baomidou.mybatisplus.annotation.DbType.*;

import io.github.tanghuibo.springtakeawaybaseinfo.util.DbTypeUtil;
import io.github.tanghuibo.springtakeawaybaseinfo.config.DbType;
import io.github.tanghuibo.springtakeawaybaseinfo.entity.vo.SqlFieldInfo;
import io.github.tanghuibo.springtakeawaybaseinfo.entity.vo.TableInfo;
import io.github.tanghuibo.springtakeawaybaseinfo.exception.SpringTakeawayException;
import io.github.tanghuibo.springtakeawaybaseinfo.service.DataBaseInfoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

/**
 * @description:数据库基本信息serviceImpl
 * @author: tanghuibo aa18984850147@qq.com
 * @create: 2019-01-27 16:24
 **/
@Service("dataBaseInfoService")
@Lazy
public class DataBaseInfoServiceImpl implements DataBaseInfoService {

    @Value("${spring.datasource.driver-class-name:}")
    private String driverClassName;

    /**
     * 数据库连接池
     */
    private DataSource dataSource;

    /**
     * bean容器
     */
    private ConfigurableListableBeanFactory configurableListableBeanFactory;



    /**
     * 构造器
     * @param dataSource
     * @param configurableListableBeanFactory
     */
    public DataBaseInfoServiceImpl(@Lazy DataSource dataSource,@Lazy ConfigurableListableBeanFactory configurableListableBeanFactory) {
        this.dataSource = dataSource;
        this.configurableListableBeanFactory = configurableListableBeanFactory;

    }

    /**
     * 获取所有表格接口
     * @return
     * @throws SQLException
     */
    @Override
    public List<TableInfo> getTables() throws SQLException {
        Connection connection = dataSource.getConnection();
        try {
            DbType dbType = DbTypeUtil.getDbTypeByDriverClassName(driverClassName, connection.getClass().getName());
            if(dbType == null) {
                throw new SpringTakeawayException("请在配置文件中设置 spring.datasource.driver-class-name");
            }
            IDbQuery dbQuery =dbType.getiDbQuery();

            String tablesSql = dbQuery.tablesSql();
            String schema = connection.getMetaData().getSchemaTerm();
            if (POSTGRE_SQL == dbQuery.dbType()) {

                tablesSql = String.format(tablesSql, schema);
            }

            if (ORACLE == dbQuery.dbType()) {
                tablesSql = String.format(tablesSql, schema);
            }

            PreparedStatement preparedStatement = connection.prepareStatement(tablesSql);
            ResultSet results = preparedStatement.executeQuery();
            List<TableInfo> tableInfos = new ArrayList<>();
            while (results.next()) {
                TableInfo tableInfo = new TableInfo();
                tableInfo.setTableName( results.getString(dbQuery.tableName()));
                tableInfo.setComment( results.getString(dbQuery.tableComment()));
                tableInfos.add(tableInfo);

            }
            return tableInfos;
        } finally {
            if(connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public List<SqlFieldInfo> getFields(String tableName) throws SQLException {
        Connection connection = dataSource.getConnection();
        try {
            DbType dbType = DbTypeUtil.getDbTypeByDriverClassName(driverClassName, connection.getClass().getName());
            if(dbType == null) {
                throw new SpringTakeawayException("请在配置文件中设置 spring.datasource.driver-class-name");
            }
            IDbQuery dbQuery = dbType.getiDbQuery();

            String tableFieldsSql = dbQuery.tableFieldsSql();
            String schema = connection.getMetaData().getSchemaTerm();
            if (POSTGRE_SQL == dbQuery.dbType()) {
                tableFieldsSql = String.format(tableFieldsSql, schema, tableName);
            } else if (ORACLE == dbQuery.dbType()) {
                tableFieldsSql = String.format(tableFieldsSql.replace("#schema", schema), tableName);
            } else {
                tableFieldsSql = String.format(tableFieldsSql, tableName);
            }

            PreparedStatement preparedStatement = connection.prepareStatement(tableFieldsSql);
            ResultSet results = preparedStatement.executeQuery();

            List<SqlFieldInfo> sqlFieldInfos = new ArrayList<>();
            while (results.next()) {
                SqlFieldInfo sqlFieldInfo = new SqlFieldInfo();
                sqlFieldInfo.setFieldName(results.getString(dbQuery.fieldName()));
                sqlFieldInfo.setDataType(results.getString(dbQuery.fieldType()));
                sqlFieldInfo.setComment(results.getString(dbQuery.fieldComment()));
                sqlFieldInfos.add(sqlFieldInfo);

            }


            return sqlFieldInfos;
        } finally {
            if(connection != null) {
                connection.close();
            }
        }

    }

    @Override
    public List<String> getDbDriverList() {
        DbType[] dbTypes = DbType.values();
        List<String> result = new ArrayList<>();
        for (DbType dbType:dbTypes) {
            result.addAll(dbType.getDriverList());
        }
        return result;
    }
}
