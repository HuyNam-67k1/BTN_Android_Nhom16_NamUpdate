package com.example.dhktpm15a_nhom16;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

public class UpdateScreen extends AppCompatActivity {

    DataNhanVien dataNhanVien;
    EditText txtUpdateName,txtUpdatePosition,txtUpdateAge,txtUpdateCountry;
    ArrayList<NhanVien> NVList;
    NhanVien nhanVienMoi;
    String name,position,country,age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_screen);

        dataNhanVien = new DataNhanVien(this,"nhanviendb.sqlite",null,1);

        txtUpdateName = findViewById(R.id.txtUpdateName);
        txtUpdatePosition = findViewById(R.id.txtUpdatePosition);
        txtUpdateAge = findViewById(R.id.txtUpdateAge);
        txtUpdateCountry = findViewById(R.id.txtUpdateCountry);

        NVList = new ArrayList<>();

        //nút <- trong main screen
        findViewById(R.id.btnBack2).setOnClickListener(view -> {
            Intent intent = new Intent(UpdateScreen.this, MainScreen.class);
            startActivity(intent);
        });

        Intent intent = getIntent();
        int id = intent.getIntExtra("id",-1);

        NhanVien nhanVien = dataNhanVien.getNVById(id);

        txtUpdateName.setText(nhanVien.getName());
        txtUpdatePosition.setText(nhanVien.getPosition());
        txtUpdateAge.setText(nhanVien.getAge()+"");
        txtUpdateCountry.setText(nhanVien.getCountry());

        findViewById(R.id.btnRemove).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataNhanVien.removeNhanVien(id);
                getNVList();
                Toast.makeText(UpdateScreen.this, "Xóa nhân viên thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UpdateScreen.this, MainScreen.class);
                startActivity(intent);
            }
        });



        findViewById(R.id.btnUpdate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = txtUpdateName.getText().toString();
                position = txtUpdatePosition.getText().toString();
                age = txtUpdateAge.getText().toString();
                country = txtUpdateCountry.getText().toString();

                if (TextUtils.isEmpty(name)){
                    txtUpdateName.setError("Họ tên không được để trống");
                    txtUpdateName.requestFocus();
                }else if (TextUtils.isEmpty(position)){
                    txtUpdatePosition.setError("Chức vụ không được để trống");
                    txtUpdatePosition.requestFocus();
                }else if (TextUtils.isEmpty(age)){
                    txtUpdateAge.setError("Tuổi không được để trống");
                    txtUpdateAge.requestFocus();
                }else if (TextUtils.isEmpty(country)){
                    txtUpdateCountry.setError("Quê quán không được để trống");
                    txtUpdateCountry.requestFocus();
                }
                else {

                nhanVienMoi = new NhanVien(id,name,position,Integer.parseInt(age),country);
                dataNhanVien.updateNhanVien(nhanVienMoi);
                //getNVList();
                Toast.makeText(UpdateScreen.this, "Cập nhật nhân viên thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UpdateScreen.this, MainScreen.class);
                startActivity(intent);
                }
            }
        });

    }
    private ArrayList getNVList(){
        NVList.clear();
        for (Iterator iterator = dataNhanVien.getAll().iterator(); iterator.hasNext(); ) {
            NhanVien nhanVien = (NhanVien) iterator.next();
            NVList.add(nhanVien);
        }
        return NVList;
    }
}