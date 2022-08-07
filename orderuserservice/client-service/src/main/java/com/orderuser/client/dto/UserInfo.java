package com.orderuser.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

    int id;
    String username;
    String name;
    int age;
    Gender gender;

    public enum Gender {
        MALE,
        FEMALE,
        OTHER2
    }


}