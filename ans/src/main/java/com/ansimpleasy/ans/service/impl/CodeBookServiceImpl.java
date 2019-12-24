package com.ansimpleasy.ans.service.impl;

import com.ansimpleasy.ans.service.ICodeBookService;
import com.google.common.collect.Maps;
import org.nutz.lang.*;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.resource.Scans;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author LiuCan
 * @date 2019/12/23 1:48
 */
@Service
public class CodeBookServiceImpl implements ICodeBookService {

    private Map<String, Object> codeBookMap;
    Log logger = Logs.get();

    @PostConstruct
    public void init() {
        Tasks.scheduleAtFixedRate(() -> {
            logger.debug("init codebook data...");
            codeBookMap = Maps.newHashMap();
            Lang.each(Scans.me().scanPackage("com.ansimpleasy.ans.enums"), new Each<Class<?>>() {

                @Override
                public void invoke(int index, Class<?> ele, int length) throws ExitLoop, ContinueLoop, LoopException {
                    xxx(ele);
                }
            });
            Lang.each(Scans.me().scanPackage("BOOT-INF.classes.com.ansimpleasy.ans.enums"), new Each<Class<?>>() {

                @Override
                public void invoke(int index, Class<?> ele, int length) throws ExitLoop, ContinueLoop, LoopException {
                    xxx(ele);
                }
            });
        }, 30, 600, TimeUnit.SECONDS);
    }

    @Override
    public Map<String, Object> getCodeBook() {
        return codeBookMap;
    }

    /**
     * invoke
     * @author LiuCan
     * @date 2019/12/23 15:00
     * @param ele
     * @return void
     */
    private void xxx(Class<?> ele) {
        if (ele.isEnum()) {
            try {
                codeBookMap.put(String.format("%ss", Strings.lowerFirst(ele.getSimpleName())),
                        Mirror.me(ele).findMethod("values").invoke(null));
            }
            catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException e) {
                throw Lang.wrapThrow(e);
            }
        }

    }
}
