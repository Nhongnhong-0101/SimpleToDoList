package com.example.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TaskListAdapter extends BaseAdapter {
    Context context;
    ArrayList<Task> item;

    public TaskListAdapter(Context context, ArrayList<Task> item) {
        this.context = context;
        this.item = item;
    }

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public Object getItem(int i) {
        return item.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        if (v == null) {
            v = LayoutInflater.from(context).inflate(R.layout.list_item_task, viewGroup, false);
        }

        // anh xa du lieu
        Task task = item.get(i);
        if(task != null)
        {
            TextView tvName = v.findViewById(R.id.tvName);
            TextView tvDate = v.findViewById(R.id.tvDate);
            CheckBox done = v.findViewById(R.id.cbDone);

            tvName.setText(task.getName());
            tvDate.setText(task.getDate());

            if (task.isDone() == true)
            {
                done.setChecked(true);
            }
            else {
                done.setChecked(false);
            }

        }
        return v;

    }
}
