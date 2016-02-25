package com.gcaa.fplmb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.gcaa.fplmb.entity.AFTNMessageEntity;

@Repository
public interface AFTNMsgRepository extends CrudRepository<AFTNMessageEntity, Long>{
}
