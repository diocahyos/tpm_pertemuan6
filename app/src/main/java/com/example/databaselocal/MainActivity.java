package com.example.databaselocal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.databaselocal.database.AppDatabase;
import com.example.databaselocal.database.DataDiri;
import com.example.databaselocal.database.DataDiriDAO;

public class MainActivity extends AppCompatActivity {

    private AppDatabase appDatabase;
    private EditText EtNama, EtAlamat, EtGender;
    private Button btnInsert, btnRead;
    private String nama,alamat;
    private char gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appDatabase = AppDatabase.initDb(this);

        EtAlamat = findViewById(R.id.EtAlamat);
        EtNama = findViewById(R.id.EtNama);
        EtGender = findViewById(R.id.EtGender);
        btnInsert = findViewById(R.id.btnInsert);
        btnRead = findViewById(R.id.btnRead);



        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nama = EtNama.getText().toString();
                alamat = EtAlamat.getText().toString();
                gender =  EtGender.getText().toString().charAt(0);
                insertData(nama,alamat,gender,appDatabase);
            }
        });

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ReadActivity.class);
                startActivity(intent);
            }
        });
    }

    private void insertData(String nama, String alamat, char gender, final AppDatabase appDatabase){

        DataDiri dataDiri = new DataDiri();
        dataDiri.setAlamat(alamat);
        dataDiri.setNama(nama);
        dataDiri.setGender(gender);
        appDatabase.dao().insertData(dataDiri);

        EtGender.setText("");
        EtAlamat.setText("");
        EtNama.setText("");

    }
}
