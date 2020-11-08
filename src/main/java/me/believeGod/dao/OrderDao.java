package me.believeGod.dao;

import me.believeGod.entity.Order;
import me.believeGod.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDao extends JpaRepository<Order,Integer> {
}
