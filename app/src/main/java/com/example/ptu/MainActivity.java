package com.example.ptu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView MainText;
    String SetColor, SetPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainText = (TextView) findViewById(R.id.zz);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        if (!this.getClass().getSimpleName().equals("MainActivity")){
            menu.findItem(R.id.changeText).setVisible(false);
        }
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
            case R.id.calc:
                intent = new Intent(this, CalcSet.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                this.finish();
                break;
            case R.id.guess:
                intent = new Intent(this, GuessSet.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                this.finish();
                break;
            case R.id.elements:
                intent = new Intent(this, ElemMain.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                this.finish();
                break;
            case R.id.send_data:
                intent = new Intent(this, WorkData.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                this.finish();
                break;
            case R.id.fillIntent:
                intent = new Intent(this, FilterIntent.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                this.finish();
                break;
            case R.id.photoShot:
                intent = new Intent(this, PhotoShot.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                this.finish();
                break;
            case R.id.changeText:
                intent = new Intent(this, ChangeText.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.todoList:
                intent = new Intent(this, MainTodoList.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                this.finish();
                break;
            case R.id.testStatus:
                intent = new Intent(this, TestStatus.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                this.finish();
                break;
            case R.id.exit:
                finish();
                break;
            default:
                super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            SetColor = "BLACK";
            SetPos = "center";
        } else {
            SetColor = data.getStringExtra("color");
            SetPos = data.getStringExtra("pos");
        }
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        switch (SetPos) {
            case "left":
                MainText.setGravity(Gravity.LEFT);
                break;
            case "top":
                params.gravity = Gravity.TOP;
                MainText.setLayoutParams(params);
                break;
            case "right":
                MainText.setGravity(Gravity.RIGHT);
                break;
            case "bottom":
                params.gravity = Gravity.BOTTOM;
                MainText.setLayoutParams(params);
                break;
            case "center":
                MainText.setGravity(Gravity.CENTER);
                params.gravity = Gravity.CENTER;
                MainText.setLayoutParams(params);
                break;
        }
        MainText.setTextColor(Color.parseColor(SetColor));
    }

}

