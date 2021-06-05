package com.hq.secondhand_book.repository;

import com.hq.secondhand_book.entity.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CollectionRepository extends JpaRepository<Collection,Integer> {

    Collection getByBookIdAndUserIdAndUsable(int bookId, int userId, int usable);

    Collection getByBookIdAndUserId(int bookId, int userId);

    List<Collection> getByUserIdAndUsable(int userId,int usable);

    Page<Collection> getByUserIdAndUsable(int userId, int usable, Pageable pageable);
}
