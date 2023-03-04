package org.dows.file.api.constants;

import lombok.Getter;
import org.dows.framework.api.StatusCode;

/**
 * 响应码常量
 */
public enum FileStatusCode implements StatusCode {

    /**
     * 服务错误
     */
    SERVER_ERROR(-1, "服务错误"),

    /**
     * 参数错误
     */
    BAD_PARAM(-2, "参数错误"),

    /**
     * 文件名格式错误
     */
    BAD_FILENAME_FORMAT(-3, "文件名格式错误"),

    /**
     * 文件类型错误
     */
    BAD_FILE_TYPE(-4, "文件类型错误"),

    /**
     * 文件不存在
     */
    NOT_FOUND_FILE(-5, ""),

    /**
     * 分片已经存在
     */
    CHUNK_EXSIT(1, "分片已经存在"),

    /**
     * 文件已经存在
     */
    FILE_EXSIT(2, "文件已经存在"),

    /**
     * 上传成功
     */
    UPLOAD_SUCCESS(3, "上传成功"),

    /**
     * 上传失败
     */
    UPLOAD_ERROR(4, "上传失败:%s");
    @Getter
    Integer code;
    @Getter
    String descr;

    FileStatusCode(int code, String descr) {
        this.code = code;
        this.descr = descr;
    }

}
