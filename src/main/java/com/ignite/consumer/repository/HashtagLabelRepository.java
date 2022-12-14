package com.ignite.consumer.repository;

import com.ignite.consumer.model.HashTag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HashtagLabelRepository extends CrudRepository<HashTag, Long> {
    HashTag findByLabel(String label);
}
