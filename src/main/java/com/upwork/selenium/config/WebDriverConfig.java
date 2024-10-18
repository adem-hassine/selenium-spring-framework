package com.upwork.selenium.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;



@Slf4j
@Configuration
public class WebDriverConfig {

	private final SeleniumFrameworkProperties seleniumFrameworkProperties;

	public WebDriverConfig(SeleniumFrameworkProperties seleniumFrameworkProperties) {
		this.seleniumFrameworkProperties = seleniumFrameworkProperties;
	}

	@Bean
//	@ConditionalOnExpression("'${my.properties.grid}'.equals('false') and '${my.properties.browser}'.equals('chrome')")
	@Scope("driverscope")
	public WebDriver getChromeDriver() {
		ChromeOptions options = new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		return new ChromeDriver(options);
	}

//	@Bean
//	@ConditionalOnExpression("'${my.properties.grid}'.equals('false') and '${my.properties.browser}'.equals('chrome')")
//	@Scope("driverscope")
//	public WebDriverWait getChromeDriverWait() {
//		return new WebDriverWait(getChromeDriver(), seleniumFrameworkProperties.getExplicitTimeout());
//	}
//
//	@Bean
//	@ConditionalOnProperty(name = "my.properties.grid", havingValue = "true")
//	@Scope("driverscope")
//	public WebDriver getGridDriver() throws MalformedURLException {
//		DesiredCapabilities dc = new DesiredCapabilities();
//		dc.setBrowserName(seleniumFrameworkProperties.getBrowser());
//		dc.setCapability("e34:token", seleniumFrameworkProperties.getGridToken());
//		log.info("Creating Driver");
//		WebDriver driver = new RemoteWebDriver(new URL(seleniumFrameworkProperties.getGridUrl()), dc);
//		((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
//		return driver;
//	}
//
//	@Bean
//	@ConditionalOnProperty(name = "my.properties.grid", havingValue = "true")
//	@Scope("driverscope")
//	public WebDriverWait getGridDriverWait() throws MalformedURLException {
//		return new WebDriverWait(getGridDriver(), seleniumFrameworkProperties.getExplicitTimeout());
//	}
//
//	@Bean
//	@ConditionalOnExpression("'${my.properties.grid}'.equals('false') and '${my.properties.browser}'.equals('edge')")
//	@Scope("driverscope")
//	public WebDriver getEdgeDriver() {
//		EdgeOptions options = new EdgeOptions();
//		options.addArguments("--remote-allow-origins=*");
////		options.addArguments("--headless=new");
//		WebDriverManager.edgedriver().setup();
//		return new EdgeDriver(options);
//	}
//
//	@Bean
//	@ConditionalOnExpression("'${my.properties.grid}'.equals('false') and '${my.properties.browser}'.equals('edge')")
//	@Scope("driverscope")
//	public WebDriverWait getEdgeDriverWait() {
//		return new WebDriverWait(getEdgeDriver(), seleniumFrameworkProperties.getExplicitTimeout());
//	}

}
