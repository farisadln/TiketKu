package com.rose.tiketku.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rose.tiketku.Items.Ticket;
import com.rose.tiketku.MyTicket;
import com.rose.tiketku.R;

import java.util.ArrayList;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.MyViewHolder> {

    Context context;
    ArrayList<Ticket> tickets;

    public TicketAdapter(Context c, ArrayList<Ticket> p){
        context = c;
        tickets = p;
    }

    @NonNull
    @Override
    public TicketAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_myticket,viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TicketAdapter.MyViewHolder myViewHolder, int i) {

        myViewHolder.xnama_wisata.setText(tickets.get(i).getNama_wisata());
        myViewHolder.xlokasi.setText(tickets.get(i).getLokasi());
        myViewHolder.xjumlah_tiket.setText(tickets.get(i).getJumlah_tiket() + " Ticket");

        final String getNamaWisata = tickets.get(i).getNama_wisata();

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goticketdetail = new Intent(context, MyTicket.class);
                goticketdetail.putExtra("nama_wisata", getNamaWisata);
                context.startActivity(goticketdetail);
            }
        });


    }

    @Override
    public int getItemCount() {
        return tickets.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView xnama_wisata, xlokasi, xjumlah_tiket;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            xnama_wisata = itemView.findViewById(R.id.xnama_wisata);
            xlokasi = itemView.findViewById(R.id.xlokasi);
            xjumlah_tiket = itemView.findViewById(R.id.xjumlah_tiket);
        }
    }
}
