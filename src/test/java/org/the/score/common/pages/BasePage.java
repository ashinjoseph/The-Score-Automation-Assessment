package org.the.score.common.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class BasePage {
    protected AndroidDriver driver;
    protected WebDriverWait wait;

    public BasePage(AndroidDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    public void clickAndTransition(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
        waitForPageLoad();
    }


    private void waitForPageLoad() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*")));
        } catch (NoSuchElementException e) {
            // Todo:logger
            System.out.println("No element found on the new page.");
        }
    }
    public WebElement waitForElementToBeClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public boolean isElementWithTextDisplayed(WebElement element, String expectedText) {
        if (!element.isDisplayed()) {
            return false;
        }
        String actualText = element.getText();

        return actualText.contains(expectedText);
    }
    public boolean navigateBack(WebElement isElementDisplayed) {
        driver.navigate().back();
        return isElementDisplayed.isDisplayed();
    }
}
