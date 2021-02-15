package com.peter.petermusicplayer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.peter.petermusicplayer.databinding.ActivitySplashBinding;
import com.peter.petermusicplayer.view.MainActivity;

public class SplashActivity extends Activity {

    private ActivitySplashBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(new splashHandler(),2000);

    }

    private class splashHandler implements Runnable{
        @Override
        public void run() {
            Intent intent = new Intent(getApplication(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onBackPressed() {
    }
}
