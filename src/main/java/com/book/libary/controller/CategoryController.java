package com.book.libary.controller;

import com.book.libary.model.request.SaveCategoryRequest;
import com.book.libary.model.response.CategoryResponse;
import com.book.libary.model.response.GetAllCategoryResponse;
import com.book.libary.security.AllowAll;
import com.book.libary.security.Authenticated;
import com.book.libary.service.CategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
@CrossOrigin
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    @AllowAll
    @Transactional
    public ResponseEntity<CategoryResponse> saveCategory(@RequestBody SaveCategoryRequest saveCategoryRequest){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(categoryService.save(saveCategoryRequest));
    }

    @GetMapping("/getall")
    @AllowAll
    @Transactional
    public ResponseEntity<List<GetAllCategoryResponse>> getAll(){
        return ResponseEntity.ok(this.categoryService.getAll());
    }
}
