import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class AlertAndMultiselect {
    WebDriver driver;

    @BeforeEach
    public void openBrowser() {
        driver = new ChromeDriver();
    }

    @Test
    public void selectMultipelValues() {
        driver.get("https://www.seleniumeasy.com/test/basic-select-dropdown-demo.html");

        Select objectsList = new Select(driver.findElement(By.id("multi-select")));
        objectsList.selectByVisibleText("Florida");
        objectsList.selectByIndex(3);
        objectsList.selectByValue("Washington");
        WebElement loginButton = driver.findElement(By.cssSelector("#printAll"));
        loginButton.click();

        List<WebElement> selectedOptions = objectsList.getAllSelectedOptions();
        List<String> selectedObjects = selectedOptions
                .stream()
                .map(x -> x.getText())
                .collect(Collectors.toList());

        List<String> expectedObjects = Arrays.asList("Florida", "New York", "Washington");
        Assert.assertEquals(expectedObjects, selectedObjects);
    }

    @Test
    public void confirmBoxAccept() {
        driver.get("https://www.seleniumeasy.com/test/javascript-alert-box-demo.html");
        WebElement confirmButton = driver.findElement(By.cssSelector("[onclick=\"myConfirmFunction()\"]"));

        confirmButton.click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
        String actualName = driver.findElement(By.cssSelector("#confirm-demo")).getText();
        String expectedName = "You pressed OK!";
        Assert.assertEquals(expectedName, actualName);
    }

    @Test
    public void confirmBoxDismiss() {
        driver.get("https://www.seleniumeasy.com/test/javascript-alert-box-demo.html");
        WebElement confirmButton = driver.findElement(By.cssSelector("[onclick=\"myConfirmFunction()\"]"));
        confirmButton.click();

        Alert alert = driver.switchTo().alert();
        alert.dismiss();
        String actualName = driver.findElement(By.cssSelector("#confirm-demo")).getText();
        String expectedName = "You pressed Cancel!";
        Assert.assertEquals(expectedName, actualName);

    }

    @Test
    public void promptBox() {
        driver.get("https://www.seleniumeasy.com/test/javascript-alert-box-demo.html");
        WebElement promptButton = driver.findElement(By.cssSelector("[onclick=\"myPromptFunction()\"]"));
        promptButton.click();

        Alert alert = driver.switchTo().alert();
        String message = alert.getText();
        alert.sendKeys("start");
        alert.accept();
        String actualName = driver.findElement(By.cssSelector("#prompt-demo")).getText();
        String expectedName = "You have entered 'start' !";
        Assert.assertEquals(expectedName, actualName);

    }

    @Test
    public void waitsForUser() {
        driver.get("https://www.seleniumeasy.com/test/dynamic-data-loading-demo.html");
        WebElement waitButton = driver.findElement(By.cssSelector("#save"));
        waitButton.click();

        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement expectedUserImg = wait.until(visibilityOfElementLocated(By.cssSelector("#loading > img")));
        WebElement actualUser = driver.findElement(By.cssSelector("#loading > img"));
        Assert.assertTrue(actualUser.isDisplayed());

    }

    @AfterEach
    public void closeBrowser() {

        driver.quit();
    }
}
