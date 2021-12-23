package com.example.ptu;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.RequiresApi;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.List;

public class TestStatus extends MainActivity {
    Spinner spinnerDay, spinnerMonth, spinnerYear, spinnerSex;
    EditText startPulse, endPulse;
    String[] rangeYear, rangeDay;
    List<String> rangeMonths;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_status);

        spinnerDay = findViewById(R.id.testBirthdayDay);
        spinnerMonth = findViewById(R.id.testBirthdayMonth);
        spinnerYear = findViewById(R.id.testBirthdayYear);
        spinnerSex = findViewById(R.id.testSex);
        startPulse = findViewById(R.id.TestStartPulse);
        endPulse = findViewById(R.id.TestEndPulse);

        rangeMonths = Arrays.asList(new DateFormatSymbols().getMonths());
        rangeYear = new String[80];
        int i,j;
        for (i=2021, j=0;j<80;i--, j++){
            rangeYear[j] = String.valueOf(i);
        }
        rangeDay = new String[31];
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

    public String sendPost(String urlRequest) throws Exception {
        URL obj = new URL("http://abashin.ru/cgi-bin/ru/tests/burnout");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        con.setDoOutput(true);
        DataOutputStream setReq = new DataOutputStream(con.getOutputStream());
        setReq.writeBytes(urlRequest);
        setReq.flush();
        setReq.close();

        BufferedReader getReq = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = getReq.readLine()) != null) {
            response.append(inputLine);
        }
        String REresponse = String.valueOf(response);
        if (REresponse.contains("Error")) {
            REresponse = "Error";
        } else {
            REresponse = REresponse.substring(REresponse.indexOf("<body>") + 6, REresponse.indexOf("</body>"));
        }
        getReq.close();
        return REresponse;
    }

    public void getResult(View v) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String daySelect = (String) spinnerDay.getSelectedItem();
                String monSelect = String.valueOf(rangeMonths.indexOf(spinnerMonth.getSelectedItem()) + 1);
                String yearSelect = (String) spinnerYear.getSelectedItem();
                String sexSelect = (String) spinnerSex.getSelectedItem();
                String startPulseSelect = startPulse.getText().toString();
                String endPulseSelect = endPulse.getText().toString();

                if (sexSelect.equals("М")) {
                    sexSelect = "1";
                } else {
                    sexSelect = "2";
                }

                String urlRequest = "day="+daySelect+"&month="+monSelect+"&year="+yearSelect+
                        "&sex="+sexSelect+"&m1="+startPulseSelect+"&m2="+endPulseSelect;
                String response = null;
                try {
                    response = String.valueOf(sendPost(urlRequest));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent("com.ptu.test.action.results");
                intent.putExtra("request", response);
                startActivity(intent);
            }
        });
        thread.start();
    }
}
