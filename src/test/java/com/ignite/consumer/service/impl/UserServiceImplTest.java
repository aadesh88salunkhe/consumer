package com.ignite.consumer.service.impl;

import com.ignite.consumer.exception.ConsumerException;
import com.ignite.consumer.model.HashTag;
import com.ignite.consumer.model.HashTagTweet;
import com.ignite.consumer.model.UserAccountLabels;
import com.ignite.consumer.pojo.ResponseData;
import com.ignite.consumer.repository.HashTagTweetRepository;
import com.ignite.consumer.repository.HashtagLabelRepository;
import com.ignite.consumer.repository.UserAccountLabelsRepository;
import com.ignite.consumer.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest()
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @Mock
    UserAccountLabelsRepository userAccountLabelsRepository;
    @Mock
    HashTagTweetRepository hashTagTweetRepository;
    @Mock
    HashtagLabelRepository hashtagLabelRepository;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    void saveUserAccountsLabels() throws ConsumerException {
        UserAccountLabels userLabels = new UserAccountLabels();
        userLabels.setLabels("information,food");
        when(userAccountLabelsRepository.findByUserId(anyInt())).thenReturn(userLabels);
        when(userAccountLabelsRepository.save(any())).thenReturn(userLabels);
        HashTag ht = new HashTag();
        ht.setHashTagName("information");
        when(hashtagLabelRepository.findByLabel(anyString())).thenReturn(ht);
        HashTagTweet htt = new HashTagTweet();
        htt.setTweet("information Tweet");
        when(hashTagTweetRepository.findByHashTag(anyString())).thenReturn(Arrays.asList(htt));
        ResponseData data = userService.getTweets("Information,Food", 1, "username");
        assertNotNull(data.getLabels().size());
    }
}