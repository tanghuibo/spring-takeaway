package io.github.tanghuibo.springtakeawaybaseinfo.service.impl;

import com.jezhumble.javasysmon.CpuTimes;
import com.jezhumble.javasysmon.JavaSysMon;
import io.github.tanghuibo.springtakeawaybaseinfo.entity.vo.ClassInfo;
import io.github.tanghuibo.springtakeawaybaseinfo.entity.vo.JvmInfo;
import io.github.tanghuibo.springtakeawaybaseinfo.service.SystemBaseInfoService;
import io.github.tanghuibo.util.ClassUtil;

import java.util.*;

/**
 * @description:
 * @author: tanghuibo aa18984850147@qq.com
 * @create: 2019-01-20 23:49
 **/
public class SystemBaseInfoServiceImpl implements SystemBaseInfoService {

    JavaSysMon javaSysMon = new JavaSysMon();

    volatile CpuTimes cpuTimes = javaSysMon.cpuTimes();

    public static final String CLASSLOADER_CLASSES = "classes";

    /**
     * 获取基础配置信息
     *
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

    @Override
    public List<ClassInfo> getClassByClassLoader() {

        List<ClassInfo> list = new ArrayList<>();
        ClassLoader classLoader = this.getClass().getClassLoader();


        while (classLoader != null) {
            Vector<Class> classes = (Vector<Class>) ClassUtil.getPropertyByFiledName(classLoader, ClassLoader.class, CLASSLOADER_CLASSES);
            Enumeration<Class> elements = classes.elements();

            String classLoaderName = classLoader.getClass().getSimpleName();
            while (elements.hasMoreElements()) {
                Class clazz = elements.nextElement();
                ClassInfo classInfo = new ClassInfo();
                classInfo.setClassLoaderName(classLoaderName);
                classInfo.setClassName(clazz.getName());
                classInfo.setSimpleClassName(clazz.getSimpleName());
                list.add(classInfo);
            }


            classLoader = classLoader.getParent();

        }

        return list;
    }


}
