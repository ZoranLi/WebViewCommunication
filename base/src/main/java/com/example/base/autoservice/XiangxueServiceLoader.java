package com.example.base.autoservice;

import java.util.ServiceLoader;

public class XiangxueServiceLoader {
    
    private XiangxueServiceLoader() {

    }

    public static <S> S load(Class<S> service) {
        try {
            return ServiceLoader.load(service).iterator().next();
        } catch (Exception e) {
            return null;
        }
    }
}
