package com.example.dhktpm15a_nhom16;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterScreen extends AppCompatActivity {
    FirebaseAuth mAuth;
    EditText txtRegEmail,txtRegPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_screen);

        txtRegEmail = findViewById(R.id.txtRegEmail);
        txtRegPass = findViewById(R.id.txtRegPass);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.btnRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();
            }
        });

        findViewById(R.id.btnSignInNow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterScreen.this, SignInScreen.class);
                startActivity(intent);
            }
        });

    }
    private void createUser(){
        String email = txtRegEmail.getText().toString();
        String password = txtRegPass.getText().toString();

        if (TextUtils.isEmpty(email)){
            txtRegEmail.setError("Email không được để trống");
            txtRegEmail.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            txtRegPass.setError("Mật khẩu không được để trống");
            txtRegPass.requestFocus();
        }else{
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(RegisterScreen.this, "Đăng ký thành công",
                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterScreen.this, SignInScreen.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(RegisterScreen.this, "Đăng ký thất bại, lỗi : "+task.getException().getMessage(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}
