package com.interview.ct.dao;

import com.interview.ct.entity.BitcoinEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BitcoinDao extends CrudRepository<BitcoinEntity, Integer> {
    Optional<BitcoinEntity> findByName(String name);
}