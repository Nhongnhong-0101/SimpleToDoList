package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewTask extends AppCompatActivity {
    private EditText edTitle;
    private EditText edDes;
    private EditText edDate;
    private CheckBox cbDone;
    private Button btSave;
    Task tempTask;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        edTitle = findViewById(R.id.et_Name);
        edDate = findViewById(R.id.et_Date);
        edDes = findViewById(R.id.et_Details);
        cbDone = findViewById(R.id.cb_IsDone);
        btSave = (Button) findViewById(R.id.btnSave);

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewTask.this, MainActivity.class);
                Bundle bundle = new Bundle();

                // Lấy chuỗi đầu vào từ EditText
                String dateString = edDate.getText().toString();
                Date date = null;

                // Tạo đối tượng SimpleDateFormat với định dạng mong muốn
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

                 // Sử dụng đối tượng SimpleDateFormat để phân tích chuỗi thành kiểu dữ liệu Date
                try {
                     date = format.parse(dateString);
                    // Ở đây bạn có thể sử dụng đối tượng Date để làm bất kỳ điều gì bạn muốn
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                tempTask = new Task(edTitle.getText().toString(), edDes.getText().toString(), date,cbDone.isChecked());

                bundle.putSerializable("newTask",  tempTask);
                //bundle.putInt("position", pos);
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finish();

            }
        });
    }
}