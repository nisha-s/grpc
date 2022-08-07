package com.orderuser.client.service;

import com.orderuser.client.dto.OrderList;
import com.orderuser.client.dto.UserInfo;
import com.shopping.stubs.order.OrderRequest;
import com.shopping.stubs.order.OrderResponse1;
import com.shopping.stubs.order.OrderServiceGrpc;
import com.shopping.stubs.user.UserRequest;
import com.shopping.stubs.user.UserResponse;
import com.shopping.stubs.user.UserServiceGrpc;
import io.grpc.ManagedChannel;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @GrpcClient("user-service")
    private UserServiceGrpc.UserServiceBlockingStub userServiceBlockingStub;

    @GrpcClient("order-service")
    private OrderServiceGrpc.OrderServiceBlockingStub orderServiceBlockingStub;

    @Autowired
    private RestTemplate restTemplate;

    public UserInfo unaryCallCustomer(String username) {

        UserRequest userRequest = UserRequest.newBuilder().setUsername(username).build();
        UserResponse userResponse = this.userServiceBlockingStub.getUserDetails(userRequest);
        UserInfo obj = new UserInfo();
        obj.setId(userResponse.getId());
        obj.setUsername(userResponse.getUsername());
        obj.setName(userResponse.getName());
        obj.setAge(userResponse.getAge());
        obj.setGender(UserInfo.Gender.valueOf(userResponse.getGender().toString()));
//        return (UserInfo) userResponse.getAllFields();
        return obj;
    }

    public List<OrderList> unaryCall(Integer userId) {
        OrderRequest orderRequest = OrderRequest.newBuilder().setUserId(userId).build();
        OrderResponse1 orderResponse1 = this.orderServiceBlockingStub.unaryGrpc(orderRequest);

//        List<OrderList> orders = (List<OrderList>) restTemplate.getForObject("http://localhost:order-service" + userId, OrderList.class);

        return orderResponse1.getOrderList()
                .stream()
                .map(order -> new OrderList(order.getOrderId(), order.getUserId(), order.getNoOfItems(), order.getTotalAmount()))
                .collect(Collectors.toList());
//        return orders;
    }


}