package com.ansimpleasy.agave.ans;

import com.ansimpleasy.agave.ans.spr.MyProtocolResolver;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import java.io.IOException;

/**
 * @author LiuCan
 * @date 2019/11/19 15:06
 */
public class MyTest {
    @Test
    public void test1() {
        Resource resource = new ClassPathResource("config/dev/readConfig.xml");
        print(resource);
    }

    @Test
    public void test2() {
        Resource resource = new ClassPathResource("readConfig.xml", MyTest.class);
        print(resource);
    }

    @Test
    public void test4() {
        // 使用DefaultResourceLoader加载
        Resource resource = new DefaultResourceLoader().getResource("config/dev/readConfig.xml");
        print(resource);
    }

    @Test
    public void test5() {
        // 使用自定义协议解析器加载
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
        resourceLoader.addProtocolResolver(new MyProtocolResolver());
        Resource resource = resourceLoader.getResource("ans:config/dev/readConfig.xml");
        print(resource);
    }
    private void print(Resource resource) {
        byte[] read = new byte[10000];
        try {
            resource.getInputStream().read(read, 0, read.length);
            System.out.println(new String(read));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
