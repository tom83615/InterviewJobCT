package com.interview.ct.service.impl;

import com.interview.ct.dao.BitcoinDao;
import com.interview.ct.entity.BitcoinEntity;
import com.interview.ct.service.CcyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CcyServiceImpl extends CcyService {

    @Autowired
    BitcoinDao ccyDao;

    @Override
    public Iterable<BitcoinEntity> getAll() {
        return ccyDao.findAll();
    }

    @Override
    public Optional<BitcoinEntity> findByName(String name) {
        return ccyDao.findByName(name);
    }

    @Override
    public BitcoinEntity addCcy(BitcoinEntity todo) {
        return ccyDao.save(todo);
    }

    @Override
    public Boolean updateCcy(String name, BitcoinEntity ccy) {
        Optional<BitcoinEntity> isExistCcy = findByName(name);
        if (! isExistCcy.isPresent()) {
            return false;
        }
        BitcoinEntity existCcy = isExistCcy.get();
        if (ccy.getNameZh() == null || ccy.getNameZh().equals("")) {
            return false;
        }
        if (ccy.getRate() == null || ccy.getRate().compareTo(new BigDecimal(0)) < 1) {
            return false;
        }
        existCcy.setNameZh(ccy.getNameZh());
        existCcy.setRate(ccy.getRate());
        ccyDao.save(existCcy);
        return true;
    }

    @Override
    public Boolean deleteCcy(String name){
        Optional<BitcoinEntity> ccy = findByName(name);
        if (!ccy.isPresent()) {
            return false;
        }
        ccyDao.delete(ccy.get());
        return true;
    }
}
