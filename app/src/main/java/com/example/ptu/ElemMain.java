package com.example.ptu;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ElemMain extends MainActivity implements View.OnClickListener,
        AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    TextView OutText;
    Button ButChgTxt;
    EditText InputChg;
    ListView ListEl;
    ArrayAdapter ListElAdap;
    ArrayList ListElArr = new ArrayList();
    String AddedElList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.elemets);

        OutText = (TextView)findViewById(R.id.elementsOutText);
        ButChgTxt = findViewById(R.id.elementsButChg);
        InputChg = findViewById(R.id.elementsInputChg);
        ListEl = findViewById(R.id.elementsList);
        ListElAdap = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ListElArr);
        ListEl.setAdapter(ListElAdap);

        ListEl.setOnItemClickListener(this);
        ListEl.setOnItemLongClickListener(this);
        ButChgTxt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        AddedElList = InputChg.getText().toString();
        InputChg.setText("");
        if(ListElArr.indexOf(AddedElList) < 0) {
            ListElArr.add(AddedElList);
            ListElAdap.notifyDataSetChanged();
            Collections.sort(ListElArr);
        } else {
            OutText.setText("Такой элемент уже есть");
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int pos, long id) {
        OutText.setText("Зажмите объект чтобы удалить");
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View v, int pos, long id) {
        Toast.makeText(getApplicationContext(), "Объект " + ListElArr.get(pos) +
                " удален", Toast.LENGTH_LONG).show();
        ListElArr.remove(pos);
        ListElAdap.notifyDataSetChanged();
        return false;
    }

}
