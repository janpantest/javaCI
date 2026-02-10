package com.yourname.selenium.fixtures;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.cdimascio.dotenv.Dotenv;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class TestFixtureIce {

    private WebDriver driver;
    private String username;
    private String password;
    private String baseUrl;

    public WebDriver setUp() {
        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing()
                .load();

        baseUrl = dotenv.get("BASE_URL_ICE", System.getenv("BASE_URL_ICE"));
        username = dotenv.get("USER_NAME_ICE", System.getenv("USER_NAME_ICE"));
        password = dotenv.get("PASSWORD_ICE", System.getenv("PASSWORD_ICE"));

        // Detect CI environment
        boolean isCI = "true".equalsIgnoreCase(System.getenv("CI"));

        FirefoxOptions options = new FirefoxOptions();

        if (isCI) {
            options.addArguments("--headless");
            options.addArguments("--width=1920");
            options.addArguments("--height=1080");
        }

        // Setup and create driver
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver(options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        driver.manage().window().maximize();

        // Navigate to app
        driver.get(baseUrl);

        return driver;
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
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
