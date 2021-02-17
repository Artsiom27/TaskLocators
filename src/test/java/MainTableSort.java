import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.*;
import java.util.stream.Collectors;

public class MainTableSort {
    public static void main(String[] args) {
        TableSort employees = new TableSort();
        ArrayList<TableSort> persons = init(employees);
        filterResult(persons);
    }

    public static ArrayList<TableSort> init(TableSort sort) {
        ArrayList<TableSort> persons = new ArrayList<TableSort>();

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.seleniumeasy.com/test/table-sort-search-demo.html");
        Select dropdown = new Select(driver.findElement(By.cssSelector("label > select")));
        dropdown.selectByVisibleText("100");


        List<WebElement> elements = driver.findElements(By.cssSelector("tbody > tr"));
        for (WebElement element : elements) {
            WebElement nameElement = element.findElement(By.cssSelector("tr > td.sorting_1"));
            WebElement positionElement = element.findElement(By.cssSelector("tr > td:nth-child(2)"));
            WebElement officeElement = element.findElement(By.cssSelector("tr > td:nth-child(3)"));
            WebElement ageElement = element.findElement(By.cssSelector("tr > td:nth-child(4)"));
            WebElement startDateElement = element.findElement(By.cssSelector("tr > td:nth-child(5)"));
            WebElement salaryElement = element.findElement(By.cssSelector("tr > td:nth-child(6)"));

            String name = nameElement.getText();
            String position = positionElement.getText();
            String office = officeElement.getText();
            String ageText = ageElement.getText();
            int age = Integer.parseInt(ageText);
            String startDate = startDateElement.getText();
            String salaryText = salaryElement.getText();

            String[] split = salaryText.split("[\\$ / y,]");
            String delimiter = "";
            String salaryString = String.join(delimiter, split);
            int salary = Integer.parseInt(salaryString);

            sort = new TableSort(name, position, office, age, startDate, salary);
            persons.add(sort);
        }
        driver.quit();

        return persons;
    }

    public static List<TableSort> filterResult(ArrayList<TableSort> person) {

        List<TableSort> personsFilted = person.stream()
                .filter(p -> p.getAge() > 45).filter(p -> p.getSalary() <= 300000).collect(Collectors.toList());
        System.out.println(personsFilted);

        return personsFilted;
    }
}


