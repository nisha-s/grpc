package com.orderuser.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderList {

    int orderId;
    int userId;
    int noOfItems;
    double totalAmount;
}