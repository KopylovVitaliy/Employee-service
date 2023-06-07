package ru.skypro.lessons.springboot.weblibrary1.pojo;

import java.util.Objects;

public class Employee {


    private  String name;
    private  int salary;
    private final Integer id;
    private static Integer idGenerator = 1;

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

    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return salary == employee.salary && Objects.equals(name, employee.name) && Objects.equals(id, employee.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, salary, id);
    }
}
