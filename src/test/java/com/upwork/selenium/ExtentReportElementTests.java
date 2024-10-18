package com.upwork.selenium;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.upwork.selenium.config.SeleniumFrameworkProperties;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@SeleniumTest
@Listeners(TestListener.class)
public class ExtentReportElementTests extends AbstractTestNGSpringContextTests {
    @Autowired
    private WebDriver driver;
    @Autowired
    private SeleniumFrameworkProperties seleniumFrameworkProperties;
    public ExtentSparkReporter htmlReporter;
    public ExtentReports extent;
    public ExtentTest test;

    @BeforeClass
    public void setUp() {
        this.htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/target/" + "/extent-output/" + "Upwork Selenium Framework" + ".html");
        this.htmlReporter.config().setDocumentTitle("Upwork Selenium Framework");
        this.htmlReporter.config().setReportName("Upwork Selenium Framework");
        this.htmlReporter.config().setTheme(Theme.DARK);


        this.extent = new ExtentReports();
        this.extent.attachReporter(this.htmlReporter);
    }

    @AfterTest
    public void tearDown() {
        this.extent.flush();
    }

    @Test(testName = "Extent Title Test")
    public void sanityCheck() {
        test = extent.createTest("Extent OPEC Fund", "Extent Initial feature");
        driver.navigate().to(seleniumFrameworkProperties.getDemoUrl());
        assertThat(driver.getTitle(), is("OPEC Fund for International Development"));
        test.log(Status.PASS, "Title matched with success");
    }
}
