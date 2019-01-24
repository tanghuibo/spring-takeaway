package io.github.tanghuibo.springtakeawaybaseinfo.entity.vo;

import com.jezhumble.javasysmon.CpuTimes;
import lombok.Data;

/**
 * @description: jvm系统消息
 * @author: tanghuibo aa18984850147@qq.com
 * @create: 2019-01-25 04:46
 **/
@Data
public class JVMInfo {

    /**
     * cpu频率
     */
    private Long cpuFrequencyInHz;

    /**
     * cpu核心数
     */
    private Integer numCpus;

    /**
     * 空闲内存字节数
     */
    private Long memoryFreeBytes;

    /**
     * 总内存字节数
     */
    private Long memoryTotalBytes;

    /**
     * 系统名称
     */
    private String osName;

    /**
     * cpu使用情况
     */
    private Float cpuUsage;

    /**
     * 内存使用情况
     * @return
     */
    public Float getMemoryUsage() {
        return (float)( 1 - (memoryFreeBytes * 1.0 / memoryTotalBytes));
    }
}
