package com.upwork.selenium;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.upwork.selenium.config.SeleniumFrameworkProperties;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@SeleniumTest
@Listeners(TestListener.class)
public class AllureElementTests extends AbstractTestNGSpringContextTests {
    @Autowired
    private WebDriver driver;
    @Autowired
    private SeleniumFrameworkProperties seleniumFrameworkProperties;


    @Test(testName = "Allure Title Test")
    @Epic("Allure OPEC Fund")
    @Feature("Allure Initial feature")
    @Story("Allure Check test")
    public void sanityCheck() {
        driver.navigate().to(seleniumFrameworkProperties.getDemoUrl());
        assertThat(driver.getTitle(), is("OPEC Fund for International Development"));
    }


}
