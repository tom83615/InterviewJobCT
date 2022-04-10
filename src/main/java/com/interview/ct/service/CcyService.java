package com.interview.ct.service;

import com.interview.ct.entity.BitcoinEntity;

import java.util.Optional;

public abstract class CcyService {
    public abstract Iterable<BitcoinEntity> getAll();

    public abstract Optional<BitcoinEntity> findByName(String name);

    public abstract BitcoinEntity addCcy(BitcoinEntity ccy);

    public abstract Boolean updateCcy(String name, BitcoinEntity ccy);

    public abstract Boolean deleteCcy(String name);
}
