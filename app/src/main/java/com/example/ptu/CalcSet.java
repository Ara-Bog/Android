package com.example.ptu;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CalcSet extends MainActivity {
    EditText btnInput;
    TextView btnOutput;
    Boolean operation = false;
    Boolean btnInputEmpty = true;
    Boolean btnOutputEmpty = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);


        btnOutput = (TextView) findViewById(R.id.btnOutput);
        btnInput = (EditText) findViewById(R.id.btnInput);
    }

    public void contOutput(String operate) {
        String inputVal;
        if (!btnInputEmpty && !btnOutputEmpty && operation) {
            calc();
            inputVal = btnOutput.getText().toString();
        } else if (!btnInputEmpty && !btnOutputEmpty && !operation) {
            inputVal = btnInput.getText().toString();
        } else if (btnInputEmpty && !btnOutputEmpty) {
            inputVal = btnOutput.getText().toString().split(" ")[0];
        } else if (!btnInputEmpty && btnOutputEmpty){
            inputVal = btnInput.getText().toString();
        } else {
            return;
        }
        String numStr = inputVal + " " + operate;
        btnInput.setText(null);
        btnInputEmpty = true;
        btnOutput.setText(numStr.toCharArray(), 0, numStr.length());
        btnOutputEmpty = false;
        operation = true;
    }

    public void contInput(String val) {
        String inputVal = btnInput.getText().toString();
        String numStr = inputVal + val;
        btnInputEmpty = false;
        btnInput.setText(numStr.toCharArray(), 0, numStr.length());
    }

    public void clickNumber(View v) {
        Button numBtn = (Button)v;
        contInput((String) numBtn.getText());
    }

    public void calc(){
        String[] out = (btnOutput.getText().toString()).split(" ");
        Double outNum = Double.valueOf(out[0]);
        Double inNum = Double.valueOf(btnInput.getText().toString());
        Double result = null;
        switch (out[1]){
            case "+":
                result = outNum + inNum;
                break;
            case "-":
                result = outNum - inNum;
                break;
            case "*":
                result = outNum * inNum;
                break;
            case "/":
                result = outNum / inNum;
                break;
        }
        btnOutputEmpty = false;
        operation = false;
        btnOutput.setText(result.toString().toCharArray(), 0, result.toString().length());
    }

    public void clickOperation(View v) {
        Button operBtn = (Button)v;
        switch (v.getId()){
            case R.id.btnSub:
            case R.id.btnPlus:
            case R.id.btnDiv:
            case R.id.btnMult:
                contOutput((String) operBtn.getText());
                break;
            case R.id.btnPer:
                if (!btnInputEmpty) {
                    Double chgNum = Double.valueOf(btnInput.getText().toString());
                    chgNum = chgNum / 100;
                    btnInput.setText(chgNum.toString());
                }
                break;
            case R.id.btnChange:
                if (!btnInputEmpty) {
                    Double chgNum = Double.valueOf(btnInput.getText().toString());
                    chgNum = chgNum * -1;
                    btnInput.setText(chgNum.toString());
                }
                break;
            case R.id.btnClear:
                if (btnInputEmpty) {
                    btnOutputEmpty = true;
                    operation = false;
                    btnOutput.setText(null);
                } else {
                    btnInput.setText(null);
                    btnInputEmpty = true;
                }
                break;
            case R.id.btnSubmit:
                if (btnInputEmpty || !operation || btnOutputEmpty) {
                    return;
                }
                calc();
                btnInput.setText(null);
                btnInputEmpty = true;
                break;
            case R.id.btnDot:
                if (!btnInputEmpty) {
                    Double chgNum = Double.valueOf(btnInput.getText().toString());
                    btnInput.setText(chgNum.toString());
                }
        }
    }
}

