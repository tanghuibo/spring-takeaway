package io.github.tanghuibo.springtakeawaybaseinfo.service;

import io.github.tanghuibo.springtakeawaybaseinfo.entity.vo.SqlFieldInfo;
import io.github.tanghuibo.springtakeawaybaseinfo.entity.vo.TableInfo;

import java.sql.SQLException;
import java.util.List;

/**
 * @description: 数据库基本信息service
 * @author: tanghuibo aa18984850147@qq.com
 * @create: 2019-01-27 16:23
 **/
public interface DataBaseInfoService {

    /**
     * 获取表格数据
     * @return
     * @throws SQLException
     */
    List<TableInfo> getTables() throws SQLException;

    /**
     * 获取表格字段
     * @param tableName
     * @throws SQLException
     * @return
     */
    List<SqlFieldInfo> getFields(String tableName) throws SQLException;

    /**
     * 获取支持数据库列表
     * @return
     */
    List<String> getDbDriverList();
}
