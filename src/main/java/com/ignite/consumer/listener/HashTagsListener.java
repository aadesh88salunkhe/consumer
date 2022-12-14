package com.ignite.consumer.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.ignite.consumer.model.HashTag;
import com.ignite.consumer.model.HashTagTweet;
import com.ignite.consumer.service.HashtagLabelService;
import com.ignite.consumer.service.HashtagTweetsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class HashTagsListener {

    @Autowired
    HashtagLabelService hashtagLabelService;

    @Autowired
    HashtagTweetsService hashtagTweetsService;

    @KafkaListener(topics = "${topic.name.hashtaglabel}", groupId = "hashTagLabelGroup")
    public void consumeHashTagAndLabel(ConsumerRecord<String, String> payload) {
        List<HashTag> list;
        try {
            list = new ObjectMapper().readValue(payload.value(),
                    TypeFactory.defaultInstance().constructCollectionType(List.class,
                            HashTag.class));

        hashtagLabelService.saveHashTagAndLabel(list);
        log.info("{} hashTagAndLabel saved!", list.size());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @KafkaListener(topics = "${topic.name.hashtagtweets}", groupId = "hashTagTweetsGroup")
    public void consumeHashTagAndTweets(ConsumerRecord<String, String> payload){
        List<HashTagTweet> list;
        try {
            list =  new  ObjectMapper().readValue(payload.value(),
                TypeFactory.defaultInstance().constructCollectionType(List.class,
                        HashTagTweet.class));
        hashtagTweetsService.saveHashTagAndTweets(list);
        log.info("{} HashTagAndTweets saved!", list.size());
    } catch (JsonProcessingException e) {
            e.printStackTrace();
    }
    }

}
