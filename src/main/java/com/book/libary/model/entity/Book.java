package com.book.libary.model.entity;

import com.book.libary.model.enums.BookStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "books")
public class Book extends BaseEntity{
    @Column(name = "title")
    private String title;

    @Column(name = "auther_name")
    private String autherName;

    @Enumerated(EnumType.STRING)
    private BookStatus bookStatus;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "last_page_number")
    private Integer lastPageNumber;

    @Column(name = "total_page")
    private Integer totalPage;

    @Column(name = "user_id")
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToOne(mappedBy = "book", cascade = CascadeType.ALL)
    private Image image;
}
