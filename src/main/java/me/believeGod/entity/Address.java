package me.believeGod.entity;

import javax.persistence.*;

/**
 * @ClassName Address
 * @Description TODO
 * @Author Tim
 * @Date 2020/6/15 20:54
 * @Version 1.0
 */
@Entity
@Table(name="TB_ADDRESS")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Integer id;

    @Column(name="NATION")
    private String nation;

    @Column(name="PROVINCE")
    private String province;

    @Column(name="CITY")
    private String city;

    @Column(name="AREA")
    private String area;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }


}
