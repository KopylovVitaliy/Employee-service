package ru.skypro.lessons.springboot.weblibrary1.pojo;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private  String name;
    private  int salary;
    @ManyToOne
    private Position position;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee1 = (Employee) o;
        return salary == employee1.salary && Objects.equals(id, employee1.id) && Objects.equals(name, employee1.name) && Objects.equals(position, employee1.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, salary, position);
    }
}
