package com.example.duan.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan.DAO.LoaiSachDAO;
import com.example.duan.DAO.SachDAO;
import com.example.duan.R;
import com.example.duan.adapter.SachAdapter;
import com.example.duan.model.LoaiSach;
import com.example.duan.model.Sach;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

public class QuanLySachFragment extends Fragment {
    SachDAO sachDAO;
    RecyclerView recyclerSach;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_qlsach, container, false);

        recyclerSach = view.findViewById(R.id.recyclerSach);
        FloatingActionButton floatAdd = view.findViewById(R.id.floatAdd);
        sachDAO = new SachDAO(getContext());
        loadData();
        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

       return view;
    }

    private void loadData(){
        ArrayList<Sach> list = sachDAO.getDSSach();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerSach.setLayoutManager(linearLayoutManager);
        SachAdapter adapter = new SachAdapter(getContext(),list, getDSLoaiSach(), sachDAO);
        recyclerSach.setAdapter(adapter);

    }

    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_themsach, null);
        builder.setView(view);

        EditText edTenSach = view.findViewById(R.id.edTenSach);
        EditText edTien = view.findViewById(R.id.edTien);
        Spinner spnLoaiSach = view.findViewById(R.id.spnLoaiSach);

        SimpleAdapter simpleAdapter = new SimpleAdapter(
                getContext(),
                getDSLoaiSach(),
                android.R.layout.simple_list_item_1,
                new String[]{"tenloai"},
                new int[]{android.R.id.text1}
        );
        spnLoaiSach.setAdapter(simpleAdapter);

        builder.setNegativeButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               String tensach = edTenSach.getText().toString();
               int tien = Integer.parseInt(edTien.getText().toString());
               HashMap<String, Object> hs = (HashMap<String, Object>) spnLoaiSach.getSelectedItem();
               int maloai = (int) hs.get("maloai");

               boolean check = sachDAO.themSachmoi(tensach, tien, maloai);
               if (check){
                   Toast.makeText(getContext(), "Thêm sách thành công", Toast.LENGTH_SHORT).show();
                   loadData();
               }else {
                   Toast.makeText(getContext(), "Thêm sách Thất Bại", Toast.LENGTH_SHORT).show();
               }
            }
        });
        builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private ArrayList<HashMap<String, Object>> getDSLoaiSach(){
        LoaiSachDAO loaiSachDAO = new LoaiSachDAO(getContext());
        ArrayList<LoaiSach> list = loaiSachDAO.getDSLoaiSach();
        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();

        for (LoaiSach loaiSach : list){
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("maloai", loaiSach.getId());
            hs.put("tenloai",loaiSach.getTenLoai());
            listHM.add(hs);
        }

        return listHM;
    }
}

