import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;


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
        int persent = 0;

        while (persent <= 50) {
            String percentage = driver.findElement(By.cssSelector(".percenttext")).getText();
            String string = percentage.replaceAll("\\D+", "");
            int persent1 = Integer.parseInt(string);
            persent = persent1;
        }
        driver.navigate().refresh();

        String percentage1 = driver.findElement(By.cssSelector(".percenttext")).getText();
        String nul = "0%";
        Assert.assertEquals(nul, percentage1);
    }

    @Test
    public void dropdownTest() {                                                            // to what I should convert datas of the table to
        driver.get("https://www.seleniumeasy.com/test/table-sort-search-demo.html");        // display the list according to the given parameters?
        Select dropdown = new Select(driver.findElement(By.cssSelector("label > select"))); // And it should contains only: Name, Position, Office?
        dropdown.selectByVisibleText("100");

        for (int ColNumber = 1; ColNumber <= 32; ColNumber++) {
            System.out.println(driver.findElement(By.xpath("//*[@id=\"example\"]/tbody/tr[" + ColNumber + "]")).getText());
        }
    }

    @AfterEach
    public void closeBrowser() {

        driver.quit();
    }
}
