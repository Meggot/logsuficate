package com.logsuficate;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.turbo.TurboFilter;
import ch.qos.logback.core.spi.FilterReply;
import org.slf4j.Marker;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class LogsuficateInterceptor extends TurboFilter {

    public FilterReply decide(Marker marker, Logger logger, Level level, String format, Object[] params, Throwable t) {
        if (params != null) {
            boolean anyContainsValues = false;
            Object[] maker = params.clone();
            for (int i = 0; i < params.length; i++) {
                boolean logContainsSensitiveFields = false;
                Map<String, String> nameToMask = new HashMap<String, String>();
                for (Field field : params[i].getClass().getDeclaredFields()) {
                    field.setAccessible(true);
                    if (field.isAnnotationPresent(Logsuficate.class)) {
                        nameToMask.put(field.getName(), field.getAnnotation(Logsuficate.class).value());
                        anyContainsValues = true;
                    }
                }
            }
            if (anyContainsValues) {
                logger.debug(format, maker);
                return FilterReply.DENY;
            }
        }
        return FilterReply.ACCEPT;
    }
}

