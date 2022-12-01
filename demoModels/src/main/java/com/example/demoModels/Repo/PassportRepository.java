package com.example.demoModels.Repo;
import com.example.demoModels.Models.Passport;
import org.springframework.data.repository.CrudRepository;

public interface PassportRepository extends CrudRepository<Passport, Long>{
    Passport findByNumber(String number);
}
