package com.ignite.consumer.service;

import com.ignite.consumer.model.HashTagTweet;

import java.util.List;

public interface HashtagTweetsService {
    void saveHashTagAndTweets(List<HashTagTweet> hashTagTweet);

}
