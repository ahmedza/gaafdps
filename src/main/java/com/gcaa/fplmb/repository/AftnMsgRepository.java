package com.gcaa.fplmb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gcaa.fplmb.entity.AftnMessageEntity;

@Repository
public interface AftnMsgRepository extends CrudRepository<AftnMessageEntity, Long>{

	
}
