import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;


public class Point8and9 {
    WebDriver driver;

    @BeforeEach
    public void openBrowser() {
        driver = new ChromeDriver();
    }

    @Test
    public void downloadTest() {
        driver.get("https://www.seleniumeasy.com/test/bootstrap-download-progress-demo.html");
        WebElement downloadButton = driver.findElement(By.cssSelector("#cricle-btn"));
        downloadButton.click();
        int actualPersent = 0;

        while (actualPersent <= 50) {
            String downloadText = driver.findElement(By.cssSelector(".percenttext")).getText();
            String persentText = downloadText.replaceAll("\\D+", "");
            int downloadPersent = Integer.parseInt(persentText);
            actualPersent = downloadPersent;
        }
        driver.navigate().refresh();
    }

    @Test
    public void dropdownTest() {
        driver.get("https://www.seleniumeasy.com/test/table-sort-search-demo.html");
        Select dropdown = new Select(driver.findElement(By.cssSelector("label > select")));
        dropdown.selectByVisibleText("10");
        WebElement nameOf10 = driver.findElement(By.cssSelector("tr:nth-child(10) > td.sorting_1"));
        WebElement number = driver.findElement(By.cssSelector("#example_length > label > select > option:nth-child(1)"));

        Assert.assertTrue(nameOf10.isDisplayed());
        Assert.assertTrue(number.isDisplayed());

    }

    @AfterEach
    public void closeBrowser() {

        driver.quit();
    }
}
