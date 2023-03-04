package org.dows.file.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @description: 文件统计(FileStatistics)Form类
 * @author: VX:PN15855012581
 * @create: 2022-06-03 12:09:28
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "FileStatistics对象", description = "文件统计")
public class FileStatisticsForm implements Serializable {
    private static final long serialVersionUID = 852810186332400798L;

    @ApiModelProperty(value = "实例编号(分布式ID)")
    private String instanceNo;
    @ApiModelProperty(value = "发布者编号(分布式ID)")
    private String accountNo;
    @ApiModelProperty(value = "统计类型(0:pv,1:uv,2:播放,3:收藏)")
    private Integer styp;
}

