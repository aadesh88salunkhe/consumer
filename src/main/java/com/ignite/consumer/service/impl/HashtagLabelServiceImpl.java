package com.ignite.consumer.service.impl;

import com.ignite.consumer.model.HashTag;
import com.ignite.consumer.repository.HashtagLabelRepository;
import com.ignite.consumer.service.HashtagLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HashtagLabelServiceImpl implements HashtagLabelService {

    @Autowired
    HashtagLabelRepository hashtagLabelRepository;

    @Override
    public void saveHashTagAndLabel(List<HashTag> hashTag) {
        for (HashTag hashTag1:hashTag) {
            HashTag hashTagFromDB = hashtagLabelRepository.findByLabel(hashTag1.getLabel());
            if(hashTagFromDB ==null)
            hashtagLabelRepository.save(hashTag1);
        }

    }
}
