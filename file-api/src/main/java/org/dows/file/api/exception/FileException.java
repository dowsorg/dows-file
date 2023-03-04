package org.dows.file.api.exception;

import org.dows.framework.api.StatusCode;
import org.dows.framework.api.exceptions.BaseException;

/**
 * @author lait.zhang@gmail.com
 * @description: 文件服务器异常, 用于表示文件服务器出现的异常并且携带异常响应码
 * @weixin SH330786
 * @date 6/3/2022
 */
public class FileException extends BaseException {
    //异常响应码
    private int code;

    public FileException(int code, String message) {
        super(message);
        this.code = code;
    }

    public FileException(String msg) {
        super(msg);
    }

    public FileException(Throwable throwable) {
        super(throwable);
    }


    public FileException(StatusCode statusCode) {
        super(statusCode);
    }

    public FileException(StatusCode statusCode, String msg) {
        super(statusCode, msg);
    }

    public FileException(StatusCode statusCode, Exception exception) {
        super(statusCode, exception);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
