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

        ProgressBar mProgressBar = (ProgressBar) findViewById(R.id.progressStatus);
        TextView ResultBtn = (TextView) findViewById(R.id.progressSting);

        ObjectAnimator animator = ObjectAnimator.ofInt(mProgressBar, "progress", 0, 80);
        animator.setInterpolator(new BounceInterpolator());
        animator.setDuration(3000);
        animator.start();

        ResultBtn.setText(R.string.testResponseGoodResult);
    }
}
