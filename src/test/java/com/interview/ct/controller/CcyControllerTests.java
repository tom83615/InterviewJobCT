package com.interview.ct.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.interview.ct.service.CcyService;

import org.junit.jupiter.api.Test;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CcyControllerTests {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    CcyService ccyService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void shouldGetADefaultCcyWhenCallMethod() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/ccy/USD")
                        .accept(MediaType.APPLICATION_JSON ))
                        .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/ccy/GBP")
                        .accept(MediaType.APPLICATION_JSON ))
                        .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/ccy/EUR")
                        .accept(MediaType.APPLICATION_JSON ))
                        .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void shouldGetA404WhenCallMethod() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/ccy/TWD")
                        .accept(MediaType.APPLICATION_JSON ))
                        .andExpect(status().isNotFound());
    }

    @Test
    public void shouldGetANewAddCcyWhenCallMethod() throws Exception {
        JSONObject todoObject = new JSONObject();
        todoObject.put("name", "TWD");
        todoObject.put("nameZh", "新臺幣");
        todoObject.put("rate", "100000.0213");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/ccy")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(todoObject)))
                .andExpect(status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/ccy/TWD")
                        .accept(MediaType.APPLICATION_JSON ))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void shouldGetTrueWhenCallUpdate() throws Exception {
        JSONObject ccyRqData = new JSONObject();
        ccyRqData.put("name", "USD");
        ccyRqData.put("nameZh", "美美金");
        ccyRqData.put("rate", "10");

        mockMvc.perform(MockMvcRequestBuilders.get("/api/ccy/USD")
                        .accept(MediaType.APPLICATION_JSON ))
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.put("/api/ccy/USD")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(ccyRqData)))
                .andExpect(status().isCreated())
                .andExpect(content().json("{'status':true}"));
    }

    @Test
    public void shouldGetTrueWhenCallDelete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/ccy/USD")
                        .accept(MediaType.APPLICATION_JSON ))
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/ccy/USD")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json("{'status':true}"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/ccy/USD")
                        .accept(MediaType.APPLICATION_JSON ))
                .andExpect(status().isNotFound());
    }
}
