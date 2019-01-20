package huibo.github.result.entity;

import lombok.Data;

/**
 * @description: 返回实体类
 * @author: tanghuibo aa18984850147@qq.com
 * @create: 2019-01-20 23:21
 **/
@Data
public class Result<DATA> {

    /**
     * 是否成功
     */
    private Boolean success;

    /**
     * 数据
     */
    private DATA data;

    /**
     * 数据
     */
    private String message;

    public Result(Boolean success, String message, DATA data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

}
