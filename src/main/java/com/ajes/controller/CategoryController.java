package com.ajes.controller;

import com.ajes.model.Category;
import com.ajes.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jewellery")
@CrossOrigin("http://localhost:4200")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/category")
    public ResponseEntity<Category> addCategory(@RequestBody() Category category){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.addCategory(category));
    }

    @GetMapping("/category/")
    public ResponseEntity<List<Category>> getAll(){
        return ResponseEntity.status(HttpStatus.FOUND).body(categoryService.getAll());
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<Category> getByCategoryId(@PathVariable() Integer categoryId){
        return ResponseEntity.status(HttpStatus.FOUND).body(categoryService.getCategoryById(categoryId));
    }

    @PutMapping("/category/{categoryId}")
    public ResponseEntity<Category> modifyCategory(@PathVariable() Integer categoryId,@RequestBody() Category category){
        category.setCategoryId(categoryId);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.modifyCategory(category));
    }

    @DeleteMapping("/category/{categoryId}")
    public ResponseEntity<Category> deleteCategory(@PathVariable() Integer categoryId){
        return ResponseEntity.status(HttpStatus.FOUND).body(categoryService.deleteCategory(categoryId));
    }
}
