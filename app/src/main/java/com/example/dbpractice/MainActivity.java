package com.example.dbpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button writeButton,readButton,updateButton,removeButton;
    private EditText nameInput,emailInput,numberInput;

    private ScrollView display;
    private TextView result;
    private DBUtil db;
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
        result=findViewById(R.id.result_text);


        //DBUtil dbHelper = new DBUtil(getContext());
        Log.i("SQL","Check");
        writeButton.setOnClickListener(this);
        readButton.setOnClickListener(this);
        updateButton.setOnClickListener(this);
        removeButton.setOnClickListener(this);

        //numberInput.addTextChangedListener(this);

        db = new DBUtil(MainActivity.this);
        result.setText("VALUE");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Read_Button:
                Log.i("SQL","read clicked");
                result.setText(db.read());
                break;
            case R.id.Write_Button:
                if(nameInput.getText().toString()=="" || emailInput.getText().toString()=="" || numberInput.getText().toString()==""){
                    Log.i("SQL","tired insert blank info");
                }
                else{
                    db.insert(nameInput.getText().toString(),emailInput.getText().toString(),numberInput.getText().toString());
                    Log.i("SQL","data inserted");
                }
                break;
            case R.id.Update_Button:
                db.update(nameInput.getText().toString(),emailInput.getText().toString(),numberInput.getText().toString());
                Log.i("SQL","Update clicked");
                break;
            case R.id.Remove_Button:
                db.delete(nameInput.getText().toString());
                break;
        }
    }




}
