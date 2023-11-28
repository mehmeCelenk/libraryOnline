package com.book.libary.service;

import com.book.libary.model.entity.Book;
import com.book.libary.model.entity.Category;
import com.book.libary.model.entity.Image;
import com.book.libary.model.request.SaveBookRequset;
import com.book.libary.model.response.BookListItemResponse;
import com.book.libary.repository.BookRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookSaveService {
    private final BookRepository bookRepository;
    private final CategoryService categoryService;
    private final ImageService imageService;

    @Transactional
    public BookListItemResponse saveBook(SaveBookRequset saveBookRequest) throws IOException, SQLException {
        Category category = categoryService.loadCategory(saveBookRequest.getCategoryId());

        // MultipartFile'dan Blob oluştur
        byte[] bytes = saveBookRequest.getImage().getBytes();
        Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);

        // Image sınıfını oluştur ve veritabanına kaydet
        Image image = new Image();
        image.setImage(blob);
        imageService.create(image);

        // Book sınıfını oluştur ve veritabanına kaydet
        Book book = Book.builder()
                .category(category)
                .bookStatus(saveBookRequest.getBookStatus())
                .title(saveBookRequest.getTitle())
                .publisher(saveBookRequest.getPublisher())
                .lastPageNumber(saveBookRequest.getLastPageNumber())
                .autherName(saveBookRequest.getAutherName())
                .image(image)  // Oluşturulan Image'i kullan
                .totalPage(saveBookRequest.getTotalPage())
                .build();

        final Book fromDb = bookRepository.save(book);

        return BookListItemResponse.builder()
                .categoryName(fromDb.getCategory().getCategoryName())
                .id(fromDb.getId())
                .title(fromDb.getTitle())
                .bookStatus(fromDb.getBookStatus())
                .publisher(fromDb.getPublisher())
                .autherName(fromDb.getAutherName())
                .imageId(fromDb.getImage().getId())
                .lastPageNumber(fromDb.getLastPageNumber())
                .totalPage(fromDb.getTotalPage())
                .build();
    }

    public Optional<Book> getByBookId(Long bookId) {
      return bookRepository.findById(bookId);
    }

}
