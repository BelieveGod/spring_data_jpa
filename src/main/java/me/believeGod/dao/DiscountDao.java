package me.believeGod.dao;

import me.believeGod.entity.Discount;
import me.believeGod.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiscountDao extends JpaRepository<Discount,Integer> {

}
