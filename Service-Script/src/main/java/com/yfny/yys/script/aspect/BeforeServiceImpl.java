package com.yfny.yys.script.aspect;

import com.yfny.utilscommon.basemvc.producer.BeforeBaseServiceImpl;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 业务通用切面处理
 * Author auto
 * Date  2020-11-17
 */
@Aspect
@Component
public class BeforeServiceImpl extends BeforeBaseServiceImpl {

    @Override
    public String getPackageName() {
        return "com.yfny.yys.script";
    }

    @Override
    public Class<?> getClazz(String className) {
    try {
        Class clazz = Class.forName(className);
        return clazz;
    } catch (Exception e) {
        e.printStackTrace();
    }
        return null;
    }

}
