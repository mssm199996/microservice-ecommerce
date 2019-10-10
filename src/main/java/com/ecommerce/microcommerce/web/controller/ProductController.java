package com.ecommerce.microcommerce.web.controller;

import com.ecommerce.microcommerce.dao.ProductRepository;
import com.ecommerce.microcommerce.dto.ProductWithMarginDto;
import com.ecommerce.microcommerce.model.Product;
import com.ecommerce.microcommerce.web.exceptions.ProduitIntrouvableException;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("productsWithMargin")
    public List<ProductWithMarginDto> productWithMargin() {
        List<Product> products = this.productRepository.findAll();
        List<ProductWithMarginDto> productWithMarginDtos = new ArrayList<>(products.size());

        for (Product product : products)
            productWithMarginDtos.add(new ProductWithMarginDto(product, product.getMarge()));

        return productWithMarginDtos;
    }

    @GetMapping("products")
    public MappingJacksonValue products() {
        Iterable<Product> produits = this.productRepository.findAll();

        SimpleBeanPropertyFilter noBuyingPriceFilter =
                SimpleBeanPropertyFilter.serializeAllExcept("buyingPrice");

        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("noBuyingPriceFilter", noBuyingPriceFilter);

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(produits);
        mappingJacksonValue.setFilters(filterProvider);

        return mappingJacksonValue;
    }

    @GetMapping("{id}")
    @ApiOperation("Fetch the product by its id if it actually exists in the repository")
    public Product productById(@PathVariable int id) {
        Optional<Product> productOptional = this.productRepository.findById(id);

        if (productOptional.isPresent())
            return productOptional.get();
        else
            throw new ProduitIntrouvableException("The product " + id + " doesn't exist !");
    }

    @PostMapping
    public ResponseEntity<Void> addProduct(@Valid @RequestBody Product product) {
        Product productToAdd = this.productRepository.save(product);

        if (productToAdd != null) {
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(productToAdd.getId())
                    .toUri();

            return ResponseEntity.created(location).build();
        } else
            return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable int id) {
        this.productRepository.deleteById(id);
    }

    @PutMapping
    public void updateProduit(@RequestBody Product product) {
        this.productRepository.save(product);
    }

    @GetMapping("findExpensiveProduct/{prix}")
    public List<Product> testeDeRequetes(@PathVariable int prix) {
        return this.productRepository.findExpensiveProduct(prix);
    }
}
