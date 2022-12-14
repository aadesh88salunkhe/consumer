package com.ignite.consumer.service.impl;

import com.ignite.consumer.exception.ConsumerException;
import com.ignite.consumer.model.HashTag;
import com.ignite.consumer.model.HashTagTweet;
import com.ignite.consumer.model.User;
import com.ignite.consumer.model.UserAccountLabels;
import com.ignite.consumer.pojo.Label;
import com.ignite.consumer.pojo.ResponseData;
import com.ignite.consumer.pojo.Tweet;
import com.ignite.consumer.repository.HashTagTweetRepository;
import com.ignite.consumer.repository.HashtagLabelRepository;
import com.ignite.consumer.repository.UserAccountLabelsRepository;
import com.ignite.consumer.repository.UserRepository;
import com.ignite.consumer.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserAccountLabelsRepository userAccountLabelsRepository;
    @Autowired
    HashTagTweetRepository hashTagTweetRepository;
    @Autowired
    HashtagLabelRepository hashtagLabelRepository;

    @Override
    public ResponseData getTweets(String labels, int userId, String userName) throws ConsumerException {
        ResponseData responseData = new ResponseData();
        responseData.setUserName(userName);
        responseData.setUserId(userId);
        UserAccountLabels userAccountLabels = userAccountLabelsRepository.findByUserId(userId);
        if (userAccountLabels == null)
            throw new ConsumerException(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
        List<String> labelsListFromRequest = Arrays.asList(labels.split(","));
        if (userAccountLabels.getLabels() == null || userAccountLabels.getLabels().isEmpty()) {
            userAccountLabels.setLabels(labels);
            userAccountLabelsRepository.save(userAccountLabels);

            List<String> labelsListFromDB = Arrays.asList(userAccountLabels.getLabels().split(","));
            List<Label> labelDataList = getLabels(userAccountLabels, labelsListFromRequest, labelsListFromDB);
            responseData.setLabels(labelDataList);
        } else {
            List<String> labelsListFromDB = Arrays.asList(userAccountLabels.getLabels().split(","));
            List<Label> labelDataList = getLabels(userAccountLabels, labelsListFromRequest, labelsListFromDB);
            responseData.setLabels(labelDataList);
        }
        return responseData;
    }

    /**
     * get all user associated labels
     * @param userAccountLabels
     * @param labelsListFromRequest
     * @param labelsListFromDB
     * @return
     */
    private List<Label> getLabels(UserAccountLabels userAccountLabels, List<String> labelsListFromRequest, List<String> labelsListFromDB) {
        List<Label> labelDataList = new ArrayList<>();
        for (String label : labelsListFromRequest) {
            if (labelsListFromDB.contains(label.toLowerCase())) {
                setTweets(labelDataList, label);
            } else {
                userAccountLabels.setLabels(userAccountLabels.getLabels().concat(",").concat(label.toLowerCase()));
                userAccountLabelsRepository.save(userAccountLabels);
                setTweets(labelDataList, label);
            }
        }
        return labelDataList;
    }

    /**
     *  Get Tweets for hashtags
     * @param labelDataList
     * @param label
     */
    private void setTweets(List<Label> labelDataList, String label) {
        HashTag hashTagLabel = hashtagLabelRepository.findByLabel(label.trim());
        List<String> hashTagList = Arrays.asList(hashTagLabel != null ? hashTagLabel.getHashTagName().split(",") : new String[0]);
        List<Tweet> tweets = new ArrayList<>();
        for (String hashTag : hashTagList) {
            List<HashTagTweet> hashTagTweets = hashTagTweetRepository.findByHashTag(hashTag);
            Tweet tweet = new Tweet();
            tweet.setHashtag(hashTag);
            tweet.setTweets(hashTagTweets.stream().map(h -> h.getTweet()).collect(Collectors.toList()));
            if (tweet.getTweets().size() > 0)
                tweets.add(tweet);
        }

        Label labelData = new Label();
        labelData.setLabel(label);
        labelData.setTweets(tweets);
        labelDataList.add(labelData);
    }

    @Override
    public void saveUser(List<User> users) {
        userRepository.saveAll(users);
        log.info("{} Users saved !", users.size());
    }

    @Override
    public void saveUserAccountsLabels(List<UserAccountLabels> userAccountLabels) {
        userAccountLabelsRepository.saveAll(userAccountLabels);
        log.info("{} Users Account labels saved !", userAccountLabels.size());
    }
}
