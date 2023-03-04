package org.dows.file.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.file.entity.FileCatalog;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;

/**
 * 文件目录(FileCatalog)
 *
 * @author VX:PN15855012581
 * @since 2022-06-03 12:09:17
 */
@Mapper
public interface FileCatalogMapper extends MybatisCrudMapper<FileCatalog> {

}
