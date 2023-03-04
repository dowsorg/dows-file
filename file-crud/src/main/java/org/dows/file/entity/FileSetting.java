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
 * 文件设置(FileSetting)表实体类
 *
 * @author VX:PN15855012581
 * @since 2022-06-03 12:09:25
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "FileSetting对象", description = "文件设置")
public class FileSetting implements CrudEntity {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("主键(数据库自增ID)")
    private Long id;
    @ApiModelProperty("发布编号")
    private String publishNo;
    @ApiModelProperty("配置编号(分布式ID)")
    private String settingNo;
    @ApiModelProperty("文件大小（kb）")
    private String fileSize;
    @ApiModelProperty("发布的文件类型(1：视频，2：文档,3：图片......)")
    private String fileTyp;
    @ApiModelProperty("分辨率（视频文件有）")
    private Integer fileRatio;
    @ApiModelProperty("尺寸单位（kb,mb,tb）")
    private String sizeUnit;
    @ApiModelProperty("编码格式")
    private String encoding;
    @ApiModelProperty("解码格式")
    private String decoding;
    @ApiModelProperty("访问权限(公开|私有|授权访问)")
    private Integer privilege;
    @ApiModelProperty("访问密码")
    private String cipher;
    @ApiModelProperty("切片大小")
    private Integer section;
    @JsonIgnore
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Boolean deleted;
    @TableField(fill = FieldFill.INSERT)
    private Date dt;
}
