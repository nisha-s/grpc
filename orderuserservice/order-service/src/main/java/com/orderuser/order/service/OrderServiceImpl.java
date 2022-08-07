package com.orderuser.order.service;


import com.orderuser.order.db.Order;
import com.orderuser.order.db.OrderRepository;
import com.shopping.stubs.order.OrderRequest;
import com.shopping.stubs.order.OrderResponse1;
import com.shopping.stubs.order.OrderServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@GrpcService
public class OrderServiceImpl extends OrderServiceGrpc.OrderServiceImplBase {

    @Autowired
    private OrderRepository orderRepository;

    private static final Logger logger = Logger.getLogger(OrderServiceImpl.class.getName());

    @Override
    public void unaryGrpc(OrderRequest request, StreamObserver<OrderResponse1> responseObserver) {
        System.out.println("received the request for " + request.getUserId());
//        List<Order> orders1 = this.orderRepository.findByUserId(request.getUserId());
//        System.out.println("order list size = " + orders1.size());
        List<com.shopping.stubs.order.Order> orderList = this.orderRepository.findByUserId(request.getUserId())
                .stream().map(order -> com.shopping.stubs.order.Order.newBuilder()
                        .setUserId(order.getUserId())
                        .setOrderId(order.getOrderId())
                        .setNoOfItems(order.getNoOfItems())
                        .setTotalAmount(order.getTotalAmount())
                        .build())
                .collect(Collectors.toList());
        OrderResponse1 orderResponse1 = OrderResponse1.newBuilder().addAllOrder(orderList).build();
        responseObserver.onNext(orderResponse1);
        responseObserver.onCompleted();

    }

//
//    @Override
//    public void serverStreamingGrpc(OrderRequest request, StreamObserver<OrderResponse2> responseObserver) {
//        List<Order> orders = orderDao.getOrders(request.getUserId());
////        logger.info("Got orders from OrderDao and converting to OrderResponse proto objects");
//
//        for(Order o : orders) {
//            OrderResponse2.Builder orderResponse2Builder =
//                    OrderResponse2.newBuilder()
//                            .setUserId(o.getUser_id())
//                            .setOrderId(o.getOrder_id())
//                            .setNoOfItems(o.getNoOfItems())
//                            .setTotalAmount(o.getTotalAmount())
//                            .setOrderDate(Timestamps.fromMillis(o.getOrderDate().getTime()));
//            OrderResponse2 orderResponse2 = orderResponse2Builder.build();
//            responseObserver.onNext(orderResponse2);
//        }
//        responseObserver.onCompleted();
//    }
//
//    @Override
//    public StreamObserver<OrderRequest> clientStreamingGrpc(StreamObserver<OrderResponse> responseObserver) {
//        //return super.getOrderForUser(responseObserver);
//        return new StreamObserver<OrderRequest>() {
//            int count = 0; // no_of_items
//            double price = 0.0; //total_amount
//            @Override
//            public void onNext(OrderRequest orderRequest) {
//                System.out.println("receive request from server : " + orderRequest.getUserId());
//                List<Order> orders = orderDao.getOrders(orderRequest.getUserId());
//                for(Order o : orders) {
//                    count += o.getNoOfItems();
//                    price += o.getTotalAmount();
//                }
//            }
//
//            @Override
//            public void onError(Throwable throwable) {
//
//            }
//
//            @Override
//            public void onCompleted() {
//                responseObserver.onNext(OrderResponse.newBuilder()
//                        .setNoOfItems(count)
//                        .setTotalAmount(price)
//                        .build());
//                responseObserver.onCompleted();
//            }
//        };
//    }
//
//    @Override
//    public StreamObserver<OrderRequest> bidirectionalStreamingGrpc(StreamObserver<OrderResponse1> responseObserver) {
//        return new StreamObserver<OrderRequest>() {
//            @Override
//            public void onNext(OrderRequest orderRequest) {
//                List<Order> orders = orderDao.getOrders(orderRequest.getUserId());
//                List<com.shopping.stubs.order.Order> ordersForUser = orders.stream().map(order -> com.shopping.stubs.order.Order.newBuilder()
//                                .setUserId(order.getUser_id())
//                                .setOrderId(order.getOrder_id())
//                                .setNoOfItems(order.getNoOfItems())
//                                .setTotalAmount(order.getTotalAmount())
//                                .setOrderDate(Timestamps.fromMillis(order.getOrderDate().getTime())).build())
//                        .collect(Collectors.toList());
//                OrderResponse1 orderResponse1 = OrderResponse1.newBuilder().addAllOrder(ordersForUser).build();
//                responseObserver.onNext(orderResponse1);
//
//            }
//
//            @Override
//            public void onError(Throwable throwable) {
//
//            }
//
//            @Override
//            public void onCompleted() {
//                responseObserver.onCompleted();
//            }
//        };
//    }

}