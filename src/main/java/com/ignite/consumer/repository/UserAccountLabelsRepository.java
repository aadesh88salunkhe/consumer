package com.ignite.consumer.repository;

import com.ignite.consumer.model.UserAccountLabels;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountLabelsRepository extends CrudRepository<UserAccountLabels, Long> {
    UserAccountLabels findByUserId(int userId);
}
