package com.example.member.config;

import com.example.common.security.auth.config.CurrentMemberWebConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(CurrentMemberWebConfig.class)
public class WebMvcConfig {
}
