package com.ecommerce.microcommerce.dao;

import com.ecommerce.microcommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByPriceGreaterThan(int priceLimit);

    List<Product> findByNameLike(String recherche);

    @Query("FROM Product p WHERE p.price > :priceLimit")
    List<Product> findExpensiveProduct(@Param("priceLimit") int prix);
}
