package org.dows.file.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 文件统计(FileStatistics)DTO类
 * @author: VX:PN15855012581
 * @create: 2022-06-03 12:09:27
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "FileStatistics对象", description = "文件统计")
public class FileStatisticsDto implements Serializable {
    private static final long serialVersionUID = -27948823197449875L;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty(value = "主键(数据库自增ID)")
    private Long id;
    @ApiModelProperty(value = "实例编号(分布式ID)")
    private String instanceNo;
    @ApiModelProperty(value = "发布者编号(分布式ID)")
    private String accountNo;
    @ApiModelProperty(value = "统计类型(0:pv,1:uv,2:播放,3:收藏)")
    private Integer styp;
    @ApiModelProperty(value = "逻辑删除")
    private Boolean deleted;
    @ApiModelProperty(value = "时间戳")
    private Date dt;


}
