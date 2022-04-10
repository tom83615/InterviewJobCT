package com.interview.ct.service.impl;

import com.interview.ct.dto.CTCoindeskDTO;
import com.interview.ct.entity.BitcoinEntity;
import com.interview.ct.service.CcyService;
import com.interview.ct.service.CoindeskService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

@Service
public class CoindeskServiceImpl extends CoindeskService {
    final String uri = "https://api.coindesk.com/v1/bpi/currentprice.json";
    final String BPI = "bpi";
    final String TIME = "time";

    @Autowired
    CcyService ccyService;

    @Override
    public JSONObject getApi() throws JSONException {
        RestTemplate restTemplate = new RestTemplate();
        String plainTxt = restTemplate.getForObject(uri, String.class);
        return new JSONObject(plainTxt);
    }

    @Override
    public CTCoindeskDTO getReshapeApi(JSONObject oriApi) throws JSONException, ParseException {
        CTCoindeskDTO dto = new CTCoindeskDTO();

        // updateTime
        JSONObject time = oriApi.getJSONObject(TIME);
        dto.setUpdateTime(time.getString("updatedISO"));

        // currencies
        JSONObject currencies = oriApi.getJSONObject(BPI);
        List<CTCoindeskDTO.CTCurrency> list = new ArrayList<>();
        for (Iterator it = currencies.keys(); it.hasNext(); ) {
            String key = (String) it.next();
            JSONObject currency =  currencies.getJSONObject(key);
            CTCoindeskDTO.CTCurrency temp = new CTCoindeskDTO.CTCurrency();
            temp.setName(currency.optString("code"));
            temp.setRate(new BigDecimal(
                    currency.optString("rate").replace(",","")));

            // find A chinese name if founded
            Optional<BitcoinEntity> optional = ccyService.findByName(currency.optString("code"));
            optional.ifPresent(bitcoinEntity -> temp.setNameZh(bitcoinEntity.getNameZh()));

            list.add(temp);
        }
        dto.setList(list);

        return dto;
    }

    @Override
    public void updateOrInsertBitcon(CTCoindeskDTO dto) {
        List<CTCoindeskDTO.CTCurrency> list = dto.getList();
        for (CTCoindeskDTO.CTCurrency ctccy: list){
            String name = ctccy.getName();
            Optional<BitcoinEntity> optional = ccyService.findByName(name);
            if(optional.isPresent()){
                BitcoinEntity newBicoinCcy = optional.get();
                newBicoinCcy.setRate(ctccy.getRate());
                newBicoinCcy.setUpdateTime(dto.pickUpdateTimeAsDate());

                ccyService.updateCcy(name, newBicoinCcy);
            }else{
                BitcoinEntity newBicoinCcy = new BitcoinEntity();
                newBicoinCcy.setName(ctccy.getName());
                newBicoinCcy.setRate(ctccy.getRate());

                ccyService.addCcy(newBicoinCcy);
            }
        }
    }
}
