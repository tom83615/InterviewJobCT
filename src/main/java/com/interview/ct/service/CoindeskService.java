package com.interview.ct.service;

import com.interview.ct.dto.CTCoindeskDTO;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;

public abstract class CoindeskService {

    public abstract JSONObject getApi() throws JSONException;

    public abstract CTCoindeskDTO getReshapeApi(JSONObject oriApi) throws JSONException, ParseException;

    public abstract void updateOrInsertBitcon(CTCoindeskDTO dto);
}
