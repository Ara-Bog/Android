package com.example.ptu;

import android.animation.ObjectAnimator;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class TestStatusResult extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_status_result);

        ProgressBar mProgressBar = findViewById(R.id.progressStatus);
        TextView ResultBtn = findViewById(R.id.progressSting);

        String requestTest = getIntent().getStringExtra("request");
        int progressVal;

        if (requestTest.contains("отсутствию переутомления")) {
            progressVal = 80;
        } else if (requestTest.contains("небольшому переутомлению")) {
            progressVal = 50;
        } else if (requestTest.contains("высокому уровню переутомления")) {
            progressVal = 20;
        } else {
            progressVal = 0;
        }
        ObjectAnimator animator = ObjectAnimator.ofInt(mProgressBar, "progress", 0, progressVal);
        animator.setInterpolator(new BounceInterpolator());
        animator.setDuration(3000);
        animator.start();

        ResultBtn.setText(requestTest);
    }
}
