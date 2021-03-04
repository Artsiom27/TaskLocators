import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainTableSort {
    private static ChromeDriver driver;

    public static void main(String[] args) {
        driver = new ChromeDriver();
        driver.get("https://www.seleniumeasy.com/test/table-sort-search-demo.html");

        List<Employee> employeesFromAutoDepartment = getEmployees(20, 40000);
        List<Employee> employeesFromDevopsDepartment = getEmployees(40, 100000);

        driver.quit();
    }

    public static List<Employee> getEmployees(int ageMin, int salaryMax) {

        ArrayList<Employee> employees = new ArrayList<Employee>();
        Select dropdown = new Select(driver.findElement(By.cssSelector("label > select")));
        dropdown.selectByVisibleText("10");

        WebElement pagination = driver.findElement(By.cssSelector("#example_paginate"));
        List<WebElement> allPagination = pagination.findElements(By.cssSelector("#example_paginate > span >a"));
        WebElement nextPage = driver.findElement(By.cssSelector("#example_next"));

        if (nextPage.isEnabled()) {
            for (int i = 0; i < allPagination.size(); i++) {
                pagination = driver.findElement(By.cssSelector("#example_paginate"));
                allPagination = pagination.findElements(By.cssSelector("#example_paginate > span >a"));
                allPagination.get(i).click();

                List<WebElement> elements = driver.findElements(By.cssSelector("tbody > tr"));
                for (WebElement element : elements) {
                    WebElement nameElement = element.findElement(By.cssSelector("tr > td.sorting_1"));
                    WebElement positionElement = element.findElement(By.cssSelector("tr > td:nth-child(2)"));
                    WebElement officeElement = element.findElement(By.cssSelector("tr > td:nth-child(3)"));
                    WebElement ageElement = element.findElement(By.cssSelector("tr > td:nth-child(4)"));
                    WebElement salaryElement = element.findElement(By.cssSelector("tr > td:nth-child(6)"));

                    String name = nameElement.getText();
                    String position = positionElement.getText();
                    String office = officeElement.getText();
                    String ageText = ageElement.getText();
                    int age = Integer.parseInt(ageText);
                    String salaryText = salaryElement.getText();

                    String[] split = salaryText.split("[\\$ / y,]");
                    String delimiter = "";
                    String salaryString = String.join(delimiter, split);
                    int salary = Integer.parseInt(salaryString);

                    Employee sort = new Employee(name, position, office, age, salary);
                    employees.add(sort);
                }
            }
        }

        List<Employee> employeesFilted = employees.stream()
                .filter(p -> p.getAge() > ageMin)
                .filter(p -> p.getSalary() <= salaryMax)
                .collect(Collectors.toList());

        return employeesFilted;

    }
}


