package com.hfchallenge.support;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

public class WebDriverFactory
{
    public WebDriver createInstance(String browser, String os)
    {
        ChromeOptions options;
        String winexe = "";
        if (os.equals("windows")){ winexe = ".exe"; }

        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chrome_"+ os +"_driver"+winexe);
        System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/gecko_"+ os +"_driver"+winexe);
        System.setProperty("webdriver.ie.driver", "src/test/resources/drivers/IEDriverServer.exe");

        switch (browser){
            case "chrome":
                return new ChromeDriver();
            case "firefox" :
                return new FirefoxDriver();
            case "ie":
                return new InternetExplorerDriver();
            case "safari":
                return new SafariDriver();
            default:
                options = new ChromeOptions();
                options.addArguments("headless");
                options.addArguments("window-size=1200x600");
                return new ChromeDriver(options);
        }
    }
}