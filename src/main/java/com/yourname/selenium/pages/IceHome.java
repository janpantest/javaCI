package com.yourname.selenium.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
 import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.yourname.selenium.fixtures.ElementActions;


public class IceHome {
    WebDriver driver;
    ElementActions actions;

    // Constructor
    public IceHome(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.actions = new ElementActions(driver);
    }

    // Elements    
    @FindBy(css = "button[class*='cancelBtn']")
    WebElement modalCloseButton;

    @FindBy(css = "li[class*='dropdown user'] a.dropdown-toggle")
    WebElement userDropdown;

    @FindBy(css = "div#verifyModel")
    WebElement model;

    @FindBy(css = "ul.dropdown-menu div.pull-left")
    WebElement profileButton;

    @FindBy(css = "ul.dropdown-menu div.pull-right")
    WebElement signOutButton;

    public void modalHandling() {
        actions.clickIfElementExists(modalCloseButton);
        actions.waitUntilInvisible(model);
    }

    public void openUserDropdown() {
        userDropdown.click();
    }

    public boolean isProfileButtonVisible() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
            .until(ExpectedConditions.visibilityOf(profileButton));

        System.out.println("Profile button is visible: " + profileButton.getText());
        return profileButton.isDisplayed();
    }

    public boolean isSignOutButtonVisible() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
            .until(ExpectedConditions.visibilityOf(signOutButton));

        System.out.println("Sign out button is visible: " + signOutButton.getText());
        return profileButton.isDisplayed();
    }    

    public void singOut() {
        signOutButton.click();
    }
}
