package com.apirest.Services;

import com.apirest.Exceptions.ResourceNotFoundException;
import com.apirest.Models.Product;
import com.apirest.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImplement implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Iterable<Product> saveList(Iterable<Product> list) {
        return productRepository.saveAll(list);
    }

    public Product findByProduct_id(Integer id) {
        Optional<Product> opt;
        opt = productRepository.findById(id);
        return opt.orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
    }

    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    public void deleteById(Integer id) {
        Product prod = findByProduct_id(id);
        if (prod != null) {
            productRepository.deleteById(id);
        }
    }

    @Override
    public Product update(Integer id, Product product) {
        Product prod = findByProduct_id(id);
        prod.setName(product.getName());
        prod.setPrice(product.getPrice());
        return save(prod);
    }
}
