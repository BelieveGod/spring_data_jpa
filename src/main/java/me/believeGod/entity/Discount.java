package me.believeGod.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName Discount
 * @Description TODO
 * @Author Tim
 * @Date 2020/6/15 23:19
 * @Version 1.0
 */
@Entity
@Table(name = "TB_DISCOUNT")
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "DISCOUNTVALUE")
    private Double discountValue;

    @Column(name = "VALIDTEPEROID")
    private Date validtePeroid;

    @OneToOne(mappedBy = "discount",fetch = FetchType.LAZY)
    private User owner;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(Double discountValue) {
        this.discountValue = discountValue;
    }

    public Date getValidtePeroid() {
        return validtePeroid;
    }

    public void setValidtePeroid(Date validtePeroid) {
        this.validtePeroid = validtePeroid;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
