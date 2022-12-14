package com.ignite.consumer.service.impl;

import com.ignite.consumer.model.HashTagTweet;
import com.ignite.consumer.repository.HashTagTweetRepository;
import com.ignite.consumer.service.HashtagTweetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HashtagTweetsServiceImpl implements HashtagTweetsService {

    @Autowired
    HashTagTweetRepository hashTagTweetRepository;

    @Override
    public void saveHashTagAndTweets(List<HashTagTweet> hashTagTweet) {
        hashTagTweetRepository.saveAll(hashTagTweet);
    }
}
