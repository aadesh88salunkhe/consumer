package com.ignite.consumer.pojo;

import lombok.Data;

import java.util.List;

@Data
public class ResponseData {
    private int userId;
    private String userName;
    private List<Label> labels;
}
