package com.yourname.selenium.fixtures;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.cdimascio.dotenv.Dotenv;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestFixture {

    WebDriver driver;
    String username;
    String password;
    String baseUrl;
    String cartTitle;

    public WebDriver setUp() {
        // Load .env locally if available; CI uses environment variables.
        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing()
                .load();

        baseUrl = getConfig("BASE_URL", dotenv);
        username = getConfig("USER_NAME", dotenv);
        password = getConfig("PASSWORD", dotenv);
        cartTitle = "Your Cart";

        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get(baseUrl);

        return driver;
    }

    private String getConfig(String key, Dotenv dotenv) {
        // Environment variables take priority in GitHub Actions.
        String value = System.getenv(key);

        // Fall back to .env for local development.
        if (value == null || value.isBlank()) {
            value = dotenv.get(key);
        }

        if (value == null || value.isBlank()) {
            throw new IllegalStateException(
                    "Missing configuration: " + key
                    + ". Set it in GitHub Actions Secrets or your local .env file."
            );
        }

        return value;
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

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

    public String getCartTitle() {
        return cartTitle;
    }
}