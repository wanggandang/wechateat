package com.itcast.manager.repository;

import com.itcast.manager.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderDetail,String> {

}
