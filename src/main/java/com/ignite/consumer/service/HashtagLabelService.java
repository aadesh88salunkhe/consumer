package com.ignite.consumer.service;

import com.ignite.consumer.model.HashTag;

import java.util.List;

public interface HashtagLabelService {
    void saveHashTagAndLabel(List<HashTag> hashTag);

}
