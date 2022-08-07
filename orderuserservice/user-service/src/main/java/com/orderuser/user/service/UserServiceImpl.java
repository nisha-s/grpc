package com.orderuser.user.service;

import com.orderuser.user.db.User;
import com.orderuser.user.db.UserRepository;
import com.shopping.stubs.user.Gender;
import com.shopping.stubs.user.UserRequest;
import com.shopping.stubs.user.UserResponse;
import com.shopping.stubs.user.UserServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@GrpcService
public class UserServiceImpl extends UserServiceGrpc.UserServiceImplBase {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void getUserDetails(UserRequest request, StreamObserver<UserResponse> responseObserver) {
        UserResponse.Builder builder = UserResponse.newBuilder();
        List<User> user = this.userRepository.findByUsername(request.getUsername());
//        System.out.println("user = " + user.get(0).getUsername());
        builder.setId(user.get(0).getId())
                .setUsername(user.get(0).getUsername())
                .setName(user.get(0).getName())
                .setAge(user.get(0).getAge())
                .setGender(Gender.valueOf(user.get(0).getGender()));
        responseObserver.onNext(builder.build());
        responseObserver.onCompleted();
    }
}
