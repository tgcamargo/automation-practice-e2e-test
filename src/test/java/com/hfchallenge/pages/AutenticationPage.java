package com.hfchallenge.pages;

import com.hfchallenge.support.PageObjectFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Date;

public class AutenticationPage extends PageObjectFactory {

    @FindBy(css="#email_create")
    WebElement emailCreate;

    @FindBy(css="#SubmitCreate")
    WebElement submitCreate;

    @FindBy(css="#email")
    WebElement email;

    @FindBy(css="#passwd")
    WebElement passwd;

    @FindBy(css="#SubmitLogin")
    WebElement submitLogin;

    public AutenticationPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(System.getProperty("url")+"?controller=authentication&back=my-account");
    }

    public void createAccount(){
        String timestamp = String.valueOf(new Date().getTime());
        String email = "hf_challenge_" + timestamp + "@hf" + timestamp.substring(7) + ".com";
        emailCreate.sendKeys(email);
        submitCreate.click();
    }

    public void login(String email, String passwd){
        this.email.sendKeys(email);
        this.passwd.sendKeys(passwd);
        submitLogin.click();
    }
}
