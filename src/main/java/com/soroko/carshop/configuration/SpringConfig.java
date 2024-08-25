package com.soroko.carshop.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author yuriy.soroko
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan("com.soroko.carshop")
public class SpringConfig {
}
