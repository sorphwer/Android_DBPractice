package com.example.dbpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {

    private Button writeButton,readButton,updateButton,removeButton;
    private EditText nameInput,emailInput,numberInput;

    private ScrollView display;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        writeButton=findViewById(R.id.Write_Button);
        readButton=findViewById(R.id.Read_Button);
        updateButton=findViewById(R.id.Update_Button);
        removeButton=findViewById(R.id.Remove_Button);

        nameInput=findViewById(R.id.input_name);
        emailInput=findViewById(R.id.input_email);
        numberInput=findViewById(R.id.input_number);

        display=findViewById(R.id.scroll_result);


        //DBUtil dbHelper = new DBUtil(getContext());




    }
}
