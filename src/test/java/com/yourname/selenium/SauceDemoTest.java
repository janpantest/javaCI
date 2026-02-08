package com.yourname.selenium;

import com.yourname.selenium.fixtures.TestFixture;
import com.yourname.selenium.pages.SauceDemoCartPage;
import com.yourname.selenium.pages.SauceDemoHomePage;
import com.yourname.selenium.pages.SauceDemoProductsPage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SauceDemoTest {

    TestFixture fixture;
    WebDriver driver;
    String username;
    String password;
    String baseUrl;
    String cartTitle;

    @Before
    public void setUp() {
        fixture = new TestFixture();
        driver = fixture.setUp(); // Initialize the WebDriver and perform setup
        username = fixture.getUsername();
        password = fixture.getPassword();
        baseUrl = fixture.getBaseUrl();
        cartTitle = fixture.getCartTitle();
        System.out.println(username);
        
    }

    @Test
    public void testLoginWorkflow() {
        SauceDemoHomePage homePage = new SauceDemoHomePage(driver);
        SauceDemoProductsPage productsPage = new SauceDemoProductsPage(driver);
        SauceDemoCartPage cartPage = new SauceDemoCartPage(driver);

        // Check if login form is visible
        assertTrue("Login form should be visible", homePage.isLoginFormVisible());
        homePage.login(username, password);

        assertTrue("Title of product should be visible", productsPage.isTitleVisible());
        productsPage.addFirstProductToBasket();
        productsPage.isRemoveButtonVisible();
        assertTrue("Remove button should be visible", productsPage.isCartBadgeVisible());
        productsPage.getAllProductsName();
        String product = productsPage.getProductText();
        productsPage.goToCart();

        cartPage.checkCartTitle(cartTitle);
        cartPage.checkProductName(product);
        assertTrue("Cart buttons are visible", cartPage.areButtonsVisible());
        cartPage.removeFromCart();
        assertFalse("Badge is not visible", cartPage.cartBageNotVisible());
        cartPage.continueInShopping();

        assertTrue("Title of product should be visible", productsPage.isTitleVisible());
        productsPage.logout();

        assertTrue("Login form should be visible", homePage.isLoginFormVisible());
    }

    @After
    public void tearDown() {
        fixture.tearDown();
    }
}
