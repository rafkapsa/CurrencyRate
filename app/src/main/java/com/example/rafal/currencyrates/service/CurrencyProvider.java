package com.example.rafal.currencyrates.service;

import android.os.AsyncTask;

import com.example.rafal.currencyrates.domain.CurrencyTable;

import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by Rafal on 2017-05-16.
 */

public class CurrencyProvider extends AsyncTask<String, Void, CurrencyTable> {
    private final static String NBP_API_URL = "http://api.nbp.pl/api/exchangerates/tables/{table}/?format=json";
    private final static String NBP_TABLE_PLACEHOLDER = "{table}";

    private CurrencyAsyncTaskListener listener;
    private CurrencyParser currencyParser;

    public CurrencyProvider(CurrencyAsyncTaskListener listener) {
        this.listener = listener;
        currencyParser = new CurrencyParser();
    }


    @Override
    protected CurrencyTable doInBackground(String... params) {

        try {
            String tableParam = params[0];
            String json = getJson(tableParam);
            return currencyParser.parseCurrencyTable(json);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
            }
        return null;
    }

    @Override
    protected void onPostExecute(CurrencyTable result) {
        listener.onDataLoaded(result);
    }


    private String getJson(String table) throws IOException {
        String urlString = NBP_API_URL.replace(NBP_TABLE_PLACEHOLDER, table);
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        InputStream inputStream = new BufferedInputStream(connection.getInputStream());

        StringBuilder sb = new StringBuilder();
        Reader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));

        int charCode;

        while ((charCode = reader.read()) != -1) {
            char character = (char) charCode;
            sb.append(character);
        }

        connection.disconnect();

        return sb.toString();
    }

    public interface CurrencyAsyncTaskListener {
        void onDataLoaded(CurrencyTable currencyTable);
    }


}
