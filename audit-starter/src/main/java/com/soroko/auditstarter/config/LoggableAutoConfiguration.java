package com.soroko.auditstarter.config;

import com.soroko.auditstarter.aspect.LoggableAspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yuriy.soroko
 */
@Configuration
public class LoggableAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public LoggableAspect loggableAspect() {
        return new LoggableAspect();
    }
}
