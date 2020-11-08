package me.believeGod.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * @ClassName Role
 * @Description TODO
 * @Author Tim
 * @Date 2020/6/16 10:30
 * @Version 1.0
 */
@Entity
@Table(name = "TB_ROLE")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "IS_AVAILABLE")
    private Boolean available;

    @ManyToMany
    @JoinTable(name="TB_USER_ROLE",
            joinColumns = {@JoinColumn(name="ROLE_ID")},
            inverseJoinColumns = {@JoinColumn(name="USER_ID")},
            foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT),
            inverseForeignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)
    )
    private Set<User> users;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
