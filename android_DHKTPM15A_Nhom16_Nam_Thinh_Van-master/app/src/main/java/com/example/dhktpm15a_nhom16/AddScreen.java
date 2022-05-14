package com.example.dhktpm15a_nhom16;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddScreen extends AppCompatActivity {

    EditText txtAddName, txtAddPosition,txtAddAge, txtAddCountry;
    String name,position,country,age;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_screen);

        db = FirebaseFirestore.getInstance();

        //nút <- trong main screen
        findViewById(R.id.btnBack1).setOnClickListener(view -> {
            Intent intent = new Intent(AddScreen.this, MainScreen.class);
            startActivity(intent);
        });

        //nút thêm nhân viên
        findViewById(R.id.btnAddNV).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtAddName = findViewById(R.id.txtAddName);
                txtAddPosition = findViewById(R.id.txtAddPosition);
                txtAddAge = findViewById(R.id.txtAddAge);
                txtAddCountry = findViewById(R.id.txtAddCountry);

                name = txtAddName.getText().toString();
                position = txtAddPosition.getText().toString();
                age = txtAddAge.getText().toString();
                country = txtAddCountry.getText().toString();

                if (TextUtils.isEmpty(name)){
                    txtAddName.setError("Họ tên không được để trống");
                    txtAddName.requestFocus();
                }else if (TextUtils.isEmpty(position)){
                    txtAddPosition.setError("Chức vụ không được để trống");
                    txtAddPosition.requestFocus();
                }else if (TextUtils.isEmpty(age)){
                    txtAddAge.setError("Tuổi không được để trống");
                    txtAddAge.requestFocus();
                }else if (TextUtils.isEmpty(country)){
                    txtAddCountry.setError("Quê quán không được để trống");
                    txtAddCountry.requestFocus();
                }
                else {
                    Map<String, Object> nhanVien = new HashMap<>();
                    nhanVien.put("name", name);
                    nhanVien.put("position", position);
                    nhanVien.put("age", age);
                    nhanVien.put("country", country);

                    db.collection("NhanVien")
                            .add(nhanVien)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Toast.makeText(AddScreen.this, "Thêm nhân viên thành công", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(AddScreen.this, MainScreen.class);
                                    startActivity(intent);
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(AddScreen.this, "Thêm nhân viên thất bại! Lỗi : "+e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });

    }
}
