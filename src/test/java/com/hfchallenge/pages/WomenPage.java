package com.hfchallenge.pages;

import com.hfchallenge.support.PageObjectFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Random;

public class WomenPage extends PageObjectFactory {

    @FindBy(css="a.product-name[title = 'Faded Short Sleeve T-shirts']")
    WebElement fadedShortSleeveProduct;

    public WomenPage(WebDriver driver) {
        super(driver);
    }

    public void selectFadedShortSleeveProduct(){ fadedShortSleeveProduct.click(); }
}