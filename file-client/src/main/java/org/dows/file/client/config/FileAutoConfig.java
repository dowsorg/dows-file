package org.dows.file.client.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author lait.zhang@gmail.com
 * @description: TODO
 * @weixin SH330786
 * @date 6/3/2022
 */
@Configuration
@EnableConfigurationProperties(FileClientProperties.class)
@ComponentScan(basePackages = {"org.dows.file.client"})
public class FileAutoConfig {

}
