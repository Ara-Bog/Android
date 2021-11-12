package com.example.ptu;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Random;

import static java.lang.System.exit;

public class GuessSet extends MainActivity {
    Random r = new Random();
    TextView tvInfo, description;
    EditText etInput;
    Button bControl;
    boolean checkTry = false;
    boolean hardMod = false;
    int countTry, hiddenNum, selectNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guess);

        tvInfo = (TextView)findViewById(R.id.guessOutText);
        description = (TextView)findViewById(R.id.guessComplexityDescription);
        etInput = (EditText)findViewById(R.id.guessInput);
        bControl = (Button)findViewById(R.id.guessSubmit);
        generateNum();
        tvInfo.setText(getResources().getString(R.string.input_value));
    }

    public void guessNum(View v) {
        if (checkTry && countTry < 1) {
            return;
        }

        if (etInput.getText().length() < 1) {
            tvInfo.setText(getResources().getString(R.string.error));
            return;
        }

        selectNum = Integer.parseInt(etInput.getText().toString());
        if (selectNum > 100 || (hardMod && selectNum > 50)) {
            tvInfo.setText(getResources().getString(R.string.error));
        } else if (selectNum == hiddenNum) {
            tvInfo.setText(getResources().getString(R.string.hit));
        } else {
            if (hardMod) {
                if (r.nextBoolean()) {
                    tvInfo.setText(getResources().getString(R.string.ahead));
                } else {
                    tvInfo.setText(getResources().getString(R.string.behind));
                }
            } else {
                if (selectNum > hiddenNum) {
                    tvInfo.setText(getResources().getString(R.string.ahead));
                } else if (selectNum < hiddenNum) {
                    tvInfo.setText(getResources().getString(R.string.behind));
                }
            }

            if (checkTry) {
                countTry -=  1;
                if (selectNum != hiddenNum && countTry < 1) {
                    tvInfo.setText(getResources().getString(R.string.input_value_lose));
                }
            }
        }
    }

    public void generateNum() {
        if (hardMod) {
            hiddenNum = r.nextInt(51);
        } else {
            hiddenNum = r.nextInt(101);
        }
        if (hiddenNum == 0) {
            generateNum();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.guess_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.mainPage:
                intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                this.finish();
                break;
            case R.id.guessReset:
                generateNum();
                if (checkTry) {
                    if (hardMod) {
                        countTry = 4;
                    } else {
                        countTry = 5;
                    }
                }
                tvInfo.setText(getResources().getString(R.string.reset_complete));
                break;
            case R.id.guessEasyMod:
                description.setText(getResources().getString(R.string.complexity_easy_description));
                countTry = 10;
                checkTry = false;
                hardMod = false;
                generateNum();
                tvInfo.setText(getResources().getString(R.string.changeComplexity));
                break;
            case R.id.guessMediumMod:
                description.setText(getResources().getString(R.string.complexity_medium_description));
                countTry = 5;
                checkTry = true;
                hardMod = false;
                generateNum();
                tvInfo.setText(getResources().getString(R.string.changeComplexity));
                break;
            case R.id.guessHardMod:
                description.setText(getResources().getString(R.string.complexity_hard_description));
                countTry = 4;
                checkTry = true;
                hardMod = true;
                generateNum();
                tvInfo.setText(getResources().getString(R.string.changeComplexity));
                break;
            case R.id.exit:
                finish();
                break;
            default:
                super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }
}
