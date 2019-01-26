package io.github.tanghuibo.result.util;

import io.github.tanghuibo.result.entity.Result;

import java.util.Properties;

/**
 * @description: 返回实体类工厂
 * @author: tanghuibo aa18984850147@qq.com
 * @create: 2019-01-20 23:23
 **/
public class ResultTool {

    /**
     * 返回成功
     *
     * @return
     */
    public static Result success() {

        return new Result(true, "", null);
    }

    /**
     * 返回成功
     *
     * @param data
     * @param <DATA>
     * @return
     */
    public static <DATA> Result<DATA> successData(DATA data) {
        return new Result(true, "", data);
    }

    /**
     * 返回失败
     *
     * @param message
     * @return
     */
    public static Result fail(String message) {
        return new Result(false, message, null);


    }
}
