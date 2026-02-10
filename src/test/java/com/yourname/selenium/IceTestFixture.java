package com.yourname.selenium;

import com.yourname.selenium.fixtures.TestFixtureIce;
import com.yourname.selenium.pages.IceHome;
import com.yourname.selenium.pages.IceLogin;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class IceTestFixture {

    TestFixtureIce fixtureIce;
    WebDriver driver;
    String username;
    String password;
    String baseUrl;

    @Before
    public void setUp() {
        fixtureIce = new TestFixtureIce();
        driver = fixtureIce.setUp(); // Initialize the WebDriver and perform setup
        username = fixtureIce.getUsername();
        password = fixtureIce.getPassword();
        baseUrl = fixtureIce.getBaseUrl();
        System.out.println(username);
        
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
        fixtureIce.tearDown();
    }
}
