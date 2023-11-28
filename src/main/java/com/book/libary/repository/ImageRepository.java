package com.book.libary.repository;

import com.book.libary.model.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
   // Image getByImageUrl(String imageUrl);
}
