package com.ecommerce.microcommerce.configuration;

import com.ecommerce.microcommerce.dao.ProductRepository;
import com.ecommerce.microcommerce.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;

@Configuration
public class InitialDataConfig {

    @Autowired
    private ProductRepository productRepository;

    @PostConstruct
    public void initInitialData() {
        if (this.productRepository.count() == 0L) {
            List<Product> products = new LinkedList<>();
            products.add(new Product(1, "Ordinateur portable", 350, 120));
            products.add(new Product(2, "Aspirateur Robot", 500, 200));
            products.add(new Product(3, "Table de Ping Pong", 750, 400));

            this.productRepository.saveAll(products);
        }
    }
}
