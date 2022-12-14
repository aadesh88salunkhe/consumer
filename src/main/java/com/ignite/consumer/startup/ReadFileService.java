package com.ignite.consumer.startup;

import com.ignite.consumer.model.User;
import com.ignite.consumer.model.UserAccountLabels;
import com.ignite.consumer.service.UserService;
import com.ignite.consumer.utils.CSVHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class ReadFileService {
    @Autowired
    UserService userService;

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        List<User> users = CSVHelper.csvToUser(getClass().getClassLoader().getResourceAsStream("User.csv"));
        userService.saveUser(users);
        List<UserAccountLabels> userAccountLabels = CSVHelper.csvToUserAccountLables(getClass().getClassLoader().getResourceAsStream("UserAccountLabels.csv"));
        userService.saveUserAccountsLabels(userAccountLabels);
        log.info("Users and Labels has been saved to DB! {} ");
    }
}
