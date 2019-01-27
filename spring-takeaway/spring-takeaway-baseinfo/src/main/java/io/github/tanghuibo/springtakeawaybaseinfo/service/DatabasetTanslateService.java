package io.github.tanghuibo.springtakeawaybaseinfo.service;

import io.github.tanghuibo.springtakeawaybaseinfo.entity.vo.TableInfo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @description:数据库翻译机
 * @author: tanghuibo aa18984850147@qq.com
 * @create: 2019-01-27 16:24
 **/
public interface DatabasetTanslateService {


    /**
     * 排序优先级
     * @return
     */
    default int getOrder() {
        return 1;
    };

    /**
     * 是否匹配
     * @param connection
     * @return
     */
    boolean isMatching(Connection connection);

    /**
     * 获取表格列表
     * @param connection
     * @return
     */
    List<TableInfo> getTableList(Connection connection);
}
