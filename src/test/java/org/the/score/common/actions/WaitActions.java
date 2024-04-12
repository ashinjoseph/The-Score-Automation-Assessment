package org.the.score.common.actions;

import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

@Getter
public class WaitActions {
    private FluentWait<WebDriver> wait;

    public WaitActions(WebDriver driver, Integer waitSeconds, Integer pollingMs) {
        wait = new FluentWait<>(driver)
            .withTimeout(Duration.ofSeconds(waitSeconds))
            .pollingEvery(Duration.ofMillis(pollingMs))
            .ignoring(NoSuchElementException.class)
            .ignoring(StaleElementReferenceException.class);
    }

    public void waitForLocatorToBePresent(By locator){
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void waitForElementIsDisplayed(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementIsClickable(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForElementIsNotDisplayed(WebElement element){
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void waitForTextBeInElement(WebElement element, String value){
        wait.until(ExpectedConditions.textToBePresentInElement(element, value));
    }

    public void waitForTextNotBeInElement(WebElement element, String value){
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(element, value)));
    }

    public void waitForTitle(String value){
        wait.until(ExpectedConditions.titleContains(value));
    }

    public void waitForUrlContains(String value){
        wait.until(ExpectedConditions.urlContains(value));
    }

    public void waitForUrlMatches(String regex){
        wait.until(ExpectedConditions.urlContains(regex));
    }

    public void waitForPresenseOfElementLocated(By locator){
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void waitForNoPresenceOfElementLocated(By locator){
        wait.until(ExpectedConditions.not(ExpectedConditions.presenceOfAllElementsLocatedBy(locator)));
    }

    public void waitForTableToHaveMoreResults(By locator, Integer moreThan){
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(locator, moreThan));
    }

    public void waitForTableToHaveLessResults(By locator, Integer lessThan){
        wait.until(ExpectedConditions.numberOfElementsToBeLessThan(locator, lessThan));
    }

    public void waitToContainsAnyText(WebElement element){
        wait.until((ExpectedCondition<Boolean>) driver -> !element.getText().isEmpty());
    }
}
