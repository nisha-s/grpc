package com.orderuser.client.controller;


import com.orderuser.client.dto.OrderList;
import com.orderuser.client.dto.UserInfo;
import com.orderuser.client.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class OrderUserController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/user/{username}")
    public UserInfo getUserInfo(@PathVariable String username) {
        return this.clientService.unaryCallCustomer(username);
    }

    @GetMapping("/order/{userId}")
    public List<OrderList> getAllOrders(@PathVariable Integer userId) {
        return this.clientService.unaryCall(userId);
    }

}