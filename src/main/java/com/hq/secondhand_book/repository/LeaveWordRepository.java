package com.hq.secondhand_book.repository;

import com.hq.secondhand_book.entity.LeaveWord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface LeaveWordRepository extends JpaRepository<LeaveWord, Integer> {
    List<LeaveWord> findByBookIdOrderByCstModifyDesc(int bookId);

    List<LeaveWord> findAllByUsable(Integer usable);

    Page<LeaveWord> findAllByUsable(int usable, Pageable pageable);

    Page<LeaveWord> findByLeaveContentContainingAndUsable(String LeaveContent,int usable,Pageable pageable);
    List<LeaveWord> findByLeaveContentContainingAndUsable(String LeaveContent,int usable);

    LeaveWord getById(int leaveId);
}
