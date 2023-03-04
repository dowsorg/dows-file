package org.dows.file.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @description: 文件发布(FilePublish)Form类
 * @author: VX:PN15855012581
 * @create: 2022-06-03 12:09:23
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "FilePublish对象", description = "文件发布")
public class FilePublishForm implements Serializable {
    private static final long serialVersionUID = 711666122098022954L;

    @ApiModelProperty(value = "目录编号（分布式ID）")
    private String catalogNo;
    @ApiModelProperty(value = "发布编号")
    private String publishNo;
    @ApiModelProperty(value = "发布者编号(分布式ID)")
    private String accountNo;
    @ApiModelProperty(value = "文件的md5码")
    private String md5;
    @ApiModelProperty(value = "文件名称（视频文件就是视频名称）")
    private String fileName;
    @ApiModelProperty(value = "发布的文件类型(1：视频，2：文档,3：图片......)")
    private String fileTyp;
    @ApiModelProperty(value = "文件分辨率(默认为null,视频文件才会有)")
    private Integer fileRatio;
    @ApiModelProperty(value = "编码格式")
    private String encoding;
    @ApiModelProperty(value = "字节大小")
    private Long bytes;
    @ApiModelProperty(value = "文件url")
    private String url;
    @ApiModelProperty(value = "文件后缀")
    private String suffix;
    @ApiModelProperty(value = "设备编号")
    private String deviceId;
    @ApiModelProperty(value = "发布的系统")
    private Integer osTyp;
    @ApiModelProperty(value = "发布状态")
    private Integer status;
}

