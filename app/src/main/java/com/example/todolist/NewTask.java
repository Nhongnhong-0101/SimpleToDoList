package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewTask extends AppCompatActivity {
    private EditText edTitle;
    private EditText edDes;
    private EditText edDate;
    private CheckBox cbDone;
    private Button btSave;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        edTitle = findViewById(R.id.etName);
        edDate = findViewById(R.id.etDate);
        edDes = findViewById(R.id.etDetails);
        cbDone = findViewById(R.id.cbIsDone);
        btSave = (Button) findViewById(R.id.btnSave);

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edTitle.getText().toString();
                String des = edDes.getText().toString();
                String stringDate = edDate.getText().toString();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date date = null;
                try {
                    date = dateFormat.parse(stringDate);
                    // Xử lý date ở đây
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                boolean isDone = cbDone.isChecked();

                Task newTask = new Task(name, date, isDone);

                Intent resultIntent = new Intent();
                resultIntent.putExtra("name", name);
                resultIntent.putExtra("des", des);
                resultIntent.putExtra("date", date.getTime());
                resultIntent.putExtra("isDone", isDone);
                setResult(Activity.RESULT_OK, resultIntent);


                finish();

            }
        });
    }
}