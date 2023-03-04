package org.dows.file.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

/**
 * 文件模型
 */
@Data
public class FileInfo implements Serializable {
    //文件编号
    private String fileNo;
    // 文件名称
    private String fileName;
    //类型(视频，音频，doc...)
    private String fileTyp;
    // 文件大小
    private Long fileSize;
    // 文件MD5
    private String md5;
    // url
    private String url;
    // 文件后缀
    private String suffix;
    // 文件编码
    private String encoding;
    //文件描述json
    private String descr;
    // 大小单位
    private String sizeUnit;
    // 其他信息
    private String accountNo;
    private String appId;

    @JsonIgnore
    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
        if (1024 * 1024 > this.fileSize && this.fileSize >= 1024) {
            this.sizeUnit = String.format("%.2f", this.fileSize.doubleValue() / 1024) + "KB";
        } else if (1024 * 1024 * 1024 > this.fileSize && this.fileSize >= 1024 * 1024) {
            this.sizeUnit = String.format("%.2f", this.fileSize.doubleValue() / (1024 * 1024)) + "MB";
        } else if (this.fileSize >= 1024 * 1024 * 1024) {
            this.sizeUnit = String.format("%.2f", this.fileSize.doubleValue() / (1024 * 1024 * 1024)) + "GB";
        } else {
            this.sizeUnit = this.fileSize.toString() + "B";
        }
    }


}
