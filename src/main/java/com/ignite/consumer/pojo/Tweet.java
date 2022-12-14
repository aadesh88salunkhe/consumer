package com.ignite.consumer.pojo;

import lombok.Data;

import java.util.List;

@Data
public class Tweet {
    String hashtag;
    public List<String> tweets;

}
