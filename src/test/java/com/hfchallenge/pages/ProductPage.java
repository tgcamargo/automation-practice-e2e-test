package com.hfchallenge.pages;

import com.hfchallenge.support.PageObjectFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;


public class ProductPage extends PageObjectFactory {

    @FindBy(css="#quantity_wanted")
    WebElement quantityTextBox;

    @FindBy(css=".color_pick")
    List<WebElement> colors;

    @FindBy(css="#add_to_cart button")
    WebElement addCartButton;

    @FindBy(css="#layer_cart a.btn")
    WebElement checkoutButton;

    public WebDriverWait wait;

    public ProductPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, 10, 50);
    }

    public void checkoutWithRandomData(){
        Random rand = new Random();
        int quantity= rand.nextInt(5)+1;
        quantityTextBox.sendKeys(Integer.toString(quantity));
        Select selectSize = new Select(driver.findElement(By.id("group_1")));
        selectSize.selectByIndex(rand.nextInt(3));
        int randomColor = rand.nextInt(colors.size());
        colors.get(randomColor).click();
        addCartButton.click();
        wait.until(ExpectedConditions.visibilityOf(checkoutButton)).click();
    }

}
