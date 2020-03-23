package com.example.databaselocal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.databaselocal.database.AppDatabase;
import com.example.databaselocal.database.DataDiri;

public class ReadActivity extends AppCompatActivity {

    private AppDatabase appDatabase;
    private RecyclerView rc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        appDatabase = AppDatabase.initDb(this);

        rc = findViewById(R.id.rv_maibread);
        rc.setHasFixedSize(true);
        read();
    }

    public void read(){
        // TODO 1 : Mengambil data diri database
        DataDiri[] list = appDatabase.dao().getData();


        // TODO 2 : Tampilin ke recylcerView
        DataDiriAdapter adapter = new DataDiriAdapter(getApplicationContext(),list, new DataDiriListener() {
            @Override
            public void onButtonDelete(DataDiri item) {
                appDatabase.dao().deleteData(item);
                Toast.makeText(getApplicationContext(),"Berhasil menghapus data!",Toast.LENGTH_SHORT).show();
                read();
            }
        });
        rc.setLayoutManager(new LinearLayoutManager(this));
        rc.setAdapter(adapter);
    }




}
