package com.example.weathersunsine;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

public class DetailActivity extends AppCompatActivity {
    private static final String FORECAST_SHARE_HASHTAG = "#WeatherSunsine";

    private String mForecast;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_detail);
        TextView mWeatherDisplay = findViewById(R.id.tv_weather_display);
        Intent intentThatStartActivity = getIntent();
        if (intentThatStartActivity != null) {
            if (intentThatStartActivity.hasExtra(Intent.EXTRA_TEXT)) {
                mForecast = intentThatStartActivity.getStringExtra(Intent.EXTRA_TEXT);
                mWeatherDisplay.setText(mForecast);
            }
        }
    }
    /**
     * Uses the ShareCompat Intent builder to create our Forecast intent for sharing. We set the
     * type of content that we are sharing (just regular text), the text itself, and we return the
     * newly created Intent.
     *
     * @return The Intent to use to start our share.
     */
    private Intent createShareForecastIntent() {
        Intent shareIntent = ShareCompat.IntentBuilder.from(this)
                .setType("text/plain")
                .setText(mForecast + FORECAST_SHARE_HASHTAG)
                .getIntent();
        return shareIntent;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail, menu);
        MenuItem menuItem = menu.findItem(R.id.action_share);
        menuItem.setIntent(createShareForecastIntent());
        return true;
    }

}
