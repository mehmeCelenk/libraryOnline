package com.book.libary.model.response;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class CategoryResponse {
    private Long categoryId;
    private String categoryName;
}
