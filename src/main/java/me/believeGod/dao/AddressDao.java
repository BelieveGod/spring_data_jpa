package me.believeGod.dao;

import me.believeGod.entity.Address;
import me.believeGod.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressDao extends JpaRepository<Address,Integer> {
    public List<Address> findByCity(String city);
    public Address findByArea(String area);
    public String test();
}
