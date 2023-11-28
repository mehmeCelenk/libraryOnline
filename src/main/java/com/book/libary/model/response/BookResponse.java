package com.book.libary.model.response;

import com.book.libary.model.entity.Image;
import com.book.libary.model.enums.BookStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class BookResponse {
    private Long id;
    private String title;
    private String autherName;
    private BookStatus bookStatus;
    private String publisher;
    private Integer lastPageNumber;
    private Long categoryId;
    private Image imageBlob;
    private Integer totalPage;
    private String ImageUrl;
}

