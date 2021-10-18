package com.internship.tailormanager.listener;

import com.internship.tailormanager.model.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.persistence.*;

public class UserListener {
    private static Log log = LogFactory.getLog(UserListener.class);

    @PrePersist
    private void beforePost(User user) {
        log.info("On processing to add an user");
    }

    @PreUpdate
    private void beforeUpdate(User user) {
        log.info("On processing to update user: " + user.getUserId());
    }

    @PostPersist
    private void afterPost(User user) {
        log.info("Add process completed for user: " + user.getUserId());
    }

    @PostUpdate
    private void afterUpdate(User user) {
        log.info("Update process completed for user: " + user.getUserId());
    }

    @PostLoad
    private void afterLoad(User user) {
        log.info("User loaded from database: " + user.getUserId());
    }
}
