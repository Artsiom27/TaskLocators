import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;


public class WaiterTest {
    WebDriver driver;

    @BeforeEach
    public void openBrowser() {
        driver = new ChromeDriver();
    }

    @ParameterizedTest
    @MethodSource("person")
    void Names(String login, String password) throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://www.tut.by/");
        driver.findElement(By.cssSelector("#authorize > div > a")).click();


        WebElement usernameInput = driver.findElement(By.name("login"));
        WebElement passwordInput = driver.findElement(By.name("password"));
        WebElement loginButton = driver.findElement(By.xpath("//input[@tabindex=6]"));

        //Thread.sleep(2000);

        usernameInput.sendKeys(login);
        passwordInput.sendKeys(password);
        loginButton.click();

        WebElement element = driver.findElement(By.cssSelector("span.uname"));
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(5, TimeUnit.SECONDS)
                .pollingEvery(200, TimeUnit.MILLISECONDS);
        wait.until(ExpectedConditions.textToBePresentInElement(element, "Selenium Test"));


        String result = driver.findElement(By.cssSelector("span.uname")).getText();
        String expectedname = "Selenium Test";
        Assert.assertEquals(expectedname, result);
    }

    private static Stream<Arguments> person() {
        return Stream.of(
                arguments("seleniumtests@tut.by", "123456789zxcvbn"),
                arguments("seleniumtests2@tut.by", "123456789zxcvbn")
        );
    }

    @AfterEach
    public void closeBrowser() {

        driver.quit();
    }
}



