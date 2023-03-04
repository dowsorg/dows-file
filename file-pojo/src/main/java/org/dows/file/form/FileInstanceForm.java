package org.dows.file.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @description: 文件实例(FileInstance)Form类
 * @author: VX:PN15855012581
 * @create: 2022-06-03 12:09:19
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "FileInstance对象", description = "文件实例")
public class FileInstanceForm implements Serializable {
    private static final long serialVersionUID = -23776153936204006L;

    @ApiModelProperty(value = "配置编号(分布式ID)")
    private String settingNo;
    @ApiModelProperty(value = "发布编号")
    private String publishNo;
    @ApiModelProperty(value = "实例编号(分布式ID)")
    private String instanceNo;
    @ApiModelProperty(value = "发布者编号(分布式ID)")
    private String accountNo;
    @ApiModelProperty(value = "发布者")
    private String accountName;
    @ApiModelProperty(value = "文件名称")
    private String fileName;
    @ApiModelProperty(value = "文件描述")
    private String descr;
    @ApiModelProperty(value = "发布的文件类型(1：视频，2：文档,3：图片......)")
    private String fileTyp;
    @ApiModelProperty(value = "文件后缀")
    private String suffix;
    @ApiModelProperty(value = "链接地址")
    private String url;
    @ApiModelProperty(value = "视频文件的md5码")
    private String md5;
    @ApiModelProperty(value = "编码格式")
    private String encoding;
    @ApiModelProperty(value = "字节大小")
    private Long bytes;
    @ApiModelProperty(value = "文件信息(尺寸大小，分辨率，...）")
    private String fileInfo;
}

