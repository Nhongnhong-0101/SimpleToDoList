package com.example.todolist;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_NEW_TASK = 1;

    ListView listView;
    ActivityResultLauncher<Intent> launcher;
    ActivityResultLauncher<Intent> launcherEdit;

    TaskListAdapter adapter;
    ArrayList<Task> listTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //khởi tạo task
        listTask = new ArrayList<>();
        listTask.add(new Task("Tên task1", new Date(), false));
        listTask.add(new Task("Tên task2", new Date(), false));



        listView = findViewById(R.id.lvTasks);
        registerForContextMenu(listView);


        //khởi tạo adapert

        adapter = new TaskListAdapter(MainActivity.this, listTask);
        listView.setAdapter(adapter);

        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == Activity.RESULT_OK){
                            Intent intent = result.getData();
                            Task temp = (Task) intent.getExtras().get("temp");
                            listTask.add(temp);
                            adapter.notifyDataSetChanged();

                        }
                    }
                });



    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.items_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_task, menu);
        return super.onCreateOptionsMenu(menu);
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
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//
//        if (requestCode == REQUEST_CODE_NEW_TASK && resultCode == RESULT_OK) {
//            // Lấy đối tượng Task từ Intent
//            String taskName = data.getStringExtra("name");
//            String taskDes = data.getStringExtra("des");
//            boolean isDone = data.getBooleanExtra("isDone", false);
//            long taskDateInMillis = data.getLongExtra("date", 0L);
//
//            Date taskDate = new Date(taskDateInMillis);
//            Task task = new Task(taskName, taskDate, isDone);
//
//            // Thêm task vào danh sách
//            listTask.add(task);
//
//            // Cập nhật lại ListView
//            adapter.notifyDataSetChanged();
//        } else {
//
//            // bạn không làm gì cả.
//        }
//    }

}