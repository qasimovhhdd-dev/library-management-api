package com.yourname.library.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByOrderDateBetween(LocalDateTime start, LocalDateTime end);
    @Query("SELECT DISTINCT o FROM Order o JOIN o.items i WHERE i.book.id = :bookId")
    List<Order> findOrdersContainingBook(@Param("bookId") Long bookId);
}