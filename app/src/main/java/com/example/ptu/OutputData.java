package com.example.ptu;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class OutputData extends MainActivity {

    TextView OutputDataName, OutputDataCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.output_data);

        OutputDataName = (TextView) findViewById(R.id.outputData_name);
        OutputDataCount = (TextView) findViewById(R.id.outputData_count);

        String txtName = getIntent().getStringExtra("name");
        String txtCount = getIntent().getStringExtra("count");

        OutputDataName.setText("Вы заказали - " + txtName);
        OutputDataCount.setText("В количестве - " + txtCount);
    }
}
