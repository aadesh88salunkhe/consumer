package com.ignite.consumer.controller;

import com.ignite.consumer.exception.ConsumerException;
import com.ignite.consumer.pojo.ResponseData;
import com.ignite.consumer.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping(value = "/tweets")
    ResponseData getUserTweetsByGenres(@RequestParam("labels") String labels, HttpServletRequest request) throws ConsumerException {
        return userService.getTweets(labels, (int) request.getAttribute("userId"), request.getHeader("username"));
    }
}
