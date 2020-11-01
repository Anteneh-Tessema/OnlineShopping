package com.cs490.onlineshopping.service;

import com.cs490.onlineshopping.model.Category;
import com.cs490.onlineshopping.model.Product;
import com.cs490.onlineshopping.model.Vendor;
import com.cs490.onlineshopping.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return (List<Category>) categoryRepository.findAll();
    }

    public Category saveCategory(Category category) throws Exception {
        return categoryRepository.save(category);
    }

    public void deleteCategory(Category category) throws Exception {
        categoryRepository.delete(category);
    }

    public Optional<Category> findById(Integer id){
        return categoryRepository.findById(id);
    }
}
