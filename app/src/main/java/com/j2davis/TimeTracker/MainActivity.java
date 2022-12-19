package com.j2davis.TimeTracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Task> tasks = new ArrayList<Task>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listTasks();
    }

    public void addTask(View view) {
        TextInputEditText txtAddTask = findViewById(R.id.txtInputAddTask);
        Task task = new Task(tasks.size()+1, txtAddTask.getText().toString());
        tasks.add(task);
        txtAddTask.setText(null);
        listTasks();
    }

    private void cleanTasks(TableLayout mainTbl){
        int children = mainTbl.getChildCount();
        if(children > 1){
            mainTbl.removeViews(1, children-1);
        }
    }
    public void processTaskButton(View btn){
        //get task id
        String task_id = btn.getTag().toString().substring(4);
        Log.i("JSD","processTaskButton: task_id = "+task_id);
        Task task = getTaskById(task_id);
        Button b = (Button) btn;
        Log.i("JSD", "Button Clicked: "+b.getText());
        if(b.getText().toString().equalsIgnoreCase("Start")){
            Log.i("JSD", "Button equals start");
            TaskTrackEvent tskTrackEvt = new TaskTrackEvent();
            tskTrackEvt.setStart(LocalDateTime.now());
            b.setText("Stop");
            task.addTrackEvent(tskTrackEvt);
            tasks.set(task.getId()-1, task);
            return;
        }
        if(b.getText().toString().equalsIgnoreCase("Stop")) {
            Log.i("JSD", "Button equals stop");
            ArrayList<TaskTrackEvent> tskTrackEvents = task.getTrackEvents();
            TaskTrackEvent tskTrackEvt = tskTrackEvents.get(tskTrackEvents.size()-1);
            tskTrackEvt.setEnd(LocalDateTime.now());
            tskTrackEvents.set(tskTrackEvents.size()-1, tskTrackEvt);
            task.setTrackEvents(tskTrackEvents);
            b.setText("Start");
            return;
        }
    }
    private Task getTaskById(String id){
        int i = Integer.valueOf(id);
        return tasks.get(i-1);
    }
    private void listTasks(){
        TableLayout mainTbl = findViewById(R.id.tblMain);
        TableRow firstRow = findViewById(R.id.frstRow);
        cleanTasks(mainTbl);
        for (int s = tasks.size()-1; s >=0; s--) {
            Task task = tasks.get(s);
            TableRow tskRow = new TableRow(this);

            TextView tskTxt = new TextView(this);
            Button tskStart = new Button(this);
            tskStart.setTag("btn_" + task.getId());
            tskStart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    processTaskButton(view);
                }
            });
            tskStart.setText("Start");

            tskTxt.setText(task.getName());
            tskRow.addView(tskTxt);
            tskRow.addView(tskStart);

            mainTbl.addView(tskRow);
        }
    }
}