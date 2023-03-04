package org.dows.file.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.dows.framework.crud.mybatis.CrudEntity;

import java.util.Date;

/**
 * 文件发布(FilePublish)表实体类
 *
 * @author VX:PN15855012581
 * @since 2022-06-03 12:09:20
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "FilePublish对象", description = "文件发布")
public class FilePublish implements CrudEntity {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("主键(数据库自增ID)")
    private Long id;
    @ApiModelProperty("目录编号（分布式ID）")
    private String catalogNo;
    @ApiModelProperty("发布编号")
    private String publishNo;
    @ApiModelProperty("发布者编号(分布式ID)")
    private String accountNo;
    @ApiModelProperty("文件的md5码")
    private String md5;
    @ApiModelProperty("文件名称（视频文件就是视频名称）")
    private String fileName;
    @ApiModelProperty("发布的文件类型(1：视频，2：文档,3：图片......)")
    private String fileTyp;
    @ApiModelProperty("文件分辨率(默认为null,视频文件才会有)")
    private Integer fileRatio;
    @ApiModelProperty("编码格式")
    private String encoding;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("字节大小")
    private Long bytes;
    @ApiModelProperty("文件url")
    private String url;
    @ApiModelProperty("文件后缀")
    private String suffix;
    @ApiModelProperty("设备编号")
    private String deviceId;
    @ApiModelProperty("发布的系统")
    private Integer osTyp;
    @ApiModelProperty("发布状态")
    private Integer status;
    @JsonIgnore
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Boolean deleted;
    @TableField(fill = FieldFill.INSERT)
    private Date dt;
}
