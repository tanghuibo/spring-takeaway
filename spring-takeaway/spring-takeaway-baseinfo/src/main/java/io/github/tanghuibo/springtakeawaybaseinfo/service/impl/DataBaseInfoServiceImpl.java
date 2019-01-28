package io.github.tanghuibo.springtakeawaybaseinfo.service.impl;

import io.github.tanghuibo.springtakeawaybaseinfo.entity.vo.SqlFieldInfo;
import io.github.tanghuibo.springtakeawaybaseinfo.entity.vo.TableInfo;
import io.github.tanghuibo.springtakeawaybaseinfo.service.DataBaseInfoService;
import io.github.tanghuibo.springtakeawaybaseinfo.service.DatabasetTanslateService;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @description:数据库基本信息serviceImpl
 * @author: tanghuibo aa18984850147@qq.com
 * @create: 2019-01-27 16:24
 **/
public class DataBaseInfoServiceImpl implements DataBaseInfoService {

    /**
     * 数据库连接池
     */
    private DataSource dataSource;

    /**
     * bean容器
     */
    private ConfigurableListableBeanFactory configurableListableBeanFactory;

    /**
     * 数据库翻译机集合
     */
    private volatile List<DatabasetTanslateService> databasetTanslateServices;

    /**
     * 当前数据库翻译机
     */
    private volatile DatabasetTanslateService databasetTanslateService;


    /**
     * 构造器
     * @param dataSource
     * @param configurableListableBeanFactory
     */
    public DataBaseInfoServiceImpl(DataSource dataSource, ConfigurableListableBeanFactory configurableListableBeanFactory) {
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
            DatabasetTanslateService databasetTanslateService = getMatchDatabasetTanslateService(connection);
            return databasetTanslateService.getTableList(connection);
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
            DatabasetTanslateService databasetTanslateService = getMatchDatabasetTanslateService(connection);
            return databasetTanslateService.getFields(connection, tableName);
        } finally {
            if(connection != null) {
                connection.close();
            }
        }

    }

    /**
     * 获取与connection匹配的数据库翻译机
     * @param connection
     * @return
     */
    private DatabasetTanslateService getMatchDatabasetTanslateService(Connection connection) {
        if(databasetTanslateService == null) {
            synchronized (this.getClass()) {
                if(databasetTanslateService == null) {
                    synchronized (this.getClass()) {
                        List<DatabasetTanslateService> databasetTanslateServices = getDatabasetTanslateServices();
                        for (DatabasetTanslateService databasetTanslateService: databasetTanslateServices) {
                            if(databasetTanslateService.isMatching(connection)) {
                                this.databasetTanslateService = databasetTanslateService;
                                return this.databasetTanslateService;
                            }
                        }
                    }
                }
            }
        }
        return databasetTanslateService;
    }

    /**
     * 获取数据库翻译机集合
     * @return
     */
    public List<DatabasetTanslateService> getDatabasetTanslateServices() {
        if(databasetTanslateServices == null) {
            synchronized (this.getClass()) {
                if(databasetTanslateServices == null) {
                    synchronized (this.getClass()) {
                        Map<String, DatabasetTanslateService> beansOfType = configurableListableBeanFactory.getBeansOfType(DatabasetTanslateService.class);
                        databasetTanslateServices = beansOfType.values().stream().collect(Collectors.toList());
                        databasetTanslateServices.sort(Comparator.comparing(DatabasetTanslateService::getOrder));
                    }

                }

            }
        }
        return databasetTanslateServices;
    }
}
