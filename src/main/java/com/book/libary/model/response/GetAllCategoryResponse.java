package com.book.libary.model.response;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class GetAllCategoryResponse {
    private Long id;
    private String categoryName;
}
