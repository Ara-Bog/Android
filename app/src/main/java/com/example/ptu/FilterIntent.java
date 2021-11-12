package com.example.ptu;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class FilterIntent extends MainActivity {

    EditText InputURL;
    RadioGroup WhatNeeds;
    RadioButton IDNeeds;
    Intent GoToSite;
    String Link;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter_intent);

        InputURL = (EditText) findViewById(R.id.inputURL);
        WhatNeeds = (RadioGroup) findViewById(R.id.whatNeeds);
    }

    public void parseLink (View v) {
        IDNeeds = (RadioButton) findViewById(WhatNeeds.getCheckedRadioButtonId());
        switch (IDNeeds.getId()) {
            case R.id.needGoing:
                GoToSite = new Intent(Intent.ACTION_VIEW);
                Link = "http://";
                break;
            case R.id.needCall:
                GoToSite = new Intent(Intent.ACTION_DIAL);
                Link = "tel:";
                break;
        }

        GoToSite.setData(Uri.parse(Link + InputURL.getText().toString()));
        startActivity(GoToSite);
    }
}
