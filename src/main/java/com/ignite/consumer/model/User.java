package com.ignite.consumer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table
@JsonIgnoreProperties
public class User {
    @Id
    private Integer id;
    private String username;
    private String password;
}
