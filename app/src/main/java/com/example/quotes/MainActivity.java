package com.example.quotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.gson.internal.JavaVersion;

public class MainActivity extends AppCompatActivity {

    private MainViewModel mMainViewModel;
    private TextView quote_text, quote_author;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View windowDecoderView = getWindow().getDecorView();
        windowDecoderView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        mMainViewModel = new ViewModelProvider(this, new MainViewModelFactory(getApplication()))
                .get(MainViewModel.class);
        setQuote(mMainViewModel.getQuote());



    }

    private void setQuote(Quote quote) {
        getQuoteText().setText(quote.getText());
        getQuoteAuthor().setText(quote.getAuthor());
    }

    private TextView getQuoteAuthor() {
        return findViewById(R.id.quote_author);
    }

    private TextView getQuoteText() {
        return findViewById(R.id.quote_text);
    }

    public void onNext(View view) {
        setQuote(mMainViewModel.nextQuote());
    }
    public void onPrevious(View view) {
        setQuote(mMainViewModel.previousQuote());
    }
    public void onShare(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, mMainViewModel.getQuote().getText());
        startActivity(intent);
    }

}