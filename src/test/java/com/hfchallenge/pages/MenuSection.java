package com.hfchallenge.pages;

import com.hfchallenge.support.PageObjectFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MenuSection extends PageObjectFactory {

    @FindBy(css=".login")
    WebElement signInBtn;

    @FindBy(css=".logout")
    WebElement logoutBtn;

    @FindBy(css=".account")
    WebElement accountLink;

    @FindBy(css="a[title=Women]")
    WebElement womenLink;

    public MenuSection(WebDriver driver) { super(driver); }

    public void clickInSignInBtn(){
        signInBtn.click();
    }

    public void clickInWomenLink() { womenLink.click(); }

    public  boolean logoutBtnIsDisplayed(){ return logoutBtn.isDisplayed(); }

    public String accountLinkGetText(){ return accountLink.getText(); }
}
