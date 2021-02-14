import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.*;
import java.util.stream.Collectors;

public class MainTableSort {                                              //the method works, but each line is a separate ArrayList
    public static void main(String[] args) {                             //and lines that don't pass through the filter are displayed empty
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.seleniumeasy.com/test/table-sort-search-demo.html");
        Select dropdown = new Select(driver.findElement(By.cssSelector("label > select")));
        dropdown.selectByVisibleText("100");

        for (int i = 1; i <= 32; i++) {
            List<WebElement> elements = driver.findElements(By.cssSelector("tbody > tr:nth-child(" + i + ")"));
            for (WebElement element : elements) {
                WebElement name = element.findElement(By.cssSelector("tr:nth-child(" + i + ") > td.sorting_1"));
                WebElement position = element.findElement(By.cssSelector("tr:nth-child(" + i + ") > td:nth-child(2)"));
                WebElement office = element.findElement(By.cssSelector("tr:nth-child(" + i + ") > td:nth-child(3)"));
                WebElement age = element.findElement(By.cssSelector("tr:nth-child(" + i + ") > td:nth-child(4)"));
                WebElement startDate = element.findElement(By.cssSelector("tr:nth-child(" + i + ") > td:nth-child(5)"));
                WebElement salary = element.findElement(By.cssSelector("tr:nth-child(" + i + ") > td:nth-child(6)"));

                String nameString = name.getText();
                String positionString = position.getText();
                String officeString = office.getText();
                String ageString = age.getText();
                int ageInt = Integer.parseInt(ageString);
                String startDateString = startDate.getText();
                String salaryInput = salary.getText();

                String[] split = salaryInput.split("[\\$ / y,]");
                for (String salaryNumber : split) {
                }

                String delimiter = "";
                String salaryString = String.join(delimiter, split);
                int salaryInt = Integer.parseInt(salaryString);

                TableSort sort = new TableSort(nameString, positionString, officeString, ageInt, startDateString, salaryInt);

                ArrayList<TableSort> persons = new ArrayList<TableSort>();
                persons.add(sort);

                    List<TableSort> personsFilted = persons.stream()
                            .filter(r -> r.getAge() > 40)
                            .filter(r -> r.getSalary() <= 300000)
                            .collect(Collectors.toList());
                    System.out.println(personsFilted);
                }
            }
        driver.quit();
        }
    }

