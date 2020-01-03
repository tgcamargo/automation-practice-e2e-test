package com.hfchallenge.support;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public class PageObjectFactory {
    protected WebDriver driver;
    protected Actions action;

    public PageObjectFactory(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        action = new Actions(driver);
    }
}
