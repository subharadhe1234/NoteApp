package com.example.basic_practice;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basic_practice.helper.*;

import java.util.ArrayList;

public class MessageListActivity extends AppCompatActivity {

    //    TextView Title_text;
//    TextView Message_text;
    RecyclerView Recycle_view_messages;
    ArrayList<Messages> messagesArrayList;
    Message_Adapter messagesAdapter;
    DatabaseManegar databaseManegar;
    ImageButton add_new_message;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.massage_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
//        recycle view find view id and set layout manager
        Recycle_view_messages = findViewById(R.id.Recycle_view_message);
        Recycle_view_messages.setLayoutManager(new LinearLayoutManager(this));
//        messages array list object
        messagesArrayList = new ArrayList<>();
//        databaseManager object
        databaseManegar = new DatabaseManegar(this);
        cursor = databaseManegar.feachData();
//      add message button
        add_new_message = findViewById(R.id.add_new);
        add_new_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MessageListActivity.this, AddMessageActivity.class);
                startActivity(intent);
            }
        });



        /*
         *   this is fist element of the array list
         * add fist element of the cursor to array list
         * then interated all element of the cursor
         * */
        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(0);
                String title = cursor.getString(1);
                String body = cursor.getString(2);
//                add data to array list
                messagesArrayList.add(new Messages(id, title, body));
            } while (cursor.moveToNext());

        }

        /*
         * object of message adapter
         * and recycle view adapter set this message adapter
         * */
        messagesAdapter = new Message_Adapter(this, messagesArrayList);
        Recycle_view_messages.setAdapter(messagesAdapter);
    }

    public void refressData() {
        messagesAdapter = new Message_Adapter(this, messagesArrayList);
        Recycle_view_messages.setAdapter(messagesAdapter);
    }
}