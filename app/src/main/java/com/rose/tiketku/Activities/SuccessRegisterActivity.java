package com.rose.tiketku.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.rose.tiketku.R;

public class SuccessRegisterActivity extends AppCompatActivity {


    Button btn_explore;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_register);



        btn_explore = findViewById(R.id.btn_explore);


        btn_explore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotohome = new Intent(SuccessRegisterActivity.this, HomeActivity.class);
                startActivity(gotohome);
            }
        });
    }
}
