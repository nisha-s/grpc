package com.orderuser.order.db;

import javax.persistence.*;

//import lombok.Data;
//import lombok.ToString;
//

//import java.util.Date;
//
//@Data
//@Entity
//@ToString
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private int orderId;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "no_of_items")
    private int noOfItems;

    @Column(name = "total_amount")
    private double totalAmount;

    public Order() {
    }

    public Order(int orderId, int customerId, int noOfItems, double totalAmount) {
        this.orderId = orderId;
        this.userId = userId;
        this.noOfItems = noOfItems;
        this.totalAmount = totalAmount;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getNoOfItems() {
        return noOfItems;
    }

    public void setNoOfItems(int noOfItems) {
        this.noOfItems = noOfItems;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}