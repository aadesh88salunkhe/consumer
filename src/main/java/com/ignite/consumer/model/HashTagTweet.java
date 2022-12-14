package com.ignite.consumer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data

@Entity
//defining class name as Table name
@Table
@JsonIgnoreProperties
public class HashTagTweet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    String hashTag;
    String tweet;
}
