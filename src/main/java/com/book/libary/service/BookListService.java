
package com.book.libary.service;
import com.book.libary.model.entity.Book;
import com.book.libary.model.entity.Category;
import com.book.libary.model.enums.BookStatus;
import com.book.libary.model.response.BookResponse;
import com.book.libary.repository.BookRepository;
import com.book.libary.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookListService {
    private final CategoryService categoryService;
    private final BookRepository bookRepository;

    public List<BookResponse> listBooks(int page, int size) {
        return bookRepository.findAll(PageRequest.of(page, size))
                .getContent()
                .stream()
                .map(this::convertResponse)  // convertResponse metodunu sınıf içinde non-static olarak çağır
                .collect(Collectors.toList());
    }

    private BookResponse convertResponse(Book model) {
        return BookResponse.builder()
                .id(model.getId())
                .autherName(model.getAutherName())  // autherName -> authorName düzeltildi
                .title(model.getTitle())
                .build();
    }

    public List<BookResponse> searchByCategory(Category categoryType) {
        final Category category = categoryService.findByName(categoryType.getCategoryName());
        return category.getBooks()
                .stream()
                .map(this::convertResponse)  // convertResponse metodunu sınıf içinde non-static olarak çağır
                .collect(Collectors.toList());
    }

    public List<BookResponse> searchByBookStatus(BookStatus bookStatus) {
        return bookRepository.findByBookStatus(bookStatus)
                .stream()
                .map(each ->
                        BookResponse.builder()
                                .id(each.getId())
                                .build())
                .collect(Collectors.toList());
    }

    public List<BookResponse> searchByTitle(String title) {
        return bookRepository.findByTitle(title)
                .stream()
                .map(each ->
                        BookResponse.builder()
                                .id(each.getId())
                                .build())
                .collect(Collectors.toList());
    }
}
