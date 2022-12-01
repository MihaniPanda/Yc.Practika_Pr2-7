package com.example.demoModels.Models;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import javax.validation.constraints.*;

@Entity
public class Pod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min=2, max=35, message ="Наименование не может быть меньше 2 букв и не может быть больше 35 букв")
    private String name;
    @Size(min=2, max=35, message ="Наименование производителя не может быть меньше 2 букв и не может быть больше 35 букв")
    @Pattern(regexp = "^[а-яА-Яa-zA-Z]+$", message = "Разрешены только буквы")
    private String manufacturer;
    @NotNull
    @Min(value=500,message = "Цена не может быть меньше 500")
    private Double price;

    @ManyToMany
    @JoinTable(name="Compatibility",
            joinColumns=@JoinColumn(name="pod_id"),
            inverseJoinColumns = @JoinColumn(name="evaporator_id"))
    private Set<Evaporator> Evaporators = new HashSet<Evaporator>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Set<Evaporator> getEvaporators() {
        return Evaporators;
    }

    public void setEvaporators(Set<Evaporator> evaporators) {
        Evaporators = evaporators;
    }
}
