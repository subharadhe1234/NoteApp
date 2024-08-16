package com.example.basic_practice;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.basic_practice.helper.*;

public class MainActivity extends AppCompatActivity {
    Button button;
    TextView text1;
    int[] display_size = new int[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        /*
         * button and text view declaration by using find view by id
         * */
        button = findViewById(R.id.radhe);
        text1 = findViewById(R.id.text1);

        /*
         * Utils class object creation
         * display_size array declaration
         * and get display size method calling
         *
         * */

        Utils utils = new Utils();

        display_size = utils.get_display_size(this);


        /*
         * calling getLayoutParams method to set button size
         * optput of params  is  android.widget.LinearLayout$LayoutParams@df724bd
         * b_hight and b_width are in percentage of display_size   display_size*(fraction of display_size)
         * set weight and height of button
         * params is set to button
         * */
        ViewGroup.LayoutParams params = button.getLayoutParams();
        float b_hight = 7; // b_hight in percentage
        float b_width = 60;   // b_width in percentage
        params.width = (int) (display_size[0] * (b_width / 100));
        params.height = (int) (display_size[1] * (b_hight / 100));
        button.setLayoutParams(params);
        Log.d("params", "params: " + params);


        /*
         * Update the the textView text by using setText method
         * */
        text1.setText("Hare Krishna ");


        /*
         * Set on click listener to button
         * and calling intent passing method
         * */
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "radhe krishna", Toast.LENGTH_SHORT).show();
                /*
                 * custom dylog box ceating
                 * */
                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.alart_diolog_activity, null);


                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setView(view);
                AlertDialog dialog = builder.create();
                dialog.show();

                Button yes = view.findViewById(R.id.yes);
                Button no = view.findViewById(R.id.no);
                TextView title = view.findViewById(R.id.title);
                TextView message = view.findViewById(R.id.message);
                title.setText("Conformation");
                message.setText("Do you want to continue");
                CardView cardView = view.findViewById(R.id.card_dialog);
                ViewGroup.LayoutParams params = cardView.getLayoutParams();
                params.width = (int) (display_size[0] * 0.85);
                params.height = (int) (display_size[1] * 0.25);

                cardView.setLayoutParams(params);

                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                      Intent intent=  utils.Intent_passing(MainActivity.this,
//                              AddMessageActivity.class,"ram","radhe");
//                      intent.putExtra("name","radhe");
                        Intent intent = new Intent(MainActivity.this, MessageListActivity.class);
                        intent.putExtra("ram", "radhe");
                        startActivity(intent);
                        dialog.dismiss();
                        finish();
                    }
                });

                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        utils.Intent_passing(MainActivity.this, AddMessageActivity.class);
                        dialog.dismiss();
                    }
                });




                /*
                 * from utils class we are calling intent passing method
                 *
                 * */


            }
        });


    }

}