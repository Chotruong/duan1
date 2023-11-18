package com.example.duan;

import static com.example.duan.R.id.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.duan.DAO.SachDAO;

import com.example.duan.DAO.ThuThuDAO;
import com.example.duan.fragment.QLLoaiSachFragment;
import com.example.duan.fragment.QLPhieuMuonFragment;
import com.example.duan.fragment.QLThanhVienFragment;
import com.example.duan.fragment.QuanLySachFragment;
import com.example.duan.fragment.ThongKeFragment;
import com.example.duan.fragment.ThongKeTop10Fragment;
import com.google.android.material.navigation.NavigationView;
public class MainActivity extends AppCompatActivity {
    DrawerLayout drawer;


    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        FrameLayout frameLayout = findViewById(R.id.frameLayout);
        NavigationView navigationView = findViewById(navigation);
        drawer = findViewById(R.id.drawer);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()){
                    case R.id.mQLPM:
                        fragment = new QLPhieuMuonFragment();
                        break;
                    case R.id.mQLLS:
                        fragment = new QLLoaiSachFragment();
                        break;
                    case R.id.mExit:
                        Intent intent = new Intent(MainActivity.this, Login.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    case R.id.mDoimk:
                        showDialogDoimatkhau();
                    case R.id.mTop:
                        fragment = new ThongKeTop10Fragment();
                        break;
                    case R.id.mDoanhthu:
                        fragment = new ThongKeFragment();
                        break;
                    case R.id.mQLTV;
                        fragment = new QLThanhVienFragment();
                    case R.id.mQLSach;
                        fragment = new QuanLySachFragment();
                    default:
                        fragment = new QLPhieuMuonFragment();
                        break;
                }
                if (fragment != null){
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit();

                    toolbar.setTitle(item.getTitle());
                }

                drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            drawer.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
    private void showDialogDoimatkhau(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this).setNegativeButton("Cập Nhập",null).setPositiveButton("Hủy", null);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_doi_pass, null);
        EditText edOldPass = view.findViewById(R.id.edOldPass);
        EditText edNewpass = view.findViewById(R.id.edNewPass);
        EditText edRENewpass = view.findViewById(R.id.edRENewPass);

        builder.setView(view);



        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();

        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldPass = edOldPass.getText().toString();
                String newPass = edNewpass.getText().toString();
                String renewPass = edRENewpass.getText().toString();
                if (oldPass.equals("") || newPass.equals("") || renewPass.equals("")){
                    Toast.makeText(MainActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else {
                    if(newPass.equals((renewPass))){
                        SharedPreferences sharedPreferences = getSharedPreferences("THONGTIN", MODE_PRIVATE);
                        String matt = sharedPreferences.getString("matt","");
                        ThuThuDAO thuThuDAO = new ThuThuDAO(MainActivity.this);
                        int check = thuThuDAO.capNhapMatKhau(matt, oldPass, newPass);
                        if (check == 1){
                            Toast.makeText(MainActivity.this, "Nhập lại mật khẩu thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, Login.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        } else if (check == 0) {
                            Toast.makeText(MainActivity.this, "Mật khẩu cũ không đúng", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Cập nhập mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(MainActivity.this, "Không trùng với mật khẩu mới", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}

