package com.example.ptu;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class ChangeText extends MainActivity {

    RadioGroup ChangeColor, ChangePos;
    Intent intent = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_text);

        ChangeColor = findViewById(R.id.changeColor);
        ChangePos = findViewById(R.id.changePos);
    }

    public void closeWindow(View v) {
        int ChangeColorId = ChangeColor.getCheckedRadioButtonId();
        int ChangePosId = ChangePos.getCheckedRadioButtonId();
        switch (ChangePosId) {
            case R.id.changePosCenter:
                intent.putExtra("pos", "center");
                break;
            case R.id.changePosLeft:
                intent.putExtra("pos", "left");
                break;
            case R.id.changePosRight:
                intent.putExtra("pos", "right");
                break;
            case R.id.changePosTop:
                intent.putExtra("pos", "top");
                break;
            case R.id.changeBottom:
                intent.putExtra("pos", "bottom");
                break;
        }
        switch (ChangeColorId) {
            case R.id.changeColorBlack:
                intent.putExtra("color", "BLACK");
                break;
            case R.id.changeColorBlue:
                intent.putExtra("color", "BLUE");
                break;
            case R.id.changeColorRed:
                intent.putExtra("color", "RED");
                break;
        }
        setResult(RESULT_OK, intent);
        finish();
    }
}
