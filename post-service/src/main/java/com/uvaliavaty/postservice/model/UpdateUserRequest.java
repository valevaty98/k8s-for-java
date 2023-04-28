package com.uvaliavaty.postservice.model;

import lombok.Data;

@Data
public class UpdateUserRequest {

    private long id;
    private int amountOfPosts;
}
