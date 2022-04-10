package com.interview.ct.service;

import com.interview.ct.dto.CTCoindeskDTO;
import com.interview.ct.entity.BitcoinEntity;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CoindeskServiceTests {

    @Autowired
    CoindeskService coindeskService;

    @Autowired
    CcyService ccyService;

    @Test
    public void shouldGetApiWhenCallMethod() throws JSONException {
        JSONObject actual = coindeskService.getApi();

        assertNotEquals(new JSONObject(), actual);
    }

    @Test
    public void shouldGetReShapeApiWhenCallMethod() throws JSONException, ParseException {
        JSONObject apiRs = coindeskService.getApi();

        CTCoindeskDTO actual = coindeskService.getReshapeApi(apiRs);

        List<CTCoindeskDTO.CTCurrency> list = actual.getList();
        assertNotEquals(0, list.size());

        for (CTCoindeskDTO.CTCurrency ctccy:list) {
            assertNotNull(ctccy.getName());
            assertNotNull(ctccy.getNameZh());
            assertNotNull(ctccy.getRate());
        }
    }

    @Test
    public void shouldUpdateDBWhenCallMethodByGiveDTO() {
        BigDecimal expectedRate  = new BigDecimal("198564.5638");
        OffsetDateTime expectedRateUpdateOffsetDateTime = OffsetDateTime.now();
        Date expectedRateUpdateTime = new Date(expectedRateUpdateOffsetDateTime.toInstant().toEpochMilli());

        CTCoindeskDTO dto = new CTCoindeskDTO();
        dto.setUpdateTime(expectedRateUpdateOffsetDateTime.toString());

        CTCoindeskDTO.CTCurrency ctccy = new CTCoindeskDTO.CTCurrency();
        ctccy.setName("USD");
        ctccy.setRate(expectedRate);

        dto.setList(Collections.singletonList(ctccy));
        coindeskService.updateOrInsertBitcon(dto);

        Optional<BitcoinEntity> optional = ccyService.findByName("USD");
        BigDecimal actualRate = optional.get().getRate();
        Date actualUpdateTime = optional.get().getUpdateTime();

        assertEquals(expectedRate, actualRate);
        assertEquals(expectedRateUpdateTime, actualUpdateTime);
    }

    @Test
    public void shouldInsertDBWhenCallMethodByGiveDTO() {
        BigDecimal expectedRate  = new BigDecimal("36558.1234");
        OffsetDateTime expectedRateUpdateOffsetDateTime = OffsetDateTime.now();
        Date expectedRateUpdateTime = new Date(expectedRateUpdateOffsetDateTime.toInstant().toEpochMilli());
        String expectedName = "TWD";

        CTCoindeskDTO dto = new CTCoindeskDTO();
        dto.setUpdateTime(expectedRateUpdateOffsetDateTime.toString());

        CTCoindeskDTO.CTCurrency ctccy = new CTCoindeskDTO.CTCurrency();
        ctccy.setName(expectedName);
        ctccy.setRate(expectedRate);

        dto.setList(Collections.singletonList(ctccy));
        coindeskService.updateOrInsertBitcon(dto);

        Optional<BitcoinEntity> optional = ccyService.findByName("TWD");
        String actualName = optional.get().getName();
        BigDecimal actualRate = optional.get().getRate();

        assertEquals(expectedName, actualName);
        assertEquals(expectedRate, actualRate);
    }
}
