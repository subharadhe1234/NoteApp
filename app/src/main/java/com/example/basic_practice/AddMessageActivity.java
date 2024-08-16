package com.example.basic_practice;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.basic_practice.helper.*;

import java.util.ArrayList;
import java.util.UUID;

public class AddMessageActivity extends AppCompatActivity {
    ImageButton back_button;
    ImageButton right_btn;
    EditText Title_edit_text;
    EditText Description_edit_text;
    ArrayList<Messages> messagesArrayList = new ArrayList<Messages>();
    DatabaseManegar databaseManegar;
    String id = null;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.second_space);


        back_button = (ImageButton) findViewById(R.id.btn_back);
        right_btn = (ImageButton) findViewById(R.id.right_btn);
        Title_edit_text = (EditText) findViewById(R.id.title_edit_text);
        Description_edit_text = (EditText) findViewById(R.id.edit_add_note);
        databaseManegar = new DatabaseManegar(this);
        textView=findViewById(R.id.note_title);

        Toolbar toolbar =findViewById(R.id.my_toolbar);
       setSupportActionBar(toolbar);

//        String data=getIntent().getStringExtra("ram");
//        Log.d("ram"," "+getIntent().getStringExtra("ram"));


//         findViewById(R.layout.alart_diolog_activity);


        String title = getIntent().getStringExtra("title");
        String description = getIntent().getStringExtra("description");
        if ((title != null && description != null)
        ) {
            textView.setText("Update Note");
            Title_edit_text.setText(title);
            Description_edit_text.setText(description);
            id = getIntent().getStringExtra("id");

        }
        Log.d("rammmm", " " + id);


        /*
         * back button click event
         * */
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBack_diolog();
            }
        });

        /*
         * this fuction is if user press enter key in title edit text then
         * go to the discription edit text and focus on it.
         * */
        //   Title_edit_text.setOnKeyListener();
        Title_edit_text.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    Description_edit_text.requestFocus();
                    return true;
                } else return false;
            }
        });

        /*
         * get text from  the edit text
         * */
        Editable Title_text = Title_edit_text.getText();
        Editable Description_text = Description_edit_text.getText();
//         Title_edit_text.getText()
//            Title_edit_text.get


        /*
         * onclick the right button
         * */

        right_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                 * create a a unique id for each message
                 * */
//                String id = UUID.randomUUID().toString();
//                Log.d("ram"," "+getIntent().getStringExtra("ram"));

                if ((!Title_text.toString().isEmpty() && !Description_text.toString().isEmpty())
                ) {
                    Messages messages = new Messages(id, Title_text.toString(),
                            Description_text.toString());
//                    data insert in the database
                    if (id == null) {
                        long data = databaseManegar.insertData(messages);
                    } else {
                        databaseManegar.updateData(messages);
                    }

//                    intent passing
                    Intent intent = new Intent(AddMessageActivity.this, MessageListActivity.class);
                    startActivity(intent);
                    finishAffinity();


                } else {
                    Toast.makeText(AddMessageActivity.this, "plaese write something !!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // =============================================================================================
    /*
     *
     * for prssed back button
     * */
    @Override
    public void onBackPressed() {
        setBack_diolog();
    }


    /*
     * for presrd user define back button
     * */
    public void setBack_diolog() {


        /*
         * this is the in build dialog box
         * */
        AlertDialog.Builder builder = new AlertDialog.Builder(AddMessageActivity.this);
        builder.setTitle("Alart Dialog!!");
        builder.setMessage("Do you want to exit");
        /*
         * if user click yes then finish the activity
         * */
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        /*
         * if user click no then dialog box is canceled.
         * */
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        /*
         * A null listener allows the button to dismiss the dialog and take no further action.
         * */
        builder.setCancelable(false);
        /*
         * create dialog box
         * */
        AlertDialog dialog = builder.create();
        dialog.show();


    }


 
}