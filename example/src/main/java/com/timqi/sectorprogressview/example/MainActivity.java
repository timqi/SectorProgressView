package com.timqi.sectorprogressview.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;
import android.widget.TextView;

import com.timqi.sectorprogressview.SectorProgressView;

public class MainActivity extends AppCompatActivity {

    private SectorProgressView spv;
    private SeekBar sbPercent;
    private SeekBar sbStartAngle;
    private TextView tvTip;
    private int percent, startAngle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTip = (TextView) findViewById(R.id.tvTip);
        spv = (SectorProgressView) findViewById(R.id.spv);
        sbPercent = (SeekBar) findViewById(R.id.sbPercent);
        sbPercent.setMax(100);
        sbPercent.setProgress(25);
        percent = 25;
        sbStartAngle = (SeekBar) findViewById(R.id.sbStartAngle);
        sbStartAngle.setMax(360);
        sbStartAngle.setProgress(0);
        startAngle = 0;

        sbPercent.setOnSeekBarChangeListener(onSeekBarChangeListener);
        sbStartAngle.setOnSeekBarChangeListener(onSeekBarChangeListener);
        showTip();
    }

    private void showTip() {
        tvTip.setText("percent: " + percent + ";  startAngle: " + startAngle);
    }

    private SeekBar.OnSeekBarChangeListener onSeekBarChangeListener
            = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            switch (seekBar.getId()) {
                case R.id.sbPercent:
                    spv.setPercent(progress);
                    percent = progress;
                    break;
                case R.id.sbStartAngle:
                    spv.setStartAngle(progress);
                    startAngle = progress;
                    break;
            }
            showTip();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };
}
