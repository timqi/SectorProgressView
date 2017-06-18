package com.timqi.sectorprogressview.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.timqi.sectorprogressview.SectorProgressView;

/**
 * Created by qiqi on 15/11/3.
 */
public class SectorProgressViewAty extends AppCompatActivity {
    private SectorProgressView spv;
    private SeekBar sbPercent;
    private SeekBar sbStartAngle;
    private TextView tvTip;

    AppCompatButton btnAnimate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sector_progress_view);
        getSupportActionBar().setTitle(getIntent().getStringExtra("TITLE"));
        tvTip = (TextView) findViewById(R.id.tvTip);
        spv = (SectorProgressView) findViewById(R.id.spv);

        sbPercent = (SeekBar) findViewById(R.id.sbPercent);
        sbStartAngle = (SeekBar) findViewById(R.id.sbStartAngle);
        sbPercent.setOnSeekBarChangeListener(onSeekBarChangeListener);
        sbStartAngle.setOnSeekBarChangeListener(onSeekBarChangeListener);
        showTip();

        btnAnimate = (AppCompatButton) findViewById(R.id.animator);
        btnAnimate.setTag(false);
        btnAnimate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean animating = (boolean) view.getTag();
                if (animating) {
                    spv.stopAnimateIndeterminate();
                    btnAnimate.setTag(false);
                    btnAnimate.setText("animateIndeterminate");
                } else {
                    spv.animateIndeterminate();
                    btnAnimate.setTag(true);
                    btnAnimate.setText("stopAnimateIndeterminate");
                }
            }
        });
    }

    private void showTip() {
        tvTip.setText("percent: " + sbPercent.getProgress() + ";  startAngle: " + sbStartAngle.getProgress());
    }

    private SeekBar.OnSeekBarChangeListener onSeekBarChangeListener
            = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            switch (seekBar.getId()) {
                case R.id.sbPercent:
                    spv.setPercent(progress);
                    break;
                case R.id.sbStartAngle:
                    spv.setStartAngle(progress);
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
