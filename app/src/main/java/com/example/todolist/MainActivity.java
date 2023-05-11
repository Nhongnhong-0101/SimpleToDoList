package com.example.todolist;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

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
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView  lv ;
    List<Task> tasks ;
    TaskListAdapter adapter;
    ActivityResultLauncher <Intent> fromNew;
    ActivityResultLauncher <Intent> fromEdit;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        tasks = new ArrayList<>();
        tasks.add(new Task("b", "bb", new Date(), false));
        //
        lv = findViewById(R.id.lvTasks);
        //
        adapter = new TaskListAdapter(this, R.layout.list_item_task, tasks);
        lv.setAdapter(adapter);
        registerForContextMenu(lv);

        fromNew = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK){
                            Intent intent = result.getData();
                            Task newTask = (Task) intent.getExtras().get("newTask");
                            tasks.add(newTask);
                            adapter.notifyDataSetChanged();
                        }
                    }
                }
        );
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch ( item.getItemId())
        {
            case R.id.btnUpdate:
                Intent intent = new Intent(MainActivity.this, NewTask.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("task", tasks.get(menuInfo.position));
                bundle.putInt("position", menuInfo.position);
                intent.putExtras(bundle);
                fromEdit.launch(intent);
                break;

            case R.id.btnDelete:
                tasks.remove(menuInfo.position);
                adapter.notifyDataSetChanged();
                break;
        }

        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menui, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.btnNew)
        {
            fromNew.launch(new Intent(MainActivity.this, NewTask.class));

        }
        return super.onOptionsItemSelected(item);
    }
}