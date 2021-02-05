import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

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

        Select selectObject = new Select(driver.findElement(By.id("multi-select")));
        selectObject.selectByVisibleText("Florida");
        selectObject.selectByIndex(3);
        selectObject.selectByValue("Washington");
        WebElement loginButton = driver.findElement(By.cssSelector("#printAll"));
        loginButton.click();

        List<WebElement> selectedOptions = selectObject.getAllSelectedOptions();
        List objects = new ArrayList();
        for (WebElement selectedOption : selectedOptions)
            objects.add(selectedOption.getText());

        List<String> list = new ArrayList<>();
        list.add("Florida");
        list.add("New York");
        list.add("Washington");

        Assert.assertEquals(list, objects);
    }

    @Test
    public void alert1Accept() {
        driver.get("https://www.seleniumeasy.com/test/javascript-alert-box-demo.html");
        WebElement alert1Button = driver.findElement(By.cssSelector("[onclick=\"myConfirmFunction()\"]"));

        alert1Button.click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
        String result = driver.findElement(By.cssSelector("#confirm-demo")).getText();
        String expectedname = "You pressed OK!";
        Assert.assertEquals(expectedname, result);
    }

    @Test
    public void alert1Dismiss() {
        driver.get("https://www.seleniumeasy.com/test/javascript-alert-box-demo.html");
        WebElement alert1Button = driver.findElement(By.cssSelector("[onclick=\"myConfirmFunction()\"]"));
        alert1Button.click();
        Alert alert1 = driver.switchTo().alert();
        alert1.dismiss();
        String result1 = driver.findElement(By.cssSelector("#confirm-demo")).getText();
        String expectedname1 = "You pressed Cancel!";
        Assert.assertEquals(expectedname1, result1);

    }

    @Test
    public void alert2() {
        driver.get("https://www.seleniumeasy.com/test/javascript-alert-box-demo.html");
        WebElement alert2Button = driver.findElement(By.cssSelector("[onclick=\"myPromptFunction()\"]"));
        alert2Button.click();

        Alert alert = driver.switchTo().alert();
        String message = alert.getText();
        alert.sendKeys("start");
        alert.accept();
        String result1 = driver.findElement(By.cssSelector("#prompt-demo")).getText();
        String expectedname1 = "You have entered 'start' !";
        Assert.assertEquals(expectedname1, result1);

    }

    @Test
    public void waitsForUser() {
        driver.get("https://www.seleniumeasy.com/test/dynamic-data-loading-demo.html");
        WebElement waitButton = driver.findElement(By.cssSelector("#save"));
        waitButton.click();

        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement gettingOutput = wait.until(visibilityOfElementLocated(By.cssSelector("#loading > img")));
        WebElement gettingOutput1 = driver.findElement(By.cssSelector("#loading > img"));
        Assert.assertTrue(gettingOutput1.isDisplayed());

    }

    @AfterEach
    public void closeBrowser() {

        driver.quit();
    }
}
