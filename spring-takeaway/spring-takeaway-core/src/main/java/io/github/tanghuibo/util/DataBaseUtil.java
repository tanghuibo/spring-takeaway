package io.github.tanghuibo.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 数据库工具
 * @author: tanghuibo aa18984850147@qq.com
 * @create: 2019-01-27 16:56
 **/
public class DataBaseUtil {

    /**
     * 数据库resultSet转map
     *
     * @param resultSet
     * @return
     * @throws SQLException
     */
    public static List<Map<String, Object>> resultSetToListMap(ResultSet resultSet) throws SQLException {

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(resultSet.getRow());

        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        while (resultSet.next()) {

            Map<String, Object> map = new HashMap<String, Object>(columnCount);
            for (int i = 1; i <= columnCount; i++) {
                map.put(StringUtil.underlineToCamel(metaData.getColumnName(i)), resultSet.getObject(i));
            }
            list.add(map);


        }

        return list;
    }
}
