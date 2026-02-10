package com.yourname.selenium.fixtures;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ElementActions {

    // private final WebDriver driver;
    private final WebDriverWait wait;

    public ElementActions(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    // For By locators
    public void clickIfElementExists(By locator) {
        try {
            WebElement element = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(locator)
            );
            element.click();
        } catch (TimeoutException e) {
            // ignore
        }
    }

    // 👇 ADD THIS for @FindBy WebElements
    public void clickIfElementExists(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            element.click();
        } catch (TimeoutException | NoSuchElementException | StaleElementReferenceException e) {
            // ignore
        }
    }

    public void waitUntilInvisible(WebElement element) {
        try {
            wait.until(ExpectedConditions.invisibilityOf(element));
        } catch (TimeoutException | NoSuchElementException | StaleElementReferenceException e) {
            // ignore
        }
    }
}
