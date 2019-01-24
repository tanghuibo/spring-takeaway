package io.github.tanghuibo.springtakeawaybaseinfo.service.impl;

import com.jezhumble.javasysmon.CpuTimes;
import com.jezhumble.javasysmon.JavaSysMon;
import io.github.tanghuibo.springtakeawaybaseinfo.entity.vo.JvmInfo;
import io.github.tanghuibo.springtakeawaybaseinfo.service.SystemBaseInfoService;

import java.util.Map;
import java.util.Properties;

/**
 * @description:
 * @author: tanghuibo aa18984850147@qq.com
 * @create: 2019-01-20 23:49
 **/
public class SystemBaseInfoServiceImpl implements SystemBaseInfoService {

    JavaSysMon javaSysMon = new JavaSysMon();

    volatile CpuTimes cpuTimes = javaSysMon.cpuTimes();

    /**
     * 获取基础配置信息
     * @return
     */
    @Override
    public Map<Object, Object> getProperties() {
        Properties properties = System.getProperties();
        return properties;
    }

    @Override
    public JvmInfo getRunTimeInfo() {

        JvmInfo jvmInfo = new JvmInfo();

        jvmInfo.setCpuFrequencyInHz(javaSysMon.cpuFrequencyInHz());
        jvmInfo.setNumCpus(javaSysMon.numCpus());
        jvmInfo.setMemoryFreeBytes(javaSysMon.physical().getFreeBytes());
        jvmInfo.setMemoryTotalBytes(javaSysMon.physical().getTotalBytes());
        jvmInfo.setOsName(javaSysMon.osName());
        CpuTimes cpuTimes = javaSysMon.cpuTimes();

        jvmInfo.setCpuUsage(cpuTimes.getCpuUsage(this.cpuTimes));
        this.cpuTimes = cpuTimes;

        return jvmInfo;


    }
}
