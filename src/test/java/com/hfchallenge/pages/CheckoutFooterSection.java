package com.hfchallenge.pages;

import com.hfchallenge.support.PageObjectFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutFooterSection extends PageObjectFactory {

    @FindBy(css=".cart_navigation .button")
    WebElement proceedCheckoutButton;

    public CheckoutFooterSection(WebDriver driver) { super(driver); }

    public void clickInProceedCheckoutButton() { proceedCheckoutButton.click(); }
}
