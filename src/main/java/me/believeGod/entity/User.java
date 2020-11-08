package me.believeGod.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * @ClassName User
 * @Description TODO
 * @Author Tim
 * @Date 2020/6/15 15:55
 * @Version 1.0
 */
@Entity
@Table(name = "TB_USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Integer id;

    @Column(name="USER_NAME",unique = true,nullable = false,length = 20)
    private String userName;

    @Column(name="PASSWORD",nullable = false)
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="F_ADDRESS_ID",referencedColumnName = "ID",
            foreignKey =@ForeignKey(ConstraintMode.NO_CONSTRAINT) )
    private  Address address;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="F_DISCOUNT_ID",referencedColumnName = "ID",
            foreignKey =@ForeignKey(ConstraintMode.NO_CONSTRAINT) )
    private Discount discount;


    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="F_USER_ID",referencedColumnName = "ID",
            foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Set<Order> orders;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="TB_USER_ROLE",
            joinColumns = {@JoinColumn(name="USER_ID")},
            inverseJoinColumns = {@JoinColumn(name="ROLE_ID")},
            foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT),
            inverseForeignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)
    )
    private Set<Role> roles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
