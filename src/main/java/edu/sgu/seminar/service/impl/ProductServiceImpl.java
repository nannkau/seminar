package edu.sgu.seminar.service.impl;

import edu.sgu.seminar.entity.Product;
import edu.sgu.seminar.repository.ProductRepository;
import edu.sgu.seminar.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }
}
