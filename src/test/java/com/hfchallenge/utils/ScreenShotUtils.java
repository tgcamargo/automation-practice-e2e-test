package com.hfchallenge.utils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShotUtils {

    //    Method to capture a screenshot using WebDriver and save it on the target
    public static String capture(WebDriver driver,String screenShotName) throws IOException
    {
        UUID uuid = UUID.randomUUID();
        TakesScreenshot ts = (TakesScreenshot)driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String dest = "screenshots/" + screenShotName + uuid + ".png";
        String completeDest = System.getProperty("user.dir") +"/target/"+dest;
        File destination = new File(completeDest);
        FileUtils.copyFile(source, destination);
        return dest;
    }
}