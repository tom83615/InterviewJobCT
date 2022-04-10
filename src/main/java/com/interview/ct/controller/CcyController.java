package com.interview.ct.controller;


import com.interview.ct.entity.BitcoinEntity;
import com.interview.ct.service.CcyService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CcyController {

    @Autowired
    CcyService ccyService;

    @GetMapping("/ccy/{name}")
    public ResponseEntity getCcy(@PathVariable String name){
        Optional<BitcoinEntity> optional = ccyService.findByName(name);
        if(optional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(optional.get());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    };

    @PostMapping("/ccy")
    public ResponseEntity addCcy(@RequestBody BitcoinEntity ccy){
        BitcoinEntity entity = ccyService.addCcy(ccy);
        return ResponseEntity.status(HttpStatus.CREATED).body(entity);
    };

    @PutMapping("/ccy/{name}")
    public ResponseEntity updateCcy(@PathVariable String name, @RequestBody BitcoinEntity ccy) throws JSONException {
        JSONObject response = new JSONObject();
        Boolean status = ccyService.updateCcy(name, ccy);
        response.put("status", status);
        return ResponseEntity.status(HttpStatus.CREATED).body(response.toString());
    };

    @DeleteMapping("/ccy/{name}")
    public ResponseEntity deleteCcy(@PathVariable String name) throws JSONException {
        JSONObject response = new JSONObject();
        Boolean status = ccyService.deleteCcy(name);;
        response.put("status", status);
        return ResponseEntity.status(HttpStatus.CREATED).body(response.toString());
    };
}
