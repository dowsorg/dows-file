package org.dows.file.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.dows.framework.api.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lait.zhang@gmail.com
 * @description: TODO
 * @weixin SH330786
 * @date 3/13/2022
 */
@Slf4j
@RestController
@Api(tags = " 文件夹管理")
public class FolderRest {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 创建文件夹
     *
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/newFolder")
    @ApiOperation("创建文件夹")
    public Response creatFolder(HttpServletRequest request) throws IOException {
        String parent = request.getParameter("filepath");
        String foldername = request.getParameter("foldername");
        String time = formatter.format(new Date());
        if (foldername == null || foldername.length() <= 0) {
            foldername = "新建文件夹" + "_" + time;
        }
        if (parent == null || parent.length() <= 0) {
        }
        File file = new File("");
        String folderpath = file.getCanonicalPath() + File.separator +
                "UsersFiles" + File.separator + parent + File.separator + foldername + File.separator;
        file = new File(folderpath);
        if (!file.exists()) {
            file.mkdirs();
            log.info("filepath :   " + folderpath);
        } else {
            file = new File(folderpath + File.separator + "2");
            log.info("filepath2 :   " + folderpath);
            file.mkdirs();
        }
        return Response.ok();
    }


}
