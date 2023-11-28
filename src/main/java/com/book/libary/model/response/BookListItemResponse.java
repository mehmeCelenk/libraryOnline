package com.book.libary.model.response;

import com.book.libary.model.entity.Image;
import com.book.libary.model.enums.BookStatus;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.io.File;

@Data
@SuperBuilder
public class BookListItemResponse {
    private Long id;
    private String title;
    private String autherName;
    private BookStatus bookStatus;
    private String publisher;
    private Integer lastPageNumber;
    private String categoryName;
    private Long imageId;
    private Integer totalPage;
}
