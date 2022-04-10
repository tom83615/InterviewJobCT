package com.interview.ct.service;

import com.interview.ct.dao.BitcoinDao;
import com.interview.ct.entity.BitcoinEntity;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CcyServiceTests {
    @Autowired
    CcyService ccyService;

    @MockBean
    BitcoinDao bitcoinDao;

    @Test
    public void shouldGetCcyWhenCallFindByName(){
        BitcoinEntity expectedCcy = new BitcoinEntity();
        expectedCcy.setName("TWD");
        expectedCcy.setNameZh("新臺幣");
        expectedCcy.setRate(new BigDecimal("1000000.0000"));
        expectedCcy.setCreateTime(new Date());
        expectedCcy.setUpdateTime(new Date());

        Mockito.when(bitcoinDao.findByName("TWD")).thenReturn(Optional.of(expectedCcy));

        Optional<BitcoinEntity> actualCcy = ccyService.findByName("TWD");

        assertTrue(actualCcy.isPresent());
        assertEquals(expectedCcy, actualCcy.get());
    }

    @Test
    public void shouldGetCcyWhenCallAddCcy () {
        BitcoinEntity expectedCcy = new BitcoinEntity();
        expectedCcy.setName("TWD");
        expectedCcy.setNameZh("新臺幣");
        expectedCcy.setRate(new BigDecimal("1000000.0000"));
        expectedCcy.setCreateTime(new Date());
        expectedCcy.setUpdateTime(new Date());

        BitcoinEntity requiredCcy = new BitcoinEntity();
        requiredCcy.setName("TWD");
        requiredCcy.setNameZh("新臺幣");
        requiredCcy.setRate(new BigDecimal("1000000.0000"));
        requiredCcy.setCreateTime(new Date());
        requiredCcy.setUpdateTime(new Date());

        Mockito.when(bitcoinDao.save(requiredCcy)).thenReturn(expectedCcy);

        BitcoinEntity actualCcy = ccyService.addCcy(requiredCcy);

        assertEquals(expectedCcy, actualCcy);
    }

    @Test
    public void shouldGetTrueWhenCallUpdateCcy () {
        BitcoinEntity expectedCcy = new BitcoinEntity();
        expectedCcy.setName("TWD");
        expectedCcy.setNameZh("新臺幣");
        expectedCcy.setRate(new BigDecimal("1000000.0000"));
        expectedCcy.setCreateTime(new Date());
        expectedCcy.setUpdateTime(new Date());

        BitcoinEntity requiredCcy = new BitcoinEntity();
        requiredCcy.setName("TWD");
        requiredCcy.setNameZh("新臺幣");
        requiredCcy.setRate(new BigDecimal("1000000.0000"));
        requiredCcy.setCreateTime(new Date());
        requiredCcy.setUpdateTime(new Date());

        Mockito.when(bitcoinDao.findByName("TWD")).thenReturn(Optional.of(expectedCcy));

        Boolean actual = ccyService.updateCcy( "TWD", requiredCcy);

        assertTrue(actual);
    }

    @Test
    public void shouldGetFalseWhenCallUpdateCcyWithNoData () {
        BitcoinEntity requiredCcy = new BitcoinEntity();
        requiredCcy.setName("TWD");
        requiredCcy.setNameZh("新臺幣");
        requiredCcy.setRate(new BigDecimal("1000000.0000"));
        requiredCcy.setCreateTime(new Date());
        requiredCcy.setUpdateTime(new Date());

        Mockito.when(bitcoinDao.findByName("TWD")).thenReturn(Optional.empty());

        Boolean actual = ccyService.updateCcy( "TWD", requiredCcy);

        assertFalse(actual);
    }

    @Test
    public void shouldGetTrueWhenCallDeleteCcy() {
        BitcoinEntity expectedCcy = new BitcoinEntity();
        expectedCcy.setName("TWD");
        expectedCcy.setNameZh("新臺幣");
        expectedCcy.setRate(new BigDecimal("1000000.0000"));
        expectedCcy.setCreateTime(new Date());
        expectedCcy.setUpdateTime(new Date());

        Mockito.when(bitcoinDao.findByName("TWD")).thenReturn(Optional.of(expectedCcy));

        Boolean actual = ccyService.deleteCcy( "TWD");

        assertTrue(actual);
    }

    @Test
    public void shouldGetFalseWhenCallDeleteCcyWithNoData() {
        Mockito.when(bitcoinDao.findByName("TWD")).thenReturn(Optional.empty());

        Boolean actual = ccyService.deleteCcy( "TWD");

        assertFalse(actual);
    }
}
