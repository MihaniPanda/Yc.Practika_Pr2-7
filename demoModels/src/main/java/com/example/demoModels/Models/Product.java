package com.example.demoModels.Models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.Date;


@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Pattern(regexp = "^[а-яА-Яa-zA-Z]+$", message = "Разрешены только буквы")
    @NotBlank(message = "Название не должно быть пустым")
    private String nameproduct;
    @Positive(message = "Цена должна быть больше 0")
    @NotNull(message = "Заполните цену")
    private double price;
    @Min(value = 1,message = "Количество должно быть больше 0")
    @NotNull(message = "Заполните цену")
    private int amount;
    @Pattern(regexp = "^[а-яА-Яa-zA-Z]+$", message = "Разрешены только буквы")
    private String country;
    @NotBlank(message = "Описание не должно быть пустым")
    private String description;

    @DateTimeFormat(iso =  DateTimeFormat.ISO.DATE)
    @Past
    @NotNull
    private Date creationdate;

    @OneToMany(mappedBy = "employees", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<Employee> employees;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameproduct() {
        return nameproduct;
    }

    public void setNameproduct(String nameproduct) {
        this.nameproduct = nameproduct;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationdate() {
        return creationdate;
    }

    public void setCreationdate(Date creationdate) {
        this.creationdate = creationdate;
    }

    public Collection<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Collection<Employee> employees) {
        this.employees = employees;
    }
}
