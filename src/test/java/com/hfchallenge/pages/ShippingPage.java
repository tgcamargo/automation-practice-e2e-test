package com.hfchallenge.pages;

import com.hfchallenge.support.PageObjectFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShippingPage extends PageObjectFactory {

    @FindBy(css=".order_carrier_content .checkbox span")
    WebElement confirmTermsCheckbox;

    public ShippingPage(WebDriver driver) { super(driver); }

    public void clickInconfirmTermsCheckbox() {
        confirmTermsCheckbox.click();
    }
}
