package ru.skypro.lessons.springboot.weblibrary1.pojo;

public class Employee {

    private final String name;
    private final int salary;
    private final int id;
    private static int idGenerator = 1;

    public Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;
        this.id = idGenerator++;
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }
    public int getId(){
        return id;
    }
}
