package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * 2. Create the package ‘testsuite’ and create the
 * following class inside the ‘testsuite’ package.
 * 1. LoginTest
 * 3. Write down the following test into ‘LoginTest’ class
 * 1. user Should Login SuccessfullyWithValidCredentials
 * * Enter “tomsmith” username
 * * Enter “SuperSecretPassword!” password
 * * Click on ‘LOGIN’ button
 * * Verify the text “Secure Area”
 * 2. verifyTheUsernameErrorMessage
 * * Enter “tomsmith1” username
 * * Enter “SuperSecretPassword!” password
 * * Click on ‘LOGIN’ button
 * * Verify the error message “Your username
 * is invalid!”
 * 3. verifyThePasswordErrorMessage
 * * Enter “tomsmith” username
 * * Enter “SuperSecretPassword” password
 * * Click on ‘LOGIN’ button
 * * Verify the error message “Your password
 * is invalid!”
 */

public class LoginTest extends BaseTest {
    String baseUrl = "http://the-internet.herokuapp.com/login";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void userSholdLoginSuccessfullyWithValidCredentials() {

        WebElement userName = driver.findElement(By.id("username"));
        userName.sendKeys("tomsmith");

        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("SuperSecretPassword!");

        WebElement loginButton = driver.findElement(By.xpath("//i[contains(text(),'Login')]"));
        loginButton.click();

        WebElement actual = driver.findElement(By.xpath("//body[1]/div[2]/div[1]/div[1]/h2[1]"));
        String expected = "Secure Area";

        Assert.assertEquals(expected, actual.getText());
    }

    @Test
    public void verifyTheUsernameErrorMessage() {

        WebElement userName = driver.findElement(By.id("username"));
        userName.sendKeys("abc");

        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("123");

        WebElement loginButton = driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']"));
        loginButton.click();

        WebElement actual = driver.findElement(By.xpath("//div[@id='flash']"));
        String expected = "Your username is invalid!\n"+"×";

        Assert.assertEquals(expected, actual.getText());

    }

    @Test
    public void verifyThePasswordErrorMessage() {
        WebElement userName = driver.findElement(By.id("username"));
        userName.sendKeys("tomsmith");

        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("SuperSecretPassword");

        WebElement loginButton = driver.findElement(By.xpath("//body/div[2]/div[1]/div[1]/form[1]/button[1]"));
        loginButton.click();

        WebElement actual = driver.findElement(By.xpath("//div[@id='flash']"));
        String expected = "Your password is invalid!\n"+"×";

        Assert.assertEquals(expected, actual.getText());
    }

    @After
    public void tearDown() {

        closeBrowser();
    }

}