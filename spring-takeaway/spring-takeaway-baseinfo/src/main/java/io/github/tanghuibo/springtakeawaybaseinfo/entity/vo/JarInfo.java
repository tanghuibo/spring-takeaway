package io.github.tanghuibo.springtakeawaybaseinfo.entity.vo;

import lombok.Data;

/**
 * @description: jar包信息
 * @author: tanghuibo aa18984850147@qq.com
 * @create: 2019-01-28 22:57
 **/
@Data
public class JarInfo {



    /**
     * jar包路径
     */
    private String jarPath;

    /**
     * jar包名称
     */
    public String getJarName() {
        if(jarPath != null) {
            String[] split = jarPath.split("\\\\");
            if(split.length > 0) {
                return split[split.length - 1];
            }
        }
        return null;
    }


}
