package com.example.todolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    TaskListAdapter adapter;
    ArrayList<Task> listTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //khởi tạo task
        listTask = new ArrayList<>();
        listTask.add(new Task("an com","4/20/2023", true));
        listTask.add(new Task("tam meo","4/20/2023", false));
        listTask.add(new Task("ngu","4/20/2023", false));
        listTask.add(new Task("di hoc","4/20/2023", true));

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


            }

        }
        return super.onOptionsItemSelected(item);
    }
}