package com.hfchallenge.pages;

import com.github.javafaker.Faker;
import com.hfchallenge.support.PageObjectFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;

public class AccountCreationPage extends PageObjectFactory {

    @FindBy(css="input[name='id_gender']")
    List<WebElement> titleGender;

    @FindBy(css="#customer_firstname")
    WebElement customerFirstname;

    @FindBy(css="#customer_lastname")
    WebElement customerLastname;

    @FindBy(css="#passwd")
    WebElement password;

    @FindBy(css="#company")
    WebElement company;

    @FindBy(css="#address1")
    WebElement address;

    @FindBy(css="#city")
    WebElement city;

    @FindBy(css="#postcode")
    WebElement postcode;

    @FindBy(css="#other")
    WebElement addicionalInformation;

    @FindBy(css="#phone")
    WebElement phone;

    @FindBy(css="#phone_mobile")
    WebElement phoneMobile;

    @FindBy(css="#submitAccount")
    WebElement submitAccount;

    public AccountCreationPage(WebDriver driver) {
        super(driver);
    }

    public String fillRandomPersonalInformation(){
        Faker faker = new Faker();
        Random rand = new Random();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String accountName = firstName+" "+lastName;
        titleGender.get(rand.nextInt(titleGender.size())).click();
        customerFirstname.sendKeys(firstName);
        customerLastname.sendKeys(lastName);
        password.sendKeys(faker.random().hex(5));
        Select selectDay = new Select(driver.findElement(By.id("days")));
        Select selectMonth = new Select(driver.findElement(By.id("months")));
        Select selectYear = new Select(driver.findElement(By.id("years")));
        selectDay.selectByIndex(rand.nextInt(10) + 2);
        selectMonth.selectByIndex(rand.nextInt(10)  + 2);
        selectYear.selectByIndex(rand.nextInt(10) + 2);
        company.sendKeys(faker.company().name());
        address.sendKeys(faker.address().streetAddress());
        city.sendKeys(faker.address().city());
        Select selectState = new Select(driver.findElement(By.id("id_state")));
        selectState.selectByIndex(rand.nextInt(10) + 2);
        postcode.sendKeys(faker.number().digits(5));
        addicionalInformation.sendKeys(faker.lorem().sentence());
        phone.sendKeys(faker.phoneNumber().cellPhone());
        phoneMobile.sendKeys(faker.phoneNumber().cellPhone());
        submitAccount.click();
        return accountName;
    }
}
