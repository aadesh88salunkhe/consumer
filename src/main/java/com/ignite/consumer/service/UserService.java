package com.ignite.consumer.service;

import com.ignite.consumer.exception.ConsumerException;
import com.ignite.consumer.model.User;
import com.ignite.consumer.model.UserAccountLabels;
import com.ignite.consumer.pojo.ResponseData;

import java.util.List;

public interface UserService {
    ResponseData getTweets(String labels, int userId, String userName) throws ConsumerException;
    void saveUser(List<User> users);
    void saveUserAccountsLabels(List<UserAccountLabels> userAccountLabels);
}
