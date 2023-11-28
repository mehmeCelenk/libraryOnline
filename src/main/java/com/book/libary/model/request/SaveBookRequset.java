package com.book.libary.model.request;

import com.book.libary.model.entity.Image;
import com.book.libary.model.enums.BookStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveBookRequset {
    @NotBlank
    private String title;

    @NotBlank
    private String autherName;

    @NotNull
    private BookStatus bookStatus;

    @NotBlank
    private String publisher;

    @NotNull
    private Integer lastPageNumber;

    @NotNull
    private Long categoryId;

    private MultipartFile image;

    @NotNull
    private Integer totalPage;
}
