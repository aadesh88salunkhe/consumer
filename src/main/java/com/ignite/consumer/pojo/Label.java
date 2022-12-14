package com.ignite.consumer.pojo;

import lombok.Data;

import java.util.List;

@Data
public class Label {
    public String label;
    private List<Tweet> tweets;
}
