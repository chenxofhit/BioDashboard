package com.bio.common.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

@ConditionalOnProperty(prefix = "bio", name = "spring-session-open", havingValue = "true")
public class SpringSessionConfig {

}
