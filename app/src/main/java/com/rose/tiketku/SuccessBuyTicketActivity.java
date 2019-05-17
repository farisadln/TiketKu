package com.rose.tiketku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SuccessBuyTicketActivity extends AppCompatActivity {

    Button btn_view_ticket,btn_my_dashboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_buy_ticket);

        btn_my_dashboard = findViewById(R.id.btn_my_dashboard);
        btn_view_ticket = findViewById(R.id.btn_view_ticket);

        btn_view_ticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goview = new Intent(SuccessBuyTicketActivity.this, MyTicket.class);
                startActivity(goview);
            }
        });

        btn_my_dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gohome = new Intent(SuccessBuyTicketActivity.this, HomeActivity.class);
                startActivity(gohome);
            }
        });

    }
}
