package com.timqi.sectorprogressview.example;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.SeekBar;
import android.widget.TextView;

import com.timqi.sectorprogressview.ColorfulRingProgressView;

/**
 * Created by qiqi on 15/11/3.
 */
public class ColorfulRingProgressViewAty extends AppCompatActivity {

    SeekBar sbPercent;
    SeekBar sbStartAngle;
    SeekBar sbStrokeWidth;
    TextView tvTip;
    ColorfulRingProgressView crpv;
    TextView tvPercent;

    AppCompatButton btnAnimate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colorful_ring_progress_view);
        getSupportActionBar().setTitle(getIntent().getStringExtra("TITLE"));

        crpv = (ColorfulRingProgressView) findViewById(R.id.crpv);
        tvTip = (TextView) findViewById(R.id.tvTip);
        sbPercent = (SeekBar) findViewById(R.id.sbPercent);
        sbPercent.setOnSeekBarChangeListener(onSeekBarChangeListener);
        sbStartAngle = (SeekBar) findViewById(R.id.sbStartAngle);
        sbStartAngle.setOnSeekBarChangeListener(onSeekBarChangeListener);
        sbStrokeWidth = (SeekBar) findViewById(R.id.sbStrokeWidth);
        sbStrokeWidth.setOnSeekBarChangeListener(onSeekBarChangeListener);

        tvPercent = (TextView) findViewById(R.id.tvPercent);

        crpv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator anim = ObjectAnimator.ofFloat(v, "percent",
                        0, ((ColorfulRingProgressView) v).getPercent());
                anim.setInterpolator(new LinearInterpolator());
                anim.setDuration(1000);
                anim.start();
            }
        });
        showTip();

        btnAnimate = (AppCompatButton) findViewById(R.id.animator);
        btnAnimate.setTag(false);
        btnAnimate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean animating = (boolean) view.getTag();
                if (animating) {
                    crpv.stopAnimateIndeterminate();
                    btnAnimate.setTag(false);
                    btnAnimate.setText("animateIndeterminate");
                } else {
                    crpv.animateIndeterminate();
                    btnAnimate.setTag(true);
                    btnAnimate.setText("stopAnimateIndeterminate");
                }
            }
        });
    }

    private SeekBar.OnSeekBarChangeListener onSeekBarChangeListener
            = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (seekBar.getId() == R.id.sbPercent) {
                crpv.setPercent(progress);
                tvPercent.setText(progress+"");
            } else if (seekBar.getId() == R.id.sbStartAngle) {
                crpv.setStartAngle(progress);
            } else if (seekBar.getId() == R.id.sbStrokeWidth) {
                crpv.setStrokeWidthDp(progress);
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

    private void showTip() {
        tvTip.setText("percent: " + sbPercent.getProgress()
                + ";  startAngle: " + sbStartAngle.getProgress()
                + ";  strokeWidth: " + sbStrokeWidth.getProgress() + "dp");
    }
}
