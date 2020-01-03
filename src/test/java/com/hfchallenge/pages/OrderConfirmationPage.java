package com.hfchallenge.pages;

import com.hfchallenge.support.PageObjectFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrderConfirmationPage extends PageObjectFactory {

   @FindBy(css=".step_current.last")
    WebElement lastStep;

    @FindBy(css=".cheque-indent strong")
    WebElement orderMessage;

    public OrderConfirmationPage(WebDriver driver) { super(driver); }

    public boolean lastStepIsDisplayed() { return lastStep.isDisplayed(); }

    public String orderMessageGetText() { return orderMessage.getText(); }
}
