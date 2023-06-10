package ru.skypro.lessons.springboot.weblibrary1.pojo;

import jakarta.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private  String name;
    private  int salary;

    public Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;

    }
    public Employee() {

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
    public void setId(Integer id) {
        this.id = id;
    }
}
