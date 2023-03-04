package org.dows.file.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 文件设置(FileSetting)DTO类
 * @author: VX:PN15855012581
 * @create: 2022-06-03 12:09:25
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "FileSetting对象", description = "文件设置")
public class FileSettingDto implements Serializable {
    private static final long serialVersionUID = 795927225946374576L;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty(value = "主键(数据库自增ID)")
    private Long id;
    @ApiModelProperty(value = "发布编号")
    private String publishNo;
    @ApiModelProperty(value = "配置编号(分布式ID)")
    private String settingNo;
    @ApiModelProperty(value = "文件大小（kb）")
    private String fileSize;
    @ApiModelProperty(value = "发布的文件类型(1：视频，2：文档,3：图片......)")
    private String fileTyp;
    @ApiModelProperty(value = "分辨率（视频文件有）")
    private Integer fileRatio;
    @ApiModelProperty(value = "尺寸单位（kb,mb,tb）")
    private String sizeUnit;
    @ApiModelProperty(value = "编码格式")
    private String encoding;
    @ApiModelProperty(value = "解码格式")
    private String decoding;
    @ApiModelProperty(value = "访问权限(公开|私有|授权访问)")
    private Integer privilege;
    @ApiModelProperty(value = "访问密码")
    private String cipher;
    @ApiModelProperty(value = "切片大小")
    private Integer section;
    @ApiModelProperty(value = "逻辑删除")
    private Boolean deleted;
    @ApiModelProperty(value = "时间戳")
    private Date dt;


}
