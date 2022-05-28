package com.revature.bibimbop.order;

import com.revature.bibimbop.order.Order;
import javax.persistence.*;

import java.util.Objects;
@Entity
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    private String type;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    private Pokemon pokemon;

    public Order() {
    }

    public Order(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrder() {
        return type;
    }

    public void setOrder(String type) {
        this.type = type;
    }
}
