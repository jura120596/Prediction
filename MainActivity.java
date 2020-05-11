package com.example.prediction;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.prediction.services.YandexService;

import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener,TextWatcher
{
    EditText input;
    TextView out;
    Button button;
    Retrofit retrofit;
    YandexService yandexService;
    private final String APIKEY = "pdct.1.1.20200511T160256Z.26a7bebd202266e5.df2158aaccf7710753040546af6ea1b8616ef454";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input = findViewById(R.id.input);
        out = findViewById(R.id.textView);
        button = findViewById(R.id.send);
        input.addTextChangedListener(this);
        button.setOnClickListener(this);
        retrofit = new Retrofit.Builder()
                .baseUrl("https://predictor.yandex.net")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        yandexService = retrofit.create(YandexService.class);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        final String text = input.getText().toString();
        if (!text.isEmpty()) {
            yandexService.prediction(APIKEY, "en", text, 10)
                    .enqueue(new Callback<YaResult>() {
                        @Override
                        public void onResponse(Call<YaResult> call, Response<YaResult> response) {
                            YaResult result = response.body();
                            int i = 1;
                            for (String item: result.text) {
                                out.append(i++ + ". " + item + "\n");
                            }
                        }
                        @Override
                        public void onFailure(Call<YaResult> call, Throwable t) {
                            t.printStackTrace();
                        }
                    });
        }
    }

    @Override
    public void onClick(View v) {
    }
}
