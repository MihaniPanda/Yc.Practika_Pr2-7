package com.example.demoModels.Models;

import javax.persistence.*;

import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Evaporator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    private Double price;

    @ManyToMany
    @JoinTable(name="Compatibility",
            joinColumns=@JoinColumn(name="evaporator_id"),
            inverseJoinColumns = @JoinColumn(name="pod_id"))
    private Set<Pod> Pods = new HashSet<Pod>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
