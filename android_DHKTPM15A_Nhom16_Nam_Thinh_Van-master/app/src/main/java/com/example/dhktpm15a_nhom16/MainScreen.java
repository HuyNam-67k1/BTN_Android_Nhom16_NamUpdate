package com.example.dhktpm15a_nhom16;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Iterator;

public class MainScreen extends AppCompatActivity {

    DataNhanVien dataNhanVien;
    ListView lvNhanVien;
    NhanVienAdapter adapter;
    ArrayList<NhanVien> NVList;
    ArrayList idList;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

        mAuth = FirebaseAuth.getInstance();

        dataNhanVien = new DataNhanVien(this,"nhanviendb.sqlite",null,1);

        lvNhanVien = findViewById(R.id.listview);

        NVList = new ArrayList();
        idList = new ArrayList();

        getNVList();

        adapter = new NhanVienAdapter(this, R.layout.nhanvien_listview,NVList);
        lvNhanVien.setAdapter(adapter);


        //nút + trong main screen
        findViewById(R.id.btnAdd).setOnClickListener(view -> {
            Intent intent = new Intent(MainScreen.this, AddScreen.class);
            startActivity(intent);
        });
        findViewById(R.id.btnSignOut).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Intent intent = new Intent(MainScreen.this, SignInScreen.class);
                startActivity(intent);
            }
        });

    }
    //Lấy nhân viên từ sqlite
    private ArrayList getNVList(){
        NVList.clear();
        for (Iterator iterator = dataNhanVien.getAll().iterator(); iterator.hasNext(); ) {
            NhanVien nhanVien = (NhanVien) iterator.next();
            NVList.add(nhanVien);
        }
        return NVList;
    }

    //Firebase
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null){
            Intent intent = new Intent(MainScreen.this, SignInScreen.class);
            startActivity(intent);
        }
    }
}

