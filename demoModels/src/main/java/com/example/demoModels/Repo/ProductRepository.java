package com.example.demoModels.Repo;


import com.example.demoModels.Models.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long>{
    List<Product> findByNameproductContains(String firstname);
    List<Product> findByNameproduct(String firstname);

    Product findByDescription (String description);


}
