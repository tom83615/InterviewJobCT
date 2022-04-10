package com.interview.ct.controller;

import com.interview.ct.dto.CTCoindeskDTO;
import com.interview.ct.service.CoindeskService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/api")
public class CoindeskController {

    @Autowired
    CoindeskService coindeskService;

    @GetMapping("/coindesk")
    public ResponseEntity getApi() throws JSONException {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(coindeskService.getApi().toString());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    };

    @GetMapping("/coindeskRe")
    public ResponseEntity getReshapeApi() throws JSONException, ParseException {
        JSONObject oriApi = coindeskService.getApi();
        CTCoindeskDTO dto = coindeskService.getReshapeApi(oriApi);

        // save the data to DB
        coindeskService.updateOrInsertBitcon(dto);

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    };
}
