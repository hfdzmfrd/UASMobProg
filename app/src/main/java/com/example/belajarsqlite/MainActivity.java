package com.example.belajarsqlite;

import androidx.annotation.ColorLong;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<MhsModel> mhsList;
    MhsModel mm;
    DbHelper db;
    boolean isEdit;
    //HAFIDZ MUFRODI G.211.20.0075
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText edNama = findViewById(R.id.edNama);
        EditText edNim = findViewById(R.id.edNim);
        EditText edNoHp = findViewById(R.id.edNoHp);

        Button btnSimpan = findViewById(R.id.btnSimpan);

        mhsList = new ArrayList<>();

        isEdit = false;
//HAFIDZ MUFRODI G.211.20.0075
        Intent intent_main = getIntent();
        if(intent_main.hasExtra("mhsData")){
            mm = intent_main.getExtras().getParcelable("mhsData");
            edNama.setText(mm.getNama());
            edNim.setText(mm.getNim());
            edNoHp.setText(mm.getNoHp());

            isEdit = true;

            btnSimpan.setBackgroundColor(Color.GREEN);
            btnSimpan.setText("Edit");
        }
//HAFIDZ MUFRODI
        db = new DbHelper(getApplicationContext());
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String isian_nama = edNama.getText().toString();
                String isian_nim = edNim.getText().toString();
                String isian_noHp = edNoHp.getText().toString();

                if (isian_nama.isEmpty() || isian_nim.isEmpty() || isian_noHp.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Isian masih kosong", Toast.LENGTH_SHORT).show();
                } else {
                    mhsList = db.listLimit(5);
                    if (mhsList.size() < 5) {
                        boolean stts;
                        if (!isEdit) {
                            mm = new MhsModel(-1, isian_nama, isian_nim, isian_noHp);
                            stts = db.simpan(mm);
                            edNama.setText("");
                            edNim.setText("");
                            edNoHp.setText("");
                        } else {
                            mm = new MhsModel(mm.getId(), isian_nama, isian_nim, isian_noHp);
                            stts = db.ubah(mm);
                        }
                        if (stts) {
                            Toast.makeText(getApplicationContext(), "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Data Gagal Disimpan", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Maksimal 5 data sudah tercapai", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
//HAFIDZ MUFRODI
        Button btnLihat = findViewById(R.id.btnLihat);
        btnLihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mhsList = db.listLimit(5);
                if (mhsList.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Belum ada data", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent_list = new Intent(MainActivity.this, ListMhsActivity.class);
                    intent_list.putParcelableArrayListExtra("mhsList", mhsList);
                    startActivity(intent_list);
                }
            }
        });
    }
}