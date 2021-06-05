package com.hq.secondhand_book.repository;

import com.hq.secondhand_book.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BookRepository extends JpaRepository<Book,Integer> {

    Page<Book> findAll(Pageable pageable);

    List<Book> findAll();

    List<Book> getByUserId(int userId);

    Page<Book> findAllByUsable(int usable, Pageable pageable);

    List<Book> findAllByUsable(Integer usable);

    Page<Book> findByBookNameContainingAndUsable(String bookName,int usable,Pageable pageable);

    List<Book> findByBookNameContainingAndUsable(String bookName,int usable);

    Book findByUserIdAndBookNameAndBookSysnopsisAndBookPriceAndBookCategoryId(
            int userId,String bookName,String bookSysnopsis,Double bookPrice,int bookCategory);

    Page<Book> findByBookCategoryIdAndUsable(int categoryId,int usable, Pageable pageable);

    Book findByIdAndUsable(int id,int usable);

    Page<Book> findByBookNameContaining(String bookName,Pageable pageable);

    List<Book> findByBookNameContaining(String bookName);

    Book getById(int id);
}
