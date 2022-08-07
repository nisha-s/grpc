package com.orderuser.order.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

//    @Query(value = "select * from orders where user_id = ?1", nativeQuery = false)
//    List<Order> getOrders(int userId);

    List<Order> findByUserId(int userId);

}