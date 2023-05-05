package com.example.todolist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_NEW_TASK = 1;

    ListView listView;
    TaskListAdapter adapter;
    ArrayList<Task> listTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //khởi tạo task
        listTask = new ArrayList<>();
        listTask.add(new Task("Tên task", new Date(), false));
//        listTask.add(new Task("an com",04 02 2023, true));
//        listTask.add(new Task("tam meo","4/20/2023", false));
//        listTask.add(new Task("ngu","4/20/2023", false));
//        listTask.add(new Task("di hoc","4/20/2023", true));

        //
        listView = findViewById(R.id.lvTasks);
        adapter = new TaskListAdapter(this, listTask);
        //khởi tạo adapert
        listView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.items_menu, menu);
        return true;
        //return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.btnNew:
            {
                Intent intent = new Intent(MainActivity.this, NewTask.class );
                startActivity(intent);
                break;
            }

        }
        return super.onOptionsItemSelected(item);
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == REQUEST_CODE_NEW_TASK && resultCode == RESULT_OK) {
            // Lấy đối tượng Task từ Intent
            Intent intent = getIntent();
            String taskName = intent.getStringExtra("name");
            String taskDes = intent.getStringExtra("des");
            boolean isDone = intent.getBooleanExtra("isDone", false);
            long taskDateInMillis = intent.getLongExtra("date", 0L);

            Date taskDate = new Date(taskDateInMillis);
            Task task = new Task(taskName, taskDate, isDone);

            // Thêm task vào danh sách
            listTask.add(task);

            // Cập nhật lại ListView
            adapter.notifyDataSetChanged();
        } else {
            // Nếu kết quả trả về không phải là RESULT_OK,
            // tức là người dùng không hoàn thành tạo task,
            // bạn không làm gì cả.
        }
    }
}