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
 * 文件目录(FileCatalog)表实体类
 *
 * @author VX:PN15855012581
 * @since 2022-06-03 12:09:17
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "FileCatalog对象", description = "文件目录")
public class FileCatalog implements CrudEntity {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("主键(数据库自增ID)")
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("目录父id")
    private Long pid;
    @ApiModelProperty("目录编号（分布式ID）")
    private String catalogNo;
    @ApiModelProperty("目录名称")
    private String catalogName;
    @ApiModelProperty("目录code")
    private String catelogCode;
    @ApiModelProperty("目录总大下")
    private Integer catelogSize;
    @ApiModelProperty("物理文件路径")
    private String localPath;
    @ApiModelProperty("文件类型")
    private String fileTyp;
    @ApiModelProperty("文件数")
    private Integer fileCount;
    @ApiModelProperty("账号(分布式ID)")
    private String accountNo;
    @ApiModelProperty("应用ID")
    private String appId;
    @ApiModelProperty("租户号")
    private String tentantNo;
    @ApiModelProperty("创建人")
    private String creator;
    @JsonIgnore
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Boolean deleted;
    @TableField(fill = FieldFill.INSERT)
    private Date dt;
}
