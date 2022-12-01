package com.example.demoModels.Models;

import javax.persistence.*;

import javax.validation.constraints.*;
import java.util.Collection;

@Entity
@Table(name="employee")
public class Employee  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min=2, max=20, message ="Фамилия не может быть меньше 2 букв и не может быть больше 35 букв")
    @Pattern(regexp = "^[а-яА-Я]+$", message = "Разрешены только буквы кириллицы")
    private String firstname;
    @Size(min=2, max=20, message ="Имя не может быть меньше 2 букв и не может быть больше 35 букв")
    @Pattern(regexp = "^[а-яА-Я]+$", message = "Разрешены только буквы кириллицы")
    private String lastname;
    @Pattern(regexp = "^[а-яА-Я]+$", message = "Разрешены только буквы кириллицы")
    private String middlename;
    @NotNull
    @Min(value = 18,message = "Возраст не может быть меньше 18")
    private Integer age;
    @NotNull
    @Min(value=15000,message = "Зарплата не может быть меньше 20000")
    private Double oklad;
    private boolean viska;
    public boolean isViska() {
        return viska ;
    }

    public void setViska(boolean viska) {
        this.viska = viska;
    }

    @NotNull
    @OneToOne(optional = true, cascade =CascadeType.ALL)
    @JoinColumn(name="passport_id")
    private Passport passport;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id")
    private Product employees;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getOklad() {
        return oklad;
    }

    public void setOklad(Double oklad) {
        this.oklad = oklad;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public Product getEmployees() {
        return employees;
    }

    public void setEmployees(Product employees) {
        this.employees = employees;
    }
}
