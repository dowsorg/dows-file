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
 * 文件统计(FileStatistics)表实体类
 *
 * @author VX:PN15855012581
 * @since 2022-06-03 12:09:27
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "FileStatistics对象", description = "文件统计")
public class FileStatistics implements CrudEntity {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("主键(数据库自增ID)")
    private Long id;
    @ApiModelProperty("实例编号(分布式ID)")
    private String instanceNo;
    @ApiModelProperty("发布者编号(分布式ID)")
    private String accountNo;
    @ApiModelProperty("统计类型(0:pv,1:uv,2:播放,3:收藏)")
    private Integer styp;
    @JsonIgnore
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Boolean deleted;
    @TableField(fill = FieldFill.INSERT)
    private Date dt;
}
