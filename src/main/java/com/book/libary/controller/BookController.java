package com.book.libary.controller;

import com.book.libary.model.entity.Category;
import com.book.libary.model.enums.BookStatus;
import com.book.libary.model.request.SaveBookRequset;
import com.book.libary.model.response.BookListItemResponse;
import com.book.libary.model.response.BookResponse;
import com.book.libary.security.AllowAll;
import com.book.libary.security.Authenticated;
import com.book.libary.service.BookListService;
import com.book.libary.service.BookSaveService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
@CrossOrigin
public class BookController {
    private final BookListService bookListService;
    private final BookSaveService bookSaveService;


    @PostMapping("/save")
    @Authenticated
    @Transactional
    public ResponseEntity<BookListItemResponse> saveBook(@Valid @RequestBody SaveBookRequset saveBookRequest) throws SQLException, IOException {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(bookSaveService.saveBook(saveBookRequest));
    }


    @GetMapping
    @AllowAll
    public ResponseEntity<List<BookResponse>> listBook(@RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
        return ResponseEntity.ok(bookListService.listBooks(page, size));
    }

    @GetMapping("/list/{categoryName}")
    @AllowAll
    public ResponseEntity<List<BookResponse>> listByCategory(@PathVariable Category categoryName) {
        return ResponseEntity.ok(this.bookListService.searchByCategory(categoryName));
    }

    @GetMapping("/list/{bookStatus}")
    @Authenticated
    public ResponseEntity<List<BookResponse>> listBookStatus(@PathVariable BookStatus bookStatus) {
        return ResponseEntity.ok(this.bookListService.searchByBookStatus(bookStatus));
    }

    @GetMapping("/list/{title}")
    @AllowAll
    public ResponseEntity<List<BookResponse>> listBookTitle(@PathVariable String title) {
        return ResponseEntity.ok(this.bookListService.searchByTitle(title));
    }

}
