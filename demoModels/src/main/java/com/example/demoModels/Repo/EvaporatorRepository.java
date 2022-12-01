package com.example.demoModels.Repo;

import com.example.demoModels.Models.Evaporator;
import com.example.demoModels.Models.Pod;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EvaporatorRepository extends CrudRepository<Evaporator, Long> {
    List<Evaporator> findAll();
    Evaporator findByType(String type);
}
