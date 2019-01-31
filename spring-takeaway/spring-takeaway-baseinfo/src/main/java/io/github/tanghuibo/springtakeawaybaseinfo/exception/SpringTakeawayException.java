package io.github.tanghuibo.springtakeawaybaseinfo.exception;

import lombok.Data;

/**
 * @description:
 * @author: tanghuibo aa18984850147@qq.com
 * @create: 2019-02-01 01:34
 **/
@Data
public class SpringTakeawayException extends RuntimeException {
    /**
     * 错误信息
     */
    private String error;

    public SpringTakeawayException(String error) {
        this.error = error;
    }
}
