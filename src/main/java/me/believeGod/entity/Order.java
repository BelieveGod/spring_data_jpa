package me.believeGod.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName Order
 * @Description TODO
 * @Author Tim
 * @Date 2020/6/15 23:53
 * @Version 1.0
 */
@Entity
@Table(name = "TB_ORDER")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Integer id;

    @Column(name="ORDERDATE")
    private Date orderDate;

    @Column(name="TOTALSUM")
    private Integer totalSum;

    @Column(name="STATUS")
    private String status;

    @ManyToOne
    @JoinColumn(name="F_USER_ID",referencedColumnName = "ID",
            foreignKey =@ForeignKey(ConstraintMode.NO_CONSTRAINT) )
    private User owner;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(Integer totalSum) {
        this.totalSum = totalSum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
