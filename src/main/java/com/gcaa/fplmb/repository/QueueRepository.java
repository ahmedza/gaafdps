package com.gcaa.fplmb.repository;

import org.springframework.data.repository.CrudRepository;

import com.gcaa.fplmb.entity.MessageQueue;

public interface QueueRepository extends CrudRepository<MessageQueue, Long> {

}
