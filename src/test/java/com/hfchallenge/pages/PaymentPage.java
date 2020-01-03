package com.hfchallenge.pages;

import com.hfchallenge.support.PageObjectFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PaymentPage extends PageObjectFactory {

    @FindBy(css=".bankwire")
    WebElement bankWireOption;

    @FindBy(css="#cart_navigation button[type=submit]")
    WebElement submitButton;

    public PaymentPage(WebDriver driver) { super(driver); }

    public void clickInbankWireOption() { bankWireOption.click(); }

    public void clickInsubmitButton() { submitButton.click(); }
}
