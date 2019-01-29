package io.github.tanghuibo.springtakeawaybaseinfo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.github.tanghuibo.springtakeawaybaseinfo.entity.vo.ClassInfo;
import io.github.tanghuibo.springtakeawaybaseinfo.entity.vo.JarInfo;
import io.github.tanghuibo.springtakeawaybaseinfo.entity.vo.JvmInfo;
import io.github.tanghuibo.springtakeawaybaseinfo.service.SystemBaseInfoService;
import io.github.tanghuibo.util.ClassUtil;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.lang.management.ManagementFactory;
import java.util.*;

/**
 * @description:
 * @author: tanghuibo aa18984850147@qq.com
 * @create: 2019-01-20 23:49
 **/
@Service("systemBaseInfoService")
@Lazy
public class SystemBaseInfoServiceImpl implements SystemBaseInfoService {

    /**
     * classloader中存放class列表的字段名
     */
    public static final String CLASSLOADER_CLASSES = "classes";

    /**
     * cpu内核数 key
     */
    private static final String AVAILABLE_PROCESSORS = "availableProcessors";

    /**
     * 空闲内存大小 key
     */
    private static final String FREE_PHYSICAL_MEMORY_SIZE = "freePhysicalMemorySize";

    /**
     * cpu使用率 key
     */
    private static final String SYSTEM_CPU_LOAD = "systemCpuLoad";

    /**
     * 总内存大小key
     */
    private static final String TOTALP_HYSICAL_MEMORYS_IZE = "totalPhysicalMemorySize";

    /**
     * 操作系统名称
     */
    private static final String NAME = "name";

    /**
     * class path；用于获取加载的jar包
     */
    public static final String JAVA_CLASS_PATH = "java.class.path";

    /**
     * jar包文件结尾
     */
    private static final String JAR_TYPE = ".JAR";

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
        String jsonString = JSON.toJSONString(ManagementFactory.getOperatingSystemMXBean());
        JSONObject jsonObject = JSON.parseObject(jsonString);
        jvmInfo.setNumCpus(jsonObject.getInteger(AVAILABLE_PROCESSORS));
        jvmInfo.setMemoryFreeBytes(jsonObject.getLong(FREE_PHYSICAL_MEMORY_SIZE));
        jvmInfo.setCpuUsage(jsonObject.getFloat(SYSTEM_CPU_LOAD));
        jvmInfo.setMemoryTotalBytes(jsonObject.getLong(TOTALP_HYSICAL_MEMORYS_IZE));
        jvmInfo.setOsName(jsonObject.getString(NAME));


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

    @Override
    public List<JarInfo> getJarList() {
        List<JarInfo> jarInfos = new ArrayList<>();
        String jarPathStrings = System.getProperty(JAVA_CLASS_PATH);
        String[] jarPaths = jarPathStrings.split(";");
        for (String jarPath:jarPaths) {
            if(jarPath.toUpperCase().endsWith(JAR_TYPE)) {
                JarInfo jarInfo = new JarInfo();
                jarInfo.setJarPath(jarPath);
                jarInfos.add(jarInfo);
            }

        }
        return jarInfos;
    }


}
