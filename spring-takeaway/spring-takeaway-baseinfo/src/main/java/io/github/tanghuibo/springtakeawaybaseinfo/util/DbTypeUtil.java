package io.github.tanghuibo.springtakeawaybaseinfo.util;

import io.github.tanghuibo.springtakeawaybaseinfo.config.DbType;

/**
 * @description: 数据库类型工具
 * @author: tanghuibo aa18984850147@qq.com
 * @create: 2019-02-01 01:21
 **/
public class DbTypeUtil {

    /**
     * 通过驱动器名称查询数据库类型
     *
     * @param driverName
     * @param connectionClassName
     * @return
     */
    public static DbType getDbTypeByDriverClassName(String driverName, String connectionClassName) {
        DbType[] dbTypes = DbType.values();
        connectionClassName = connectionClassName.toUpperCase();
        for (DbType dbType: dbTypes) {
            if(dbType.getDriverList().contains(driverName)) {
                return dbType;
            }

            if(connectionClassName.contains(dbType.getDesc())) {
                return dbType;
            }

        }
        return null;
    }

    DbType getDbTypeByDesc(String desc) {
        DbType[] dbTypes = DbType.values();
        for (DbType dbType: dbTypes) {
            if(dbType.getDriverList().equals(desc)) {
                return dbType;
            }

        }
        return null;
    }
}
