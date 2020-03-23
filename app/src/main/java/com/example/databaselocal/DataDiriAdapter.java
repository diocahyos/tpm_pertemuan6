package com.example.databaselocal;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.databaselocal.database.DataDiri;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DataDiriAdapter extends RecyclerView.Adapter<DataDiriAdapter.ViewHolder> {
    private DataDiri[] list;
    private Context context;
    private DataDiriListener listener;


    public DataDiriAdapter(Context context, DataDiri[] list, DataDiriListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    // Menentukan mau memakai template item yang mana
    public DataDiriAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data_diri, parent, false);
        return new ViewHolder(itemRow);
    }

    @Override
    // Proses untuk memasukkan ke list view/tampilan
    public void onBindViewHolder(@NonNull DataDiriAdapter.ViewHolder holder, final int position) {
        final DataDiri item = list[position];

        holder.tvName.setText(item.getNama());
        holder.tvAlamat.setText(item.getAlamat());
        holder.tvGender.setText("" + item.getGender());

        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_update = new Intent(context,UpdateActivity.class);
                intent_update.putExtra("nama",item.getNama());
                intent_update.putExtra("alamat",item.getAlamat());
                intent_update.putExtra("gender",item.getGender());
                intent_update.putExtra("id",item.getId());
                intent_update.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent_update);
            }
        });

        holder.btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onButtonDelete(item);
            }
        });
    }

    @Override
//     Untuk Menghitung Data
    public int getItemCount() {
        return list.length;
    }

    // Untuk mengambil id pada tampilan
    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName, tvAlamat, tvGender;
        private Button btnHapus, btnUpdate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvAlamat = itemView.findViewById(R.id.item_tv_alamat);
            tvName = itemView.findViewById(R.id.item_tv_name);
            tvGender = itemView.findViewById(R.id.item_tv_gender);
            btnHapus = itemView.findViewById(R.id.btnHapus);
            btnUpdate = itemView.findViewById(R.id.btnUpdate);

        }
    }
}
