package com.upwork.selenium.config;

import java.time.Duration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("selenium.framework")
@Component
@Getter
@Setter
public class SeleniumFrameworkProperties {
	private String browser;
	private Duration explicitTimeout;
	private String demoUrl;
}
