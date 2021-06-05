package com.hq.secondhand_book.repository;

import com.hq.secondhand_book.entity.PlaceTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PlaceTransactionRepository extends JpaRepository<PlaceTransaction, Integer> {

    List<PlaceTransaction> findAll();

    PlaceTransaction getById(int id);

    PlaceTransaction getByPlaceName(String placeName);

    List<PlaceTransaction> findAllByUsable(Integer usable);

    Page<PlaceTransaction> findAllByUsable(Integer usable, Pageable pageable);

    Page<PlaceTransaction> findByPlaceNameContainingAndUsable (String placeName,int usable,Pageable pageable);

    List<PlaceTransaction> findByPlaceNameContainingAndUsable(String placeName,int usable);

}
