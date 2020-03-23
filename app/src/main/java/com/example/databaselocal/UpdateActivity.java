package com.example.databaselocal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.databaselocal.database.AppDatabase;
import com.example.databaselocal.database.DataDiri;

public class UpdateActivity extends AppCompatActivity {

    private TextView EtName, EtAlamat, EtGender;
    private Button btnUpdate;
    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        EtName = findViewById(R.id.Et_Nama);
        EtAlamat = findViewById(R.id.Et_Alamat);
        EtGender = findViewById(R.id.Et_Gender);
        btnUpdate = findViewById(R.id.btn_Update);

        appDatabase = AppDatabase.initDb(this);

        final int id = getIntent().getIntExtra("id",0);
        String nama = getIntent().getStringExtra("nama");
        String alamat = getIntent().getStringExtra("alamat");
        char gender = getIntent().getCharExtra("gender",'N');

        EtName.setText(nama);
        EtAlamat.setText(alamat);
        EtGender.setText(new char[]{gender},0,1);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateData(id);
            }
        });
    }

    private void UpdateData(int id){
        String newname = EtName.getText().toString();
        String newaddress = EtAlamat.getText().toString();
        char newgender = EtGender.getText().charAt(0);

        DataDiri dataDiri = new DataDiri();
        dataDiri.setId(id);
        dataDiri.setNama(newname);
        dataDiri.setAlamat(newaddress);
        dataDiri.setGender(newgender);
        appDatabase.dao().updateData(dataDiri);
        Toast.makeText(getApplicationContext(),"Berhasil mengupdate data",Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getApplicationContext(),ReadActivity.class);
        startActivity(intent);
    }
}
