package com.iigo.signalloadingview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import com.iigo.library.SignalLoadingView;

public class MainActivity extends AppCompatActivity {
    private SignalLoadingView signalLoadingView1;
    private SignalLoadingView signalLoadingView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signalLoadingView1 = findViewById(R.id.slv_loading1);
        signalLoadingView2 = findViewById(R.id.slv_loading2);
    }

    public void onStart(View view) {
        signalLoadingView1.start();
        signalLoadingView2.start();
    }

    public void onStop(View view) {
        signalLoadingView1.stop();
        signalLoadingView2.stop();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        signalLoadingView1.setInterpolator(new LinearInterpolator());
        signalLoadingView1.setSignalColor(Color.RED);
        signalLoadingView1.setDuration(1500);

        ViewGroup.LayoutParams params = signalLoadingView1.getLayoutParams();
        params.width = 50;
        params.height = 50;
        signalLoadingView1.setLayoutParams(params);

        return super.onKeyDown(keyCode, event);
    }
}
