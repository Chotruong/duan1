package com.example.duan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duan.DAO.ThuThuDAO;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText edUser = findViewById(R.id.edUser);
        EditText edPass = findViewById(R.id.edPass);
        Button btnLogin = findViewById(R.id.btnLogin);

        ThuThuDAO thuThuDAO = new ThuThuDAO(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = edUser.getText().toString();
                String pass = edPass.getText().toString();
                if(thuThuDAO.checkLogin(user, pass)){
                    SharedPreferences sharedPreferences = getSharedPreferences("Thông tin", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("matt", user);
                    editor.commit();

                    startActivity(new Intent(Login.this,MainActivity.class));
                }else {
                    Toast.makeText(Login.this, "Tài Khoản hoặc Mật Khẩu không đúng", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}