package com.example.demoModels.Repo;

import com.example.demoModels.Models.Pod;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PodRepository extends CrudRepository<Pod, Long> {

    List<Pod> findAll();
    Pod findByName(String name);
}
