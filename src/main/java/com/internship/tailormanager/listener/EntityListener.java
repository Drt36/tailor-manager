package com.internship.tailormanager.listener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.persistence.*;

public class EntityListener {
    private static Log log = LogFactory.getLog(EntityListener.class);

    @PrePersist
    private void beforePost(Object object) {
        log.info(object+"On processing to add");
    }

    @PreUpdate
    private void beforeUpdate(Object object) {
        log.info("On processing to update user: " +object);
    }

    @PostPersist
    private void afterPost(Object object) {
        log.info("Add process completed for user: " + object);
    }

    @PostUpdate
    private void afterUpdate(Object object) {
        log.info("Update process completed for user: " + object);
    }

    @PostLoad
    private void afterLoad(Object object) {
        log.info("User loaded from database: " + object);
    }
}
