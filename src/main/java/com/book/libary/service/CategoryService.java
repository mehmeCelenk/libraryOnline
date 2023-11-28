package com.book.libary.service;

import com.book.libary.model.entity.Category;
import com.book.libary.model.request.SaveCategoryRequest;
import com.book.libary.model.response.CategoryResponse;
import com.book.libary.model.response.GetAllCategoryResponse;
import com.book.libary.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Category loadCategory(Long id){
        return categoryRepository.findById(id).orElseThrow();
    }

    public Category findByName(String categoryName) {
        return categoryRepository.findByCategoryName(categoryName).orElseThrow(RuntimeException::new);
    }

    public CategoryResponse save(SaveCategoryRequest saveCategoryRequest){
        final Category category = Category.builder()
                .categoryName(saveCategoryRequest.getCategoryName())
                .build();

        final Category fromDb = categoryRepository.save(category);

        return CategoryResponse.builder()
                .categoryName(fromDb.getCategoryName())
                .categoryId(fromDb.getId())
                .build();
    }

    public List<GetAllCategoryResponse> getAll(){
        return categoryRepository.findAll()
                .stream()
                .map(each ->
                        GetAllCategoryResponse.builder()
                                .id(each.getId())
                                .categoryName(each.getCategoryName())
                                .build())
                .collect(Collectors.toList());
    }
}
