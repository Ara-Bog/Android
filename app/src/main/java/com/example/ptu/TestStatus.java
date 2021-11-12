package com.example.ptu;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Range;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;

import java.io.IOException;
import java.lang.reflect.Array;
import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.Collections;

public class TestStatus extends MainActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_status);
        MainText = (TextView) findViewById(R.id.zz);

        Spinner spinnerDay = (Spinner) findViewById(R.id.testBirthdayDay);
        Spinner spinnerMonth = (Spinner) findViewById(R.id.testBirthdayMonth);
        Spinner spinnerYear = (Spinner) findViewById(R.id.testBirthdayYear);
        Spinner spinnerSex = (Spinner) findViewById(R.id.testSex);
        String[] rangeMonths = new DateFormatSymbols().getMonths();
        String[] rangeYear = new String[80];
        int i,j;
        for (i=2021, j=0;j<80;i--, j++){
            rangeYear[j] = String.valueOf(i);
        }
        String[] rangeDay = new String[31];
        for (i=1;i<=31;i++){
            rangeDay[i-1] = String.valueOf(i);
        }
        ArrayAdapter<String> adapterDay  =  new  ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,rangeDay);
        ArrayAdapter<String> adapterMonth  =  new  ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, rangeMonths);
        ArrayAdapter<String> adapterYear  =  new  ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, rangeYear);
        ArrayAdapter<String> adapterSex  =  new  ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, new String[]{"М", "Ж"});
        adapterDay.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterMonth.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterYear.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDay.setAdapter(adapterDay);
        spinnerMonth.setAdapter(adapterMonth);
        spinnerYear.setAdapter(adapterYear);
        spinnerSex.setAdapter(adapterSex);
    }

    public void getResult(View v) {
        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.dialog_test_status, null);

        AlertDialog.Builder DialogAddedEl = new AlertDialog.Builder(this);

        DialogAddedEl.setView(promptsView);
        final EditText TestStatusInputVal = (EditText) promptsView.findViewById(R.id.testStatusInputVal);

        DialogAddedEl.setCancelable(false).setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    public void onClick(DialogInterface dialog, int id) {
                        if (TestStatusInputVal.getText().toString() == "") {
                            TestStatusInputVal.setText(0);
                        }
                        Intent intent = new Intent(TestStatus.this, TestStatusResult.class);
                        intent.putExtra("data", TestStatusInputVal.getText().toString());
                        startActivity(intent);
                    }
                }).setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = DialogAddedEl.create();

        alertDialog.show();
    }
}
