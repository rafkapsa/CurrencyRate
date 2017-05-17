package com.example.rafal.currencyrates;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rafal on 2017-05-16.
 */

public class CurrencyParser {

    public CurrencyTable parseCurrencyTable(String string) throws JSONException {
        JSONArray array = new JSONArray(string);
        JSONObject jsonObject = array.getJSONObject(0);
        String table = jsonObject.getString("table");
        String number = jsonObject.getString("no");
        String effectiveDate = jsonObject.getString("effectiveDate");

        CurrencyTable currencyTable = new CurrencyTable();
        currencyTable.setTable(table);
        currencyTable.setNumber(number);
        currencyTable.setEffectiveDate(effectiveDate);

        JSONArray currenciesJson = jsonObject.getJSONArray("rates");
        List<Currency> currencies = parseRateArray(currenciesJson);
        currencyTable.setCurrencies(currencies);

        return currencyTable;
    }

    private List<Currency> parseRateArray(JSONArray array){
        List<Currency> currencies = new ArrayList<>();

        for(int i=0; i < array.length(); i++){
            JSONObject jsonRate = array.optJSONObject(i);
            String symbol = jsonRate.optString("code");
            String name = jsonRate.optString("currency");
            double rate = jsonRate.optDouble("mid");

            Currency currency = new Currency();
            currency.setSymbol(symbol);
            currency.setName(name);
            currency.setRate(rate);

            currencies.add(currency);
        }
        return currencies;
    }
}
