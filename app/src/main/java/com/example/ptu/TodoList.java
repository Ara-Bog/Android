package com.example.ptu;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class TodoList extends AppCompatActivity {

    String SelectBase;
    LinearLayout.LayoutParams paramsBlock = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    LinearLayout.LayoutParams paramsWrap = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    LinearLayout.LayoutParams paramsText = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    LinearLayout.LayoutParams paramsbtnDel = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_list);

        SelectBase = getIntent().getStringExtra("base");
        this.setTitle(getIntent().getStringExtra("title"));
        todoListWriteBase();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void todoListWriteBase() {
        try {
            File OpenedFile = new File(getFilesDir(), SelectBase + ".txt");
            FileInputStream FileWorkBase = new FileInputStream(OpenedFile);

            BufferedReader input = new BufferedReader(new InputStreamReader(FileWorkBase));
            String line = "";
            int CountLines = 0;
            while ((line = input.readLine()) != null) {
                CountLines = CountLines + 1;
                String[] row = line.split(";");
                row[1] = row[1].replace("/n", "\n");
                todoListAddedEl(row[0], row[1]);
            }
            FileWorkBase.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void todoListChangingBase(String LabelEl, String TextEl, boolean delEl) throws IOException {
        String TextToToast = "";
        if (delEl) {
            File OpenedFile = new File(getFilesDir(), SelectBase + ".txt");
            File CacheFile = new File(getFilesDir(), SelectBase + "Cache" + ".txt");

            BufferedReader FileWorkBase = new BufferedReader(new FileReader(OpenedFile));
            PrintWriter pw = new PrintWriter(new FileWriter(CacheFile));

            String line = null;
            String SearchString = LabelEl + ";" + TextEl;
            while ((line = FileWorkBase.readLine()) != null) {
                if (!line.contains(SearchString)) {
                    pw.println(line);
                    pw.flush();
                }
            }
            pw.close();
            FileWorkBase.close();
            OpenedFile.delete();
            CacheFile.renameTo(OpenedFile);
            TextToToast = "Запись удалена!";
        } else {
            File OpenedFile = new File(getFilesDir(), SelectBase + ".txt");
            FileOutputStream FileWorkBase = new FileOutputStream(OpenedFile, true);
            TextEl = TextEl.replace("\n", "/n");
            String addRow = LabelEl + ";" + TextEl + "\n";
            FileWorkBase.write(addRow.getBytes());
            FileWorkBase.close();
            TextToToast = "Новая запись добавленна!";
        }
        Toast toast = Toast.makeText(getApplicationContext(),
                TextToToast, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void todoListAdded(View view) {
        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.dialog_todo_list, null);

        AlertDialog.Builder DialogAddedEl = new AlertDialog.Builder(this);

        DialogAddedEl.setView(promptsView);

        final EditText LabelInput = (EditText) promptsView.findViewById(R.id.todoListInputLabel);
        final EditText TextInput = (EditText) promptsView.findViewById(R.id.todoListInputText);

        DialogAddedEl.setCancelable(false).setPositiveButton("OK",
            new DialogInterface.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                public void onClick(DialogInterface dialog, int id) {
                    if (LabelInput.getText().toString() == "" || TextInput.getText().toString() == "") {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Разве можно ставить задачи без задач ?", Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        try {
                            todoListAddedEl(LabelInput.getText().toString(), TextInput.getText().toString());
                            todoListChangingBase(LabelInput.getText().toString(), TextInput.getText().toString(), false);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog,int id) {
                    dialog.cancel();
        }
        });
        AlertDialog alertDialog = DialogAddedEl.create();

        alertDialog.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void todoListAddedEl (String LabelEl, String TextEl) throws IOException {

        final LinearLayout MainWrap = (LinearLayout) findViewById(R.id.wrapTodoList);

        int paddingBlock = (int) (15 * MainWrap.getResources().getDisplayMetrics().density);
        int marginBlock = (int) (10 * MainWrap.getResources().getDisplayMetrics().density);

        LinearLayout BlockElTodoList = new LinearLayout(this, null, 0, R.style.ElTodoList_Block);
        LinearLayout WrapElTodoList = new LinearLayout(this, null, 0, R.style.ElTodoList_Wrap);
        TextView LabelElTodoList = new TextView(this, null, 0, R.style.ElTodoList_Label);
        TextView TextElTodoList = new TextView(this, null, 0, R.style.ElTodoList_Text);
        ImageButton ButtonDelTodoList = new ImageButton(this, null, 0, R.style.ElTodoList_btnDel);

        paramsBlock.setMargins(marginBlock, marginBlock, marginBlock, marginBlock);
        WrapElTodoList.setPadding(paddingBlock, paddingBlock, paddingBlock, paddingBlock);
        paramsWrap.width = (int) (330 * MainWrap.getResources().getDisplayMetrics().density);
        paramsText.topMargin = marginBlock;
        paramsbtnDel.width = (int) (40 * MainWrap.getResources().getDisplayMetrics().density);
        paramsbtnDel.height = (int) (60 * MainWrap.getResources().getDisplayMetrics().density);
        ButtonDelTodoList.setPadding(0, 0, paddingBlock, 0);

        BlockElTodoList.setLayoutParams(paramsBlock);
        WrapElTodoList.setLayoutParams(paramsWrap);
        TextElTodoList.setLayoutParams(paramsText);
        ButtonDelTodoList.setLayoutParams(paramsbtnDel);

        LabelElTodoList.setText(LabelEl);
        TextElTodoList.setText(TextEl);
        ButtonDelTodoList.setImageResource(R.drawable.ic_delete);
        ButtonDelTodoList.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                ViewGroup BlockEl = (ViewGroup) v.getParent();
                ViewGroup WrapEl = (ViewGroup) BlockEl.getChildAt(0);
                TextView LabelEl = (TextView) WrapEl.getChildAt(0);
                TextView TextEl = (TextView) WrapEl.getChildAt(1);
                String masData = (String) LabelEl.getText() + ";" + (String) TextEl.getText() + "\n";
                try {
                    todoListChangingBase((String) LabelEl.getText(), (String) TextEl.getText(), true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                MainWrap.removeViewAt(MainWrap.indexOfChild(BlockEl));
            }
        });

        WrapElTodoList.addView(LabelElTodoList);
        WrapElTodoList.addView(TextElTodoList);
        BlockElTodoList.addView(WrapElTodoList);
        BlockElTodoList.addView(ButtonDelTodoList);

        MainWrap.addView(BlockElTodoList);
    }
}
