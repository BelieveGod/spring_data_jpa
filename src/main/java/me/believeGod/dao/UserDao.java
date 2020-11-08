package me.believeGod.dao;

import me.believeGod.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

/**
 * @ClassName UserDao
 * @Description TODO
 * @Author Tim
 * @Date 2020/6/15 16:00
 * @Version 1.0
 */

public interface UserDao  extends JpaRepository<User,Integer> , JpaSpecificationExecutor<User> {
    public Optional<User> findByUserName(String userName);


}
