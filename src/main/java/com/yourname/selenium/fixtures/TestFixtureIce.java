package com.yourname.selenium.fixtures;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.cdimascio.dotenv.Dotenv;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestFixtureIce {

    WebDriver driver;
    String username;
    String password;
    String baseUrl;

    // Method for initializing the WebDriver and other setup tasks
    public WebDriver setUp() {
        // String url = "https://icehrmpro.gamonoid.com/login.php?";

        // WebDriverManager.edgedriver().setup(); // Manage WebDriver binary for Edge
        WebDriverManager.chromedriver().setup();
        // driver = new EdgeDriver();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();

        Dotenv dotenv = Dotenv.configure().load(); 
        baseUrl = dotenv.get("BASE_URL_ICE");
        username = dotenv.get("USER_NAME_ICE");
        password = dotenv.get("PASSWORD_ICE");
        driver.get(baseUrl);

        return driver;
    }

    // Cleanup method to quit the driver after tests
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // Getter methods for easy access in tests
    public WebDriver getDriver() {
        return driver;
    }
    
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}
