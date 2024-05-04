package com.ajes.service;

import com.ajes.model.Category;
import com.ajes.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    //It is post method(body)
    public Category addCategory(Category category)
    {
        List<Category>categoryList=categoryRepository.findAll();

        for (Category category1:categoryList){
            if(!category1.getCategoryName().equals(category.getCategoryName())){

                categoryRepository.save(category);
            }
            else{
                throw new RuntimeException(" Category Name Already present  ");
            }

        }


        return categoryRepository.save(category);
    }

    //It is get method(head)
    public List<Category> getAll(){
        return categoryRepository.findAll();
    }

    //It is get method(head)
    public Category getCategoryById(Integer categoryId){
        Optional<Category> optional = categoryRepository.findById(categoryId);
        if(optional.isPresent()){
            return optional.get();
        }
        else
            return  null;
    }

    //it is put method(head and body)
    public Category modifyCategory(Category category){
        return categoryRepository.save(category);
    }

    //it is delete method(head)
    public Category deleteCategory(Integer categoryId){
        Category category = getCategoryById(categoryId);
        categoryRepository.deleteById(categoryId);
        return  category;
    }


}
