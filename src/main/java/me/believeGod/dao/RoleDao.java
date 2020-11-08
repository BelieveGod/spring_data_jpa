package me.believeGod.dao;

import me.believeGod.entity.Role;
import me.believeGod.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName RoleDao
 * @Description TODO
 * @Author Tim
 * @Date 2020/6/16 20:34
 * @Version 1.0
 */
public interface RoleDao extends JpaRepository<Role,Integer> {
}
