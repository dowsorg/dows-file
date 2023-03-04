package org.dows.file.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 文件目录(FileCatalog)DTO类
 * @author: VX:PN15855012581
 * @create: 2022-06-03 12:09:17
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "FileCatalog对象", description = "文件目录")
public class FileCatalogDto implements Serializable {
    private static final long serialVersionUID = -96336700084038583L;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty(value = "主键(数据库自增ID)")
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty(value = "目录父id")
    private Long pid;
    @ApiModelProperty(value = "目录编号（分布式ID）")
    private String catalogNo;
    @ApiModelProperty(value = "目录名称")
    private String catalogName;
    @ApiModelProperty(value = "目录code")
    private String catelogCode;
    @ApiModelProperty(value = "目录总大下")
    private Integer catelogSize;
    @ApiModelProperty(value = "物理文件路径")
    private String localPath;
    @ApiModelProperty(value = "文件类型")
    private String fileTyp;
    @ApiModelProperty(value = "文件数")
    private Integer fileCount;
    @ApiModelProperty(value = "账号(分布式ID)")
    private String accountNo;
    @ApiModelProperty(value = "应用ID")
    private String appId;
    @ApiModelProperty(value = "租户号")
    private String tentantNo;
    @ApiModelProperty(value = "创建人")
    private String creator;
    @ApiModelProperty(value = "逻辑删除")
    private Boolean deleted;
    @ApiModelProperty(value = "时间戳")
    private Date dt;


}
