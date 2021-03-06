package io.github.tanghuibo.springtakeawaybaseinfo.controller;

import io.github.tanghuibo.result.entity.Result;
import io.github.tanghuibo.result.util.ResultTool;
import io.github.tanghuibo.springtakeawaybaseinfo.config.ConstantConfig;
import io.github.tanghuibo.springtakeawaybaseinfo.entity.vo.SqlFieldInfo;
import io.github.tanghuibo.springtakeawaybaseinfo.entity.vo.TableInfo;
import io.github.tanghuibo.springtakeawaybaseinfo.service.DataBaseInfoService;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.sql.SQLException;
import java.util.List;

/**
 * @description: 数据库基本信息controller
 * @author: tanghuibo aa18984850147@qq.com
 * @create: 2019-01-21 22:37
 **/
@RestController
@RequestMapping(ConstantConfig.CONTENT_PATH + "/database")
@Lazy
@ApiIgnore
public class DataBaseInfoController {

    DataBaseInfoService dataBaseInfoService;

    public DataBaseInfoController(@Lazy DataBaseInfoService dataBaseInfoService) {
        this.dataBaseInfoService = dataBaseInfoService;
    }

    /**
     * 获取数据库中所有表格信息
     *
     * @return
     */
    @GetMapping("tables")
    public Result<List<TableInfo>> getTables() throws SQLException {
        List<TableInfo> beans = dataBaseInfoService.getTables();
        return ResultTool.successData(beans);
    }

    /**
     * 获取数据库中表格字段信息
     * @param tableName 表格名称
     * @return
     */
    @GetMapping("fields")
    public Result<List<SqlFieldInfo>> getFields(String tableName) throws SQLException {
        List<SqlFieldInfo> beans = dataBaseInfoService.getFields(tableName);
        return ResultTool.successData(beans);
    }

    /**
     * 获取数据库支持的数据库类型列表
     *
     * @return
     */
    @GetMapping("driver-list")
    public Result<List<String>> getDbDriverList() throws SQLException {
        List<String> dbTypeList = dataBaseInfoService.getDbDriverList();
        return ResultTool.successData(dbTypeList);
    }

}
