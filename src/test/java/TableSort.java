
public class TableSort {

    private String name;
    private String position;
    private String office;
    private int age;
    private String startDate;
    private int salary;

    public TableSort(String name, String position, String office, int age, String startDate, int salary) {
        this.name = name;
        this.position = position;
        this.office = office;
        this.age = age;
        this.startDate = startDate;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public String getOffice() {
        return office;
    }

    public int getAge() {
        return age;
    }

    public String getStartDate() {
        return startDate;
    }

    public int getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return name + "  " + position + "  " + office;
    }

}

