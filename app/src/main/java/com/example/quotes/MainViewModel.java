package com.example.quotes;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class MainViewModel extends ViewModel {
    private Context mContext;
    private Quote[] quoteList;
    private int index;

    public MainViewModel(Context context) {
        this.mContext = context;
        this.quoteList = loadQuoteFromAssets();
    }

    private Quote[] loadQuoteFromAssets() {
        try {
            InputStream inputStream = mContext.getAssets().open("quotes.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String json = new String(buffer, StandardCharsets.UTF_8);
            Gson gson = new Gson();
            return gson.fromJson(json, Quote[].class);

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    public Quote getQuote() {
        return quoteList[index];
    }

    public Quote nextQuote() {
        index = (index + 1) % quoteList.length;
        return quoteList[index];
    }

    public Quote previousQuote() {
        index = (index - 1 + quoteList.length) % quoteList.length;
        return quoteList[index];
    }
}
