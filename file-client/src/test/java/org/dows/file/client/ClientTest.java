package org.dows.file.client;

import org.dows.file.client.config.FileAutoConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author lait.zhang@gmail.com
 * @description: TODO
 * @weixin SH330786
 * @date 6/3/2022
 */

public class ClientTest {

    @Test
    public void testClient() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(FileAutoConfig.class);
        FileClient bean = applicationContext.getBean(FileClient.class);
        System.out.println(bean);
    }

}
