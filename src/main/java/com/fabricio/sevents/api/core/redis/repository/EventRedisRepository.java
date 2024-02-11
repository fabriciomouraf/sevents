package com.fabricio.sevents.api.core.redis.repository;

import com.fabricio.sevents.api.model.domain.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EventRedisRepository extends CrudRepository<Event, UUID> {
}