
public class Employee {

    private String name;
    private String position;
    private String office;
    private int age;
    private int salary;


    public Employee(String name, String position, String office, int age, int salary) {
        this.name = name;
        this.position = position;
        this.office = office;
        this.age = age;
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

    public int getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return name + "  " + position + "  " + office;
    }
}




