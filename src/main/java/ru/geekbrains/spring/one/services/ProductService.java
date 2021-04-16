package ru.geekbrains.spring.one.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.geekbrains.spring.one.model.Product;
import ru.geekbrains.spring.one.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Component
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findOneById(Long id) {
        return productRepository.findOneById(id);
    }

    public void save(Product product) {
        productRepository.save(product);
    }

}
