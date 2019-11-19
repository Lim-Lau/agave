package com.ansimpleasy.agave.ans.spr;

import org.springframework.core.io.ProtocolResolver;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

/**
 * @author LiuCan
 * @date 2019/11/19 16:48
 */
public class MyProtocolResolver implements ProtocolResolver {
   final String ANS = "ans";
   final String CLASSPATH = "classpath";

    @Override
    public Resource resolve(String location, ResourceLoader resourceLoader) {
        if (location.startsWith(ANS)) {
            return resourceLoader.getResource(location.replace(ANS, CLASSPATH));
        }
        return null;
    }
}
