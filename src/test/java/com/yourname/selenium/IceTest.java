package com.yourname.selenium;

import com.yourname.selenium.pages.IceHome;
import com.yourname.selenium.pages.IceLogin;

import io.github.bonigarcia.wdm.WebDriverManager;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
// import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

// import static org.junit.Assert.assertTrue;

public class IceTest {

    WebDriver driver;
    String username;
    String password;
    String baseUrl;

    @Before
    public void setUp() {
        String url = "https://icehrmpro.gamonoid.com/login.php?";

        WebDriverManager.chromedriver().setup();

        // Create a new instance of Chrome driver
        driver = new FirefoxDriver();
        // driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get(url);

        username = "admin";
        password = "admin";
    }

    @Test
    public void testLoginWorkflow() {
        IceLogin iceLogin = new IceLogin(driver);
        IceHome iceHome = new IceHome(driver);
        
        iceLogin.checkLoginPage();
        iceLogin.login(username, password);
        iceHome.modalHandling();
        iceHome.openUserDropdown();
        assertTrue(iceHome.isProfileButtonVisible(), "Profile button should be visible");
        assertTrue(iceHome.isSignOutButtonVisible(), "Sign out button should be visible");
        iceHome.singOut();
        iceLogin.checkLoginPage();

    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
