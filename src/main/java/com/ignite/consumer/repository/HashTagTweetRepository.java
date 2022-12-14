package com.ignite.consumer.repository;

import com.ignite.consumer.model.HashTagTweet;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HashTagTweetRepository  extends CrudRepository<HashTagTweet, Long> {
    List<HashTagTweet> findByHashTag(String hashTag);
    @Query("SELECT tweet FROM HashTagTweet WHERE hashTag = :hashTag")
    List<String> findTweetByHashTag(@Param("hashTag") String hashTag);
}
