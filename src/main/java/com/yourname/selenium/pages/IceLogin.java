package com.yourname.selenium.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
 import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
// import org.openqa.selenium.support.ui.ExpectedConditions;
// import java.time.Duration;
// import java.util.List;

public class IceLogin {
    WebDriver driver;

    // Constructor
    public IceLogin(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Elements    
    @FindBy(css = "input#username")
    WebElement nameInput;

    @FindBy(css = "input#password")
    WebElement passwordInput;

    @FindBy(css = "button[class*='btn-info']")
    WebElement buttonLogin;

    public void checkLoginPage() {
        new WebDriverWait(driver, Duration.ofSeconds(5))  // wait up to 5 seconds
            .until(ExpectedConditions.visibilityOfAllElements(
                nameInput,
                passwordInput,
                buttonLogin
            ));

        // nameInput.isDisplayed();
        // passwordInput.isDisplayed();
        // buttonLogin.isDisplayed();
    }

    public void login(String user, String password) {
        nameInput.sendKeys(user);
        passwordInput.sendKeys(password);
        buttonLogin.click();
    }

 }
