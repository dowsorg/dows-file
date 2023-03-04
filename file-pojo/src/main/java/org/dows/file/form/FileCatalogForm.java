package org.dows.file.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @description: 文件目录(FileCatalog)Form类
 * @author: VX:PN15855012581
 * @create: 2022-06-03 12:09:18
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "FileCatalog对象", description = "文件目录")
public class FileCatalogForm implements Serializable {
    private static final long serialVersionUID = -30266902265115556L;

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
}

