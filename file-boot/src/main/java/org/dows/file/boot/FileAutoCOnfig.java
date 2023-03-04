package org.dows.file.boot;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author lait.zhang@gmail.com
 * @description: TODO
 * @weixin SH330786
 * @date 6/6/2022
 */
@Configuration
@ComponentScan({
        "org.dows.file.boot",
        "org.dows.file.biz",
        "org.dows.file.crud"
})
public class FileAutoCOnfig {


}
