package com.hfchallenge;

import com.aventstack.extentreports.Status;
import com.hfchallenge.support.BaseTest;
import org.junit.Test;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.assertEquals;

public class WebTest extends BaseTest {

    @Test
    public void signInTest() {
        test = extent.createTest("Sign In Test",
                "Sign in with random data and validates if my account page is opened and " +
                        "the user name is correct");
        given_i_access_the_sign_in_page();
        when_i_insert_a_new_user_using_random_data();
        then_the_My_Account_Page_must_be_opened_for_the_user();
    }

    @Test
    public void loginTest() {
        test = extent.createTest("Login Test",
                "Login with the default user and validates if my account page is opened and " +
                        "the user name is correct");
        given_i_login_with_default_user();
        then_the_My_Account_Page_must_be_opened_for_the_user();
    }

    @Test
    public void checkoutTest() {
        test = extent.createTest("Checkout Test",
                "Login with the default user, proceed checkout for the product " +
                        "'Faded Short Sleeve T-shirts' and validates if the order is confirmed");
        given_i_login_with_default_user();
        when_i_select_one_product_and_proceed_the_checkout();
        then_the_confirmation_of_the_order_must_be_showed();
    }


   public void given_i_access_the_sign_in_page() {
        menuSection.clickInSignInBtn();
        assertTrue(driver.getCurrentUrl().contains("?controller=authentication&back=my-account"));
        test.log(Status.PASS, "Given I Access the Sign In Page");
   }

    public void given_i_login_with_default_user() {
        autenticationPage.open();
        autenticationPage.login(System.getProperty("defaultUser.id"), System.getProperty("defaultUser.pwd"));
        assertEquals(menuSection.accountLinkGetText(), System.getProperty("defaultUser.fullName"));
        test.log(Status.PASS, "Given i login with default user");
    }

    public void when_i_insert_a_new_user_using_random_data() {
        autenticationPage.createAccount();
        String accountName = accountCreationPage.fillRandomPersonalInformation();
        assertEquals(menuSection.accountLinkGetText(), accountName);
        test.log(Status.PASS, "When i insert a new user using random data");
    }

    public void when_i_select_one_product_and_proceed_the_checkout() {
        menuSection.clickInWomenLink();
        womenPage.selectFadedShortSleeveProduct();
        productPage.checkoutWithRandomData();
        checkoutFooterSection.clickInProceedCheckoutButton(); //Checkout in summary page
        checkoutFooterSection.clickInProceedCheckoutButton(); //Checkout in address page
        shippingPage.clickInconfirmTermsCheckbox();
        checkoutFooterSection.clickInProceedCheckoutButton(); //Checkout in shipping page
        paymentPage.clickInbankWireOption();
        paymentPage.clickInsubmitButton();
        test.log(Status.PASS, "When i select one product and proceed the checkout");
    }

    public void then_the_My_Account_Page_must_be_opened_for_the_user() {
        assertTrue(driver.getCurrentUrl().contains("?controller=my-account"));
        assertTrue(menuSection.logoutBtnIsDisplayed());
        test.log(Status.PASS, "Then the My Account Page must be opened for the user");
    }

    public void then_the_confirmation_of_the_order_must_be_showed() {
        assertTrue(driver.getCurrentUrl().contains("?controller=order-confirmation"));
        assertEquals(orderConfirmationPage.orderMessageGetText(), "Your order on My Store is complete.");
        assertTrue(orderConfirmationPage.lastStepIsDisplayed());
        test.log(Status.PASS, "Then the confirmation of the order must be showed");
    }
}