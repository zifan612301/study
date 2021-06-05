package com.hq.secondhand_book.repository;

import com.hq.secondhand_book.entity.BookCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BookCategoryRepository extends JpaRepository<BookCategory, Integer> {
    List<BookCategory> findAllByUsable(Integer usable);
    Page<BookCategory> findAllByUsable(int usable, Pageable pageable);

    Page<BookCategory> findByBookCategoryNameContainingAndUsable(String CategoryName,int usable,Pageable pageable);
    List<BookCategory> findByBookCategoryNameContainingAndUsable(String CategoryName,int usable);

    BookCategory getByBookCategoryName(String categoryName);

    BookCategory getById(int id);
}
