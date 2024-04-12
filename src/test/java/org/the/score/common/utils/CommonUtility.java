package org.the.score.common.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

public abstract class CommonUtility {


    public static void sleep(int durationInSec){
        try {
            Thread.sleep(durationInSec*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public static void waitUntilElementExists(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, (Long.parseLong(FilesUtility.getAutomationProperties("browser.implicit.wait"))));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

}
