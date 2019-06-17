package com.logsuficate;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.turbo.TurboFilter;
import ch.qos.logback.core.spi.FilterReply;
import org.slf4j.Marker;

import java.lang.reflect.Field;

public class LogsuficateInterceptor extends TurboFilter {

    public FilterReply decide(Marker marker, Logger logger, Level level, String format, Object[] params, Throwable t) {
        if (params != null) {
            boolean logContainsSensitiveFields = false;
            for (int i = 0; i < params.length; i++) {
                for (Field field : params[i].getClass().getDeclaredFields()) {
                    field.setAccessible(true);
                    if (field.isAnnotationPresent(Logsuficate.class)) {
                        params[i] = field.getAnnotation(Logsuficate.class).value();
                        logContainsSensitiveFields = true;
                    }
                }
            }
            if (logContainsSensitiveFields) {
                logger.debug(format, params);
                return FilterReply.DENY;
            }
        }
        return FilterReply.ACCEPT;
    }
}

