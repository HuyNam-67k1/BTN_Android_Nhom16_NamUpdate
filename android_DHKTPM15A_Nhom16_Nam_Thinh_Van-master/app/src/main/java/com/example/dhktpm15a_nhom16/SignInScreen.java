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

public class SignInScreen extends AppCompatActivity {

    EditText txtSignInEmail,txtSignInPass;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_screen);

        mAuth = FirebaseAuth.getInstance();

        txtSignInEmail = findViewById(R.id.txtSignInEmail);
        txtSignInPass = findViewById(R.id.txtSignInPass);

        findViewById(R.id.btnRegisterNow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInScreen.this, RegisterScreen.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnSignIn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInUser();
            }
        });
    }
    private void signInUser(){
        String email = txtSignInEmail.getText().toString();
        String password = txtSignInPass.getText().toString();

        if (TextUtils.isEmpty(email)){
            txtSignInEmail.setError("Email không được để trống");
            txtSignInEmail.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            txtSignInPass.setError("Mật khẩu không được để trống");
            txtSignInPass.requestFocus();
        }else{
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(SignInScreen.this, "Đăng nhập thành công",
                                Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignInScreen.this, MainScreen.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(SignInScreen.this, "Đăng nhâp thất bại",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}