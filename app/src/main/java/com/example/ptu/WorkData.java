package com.example.ptu;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class WorkData extends MainActivity {

    EditText InputDataName, InputDataCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_data);

        InputDataName = (EditText) findViewById(R.id.inputData_name);
        InputDataCount = (EditText) findViewById(R.id.inputData_count);
    }

    public void sendData(View v) {
        Intent outTarget = new Intent(this, OutputData.class);

        outTarget.putExtra("name", InputDataName.getText().toString());
        outTarget.putExtra("count", InputDataCount.getText().toString());

        startActivity(outTarget);
    }
}
