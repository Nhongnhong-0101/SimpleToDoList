package com.example.todolist;

import android.app.Activity;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskListAdapter extends ArrayAdapter<Task> {
    private  int resource;
    private List<Task> tasks;


    public TaskListAdapter(@NonNull Context context, int resource, @NonNull List<Task> objects) {
        super(context, resource, objects);
        this.resource = resource;
        this.tasks = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v ==  null)
        {
            LayoutInflater vi;
            vi = LayoutInflater.from(this.getContext());
            v = vi.inflate(this.resource, null);

        }
        Task t = getItem(position);
        TextView tvTitle = v.findViewById(R.id.tvName);
        //TextView tvDes = v.findViewById(R.id.)
        TextView tvDate = v.findViewById(R.id.tvDateofTask);
        CheckBox cbDone = v.findViewById(R.id.cbDone);

        if (tvTitle!= null){
            tvTitle.setText(t.getName());
        }
        if (tvDate!= null){
            Date temp = t.getDate();
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            String dateString = format.format(temp);
            tvDate.setText(dateString);
        }
        if (cbDone!= null){
            cbDone.setChecked(t.isDone());
        }
        return v;
    }
}

