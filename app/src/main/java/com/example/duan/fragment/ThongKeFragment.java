package com.example.duan.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.duan.DAO.ThongkeDAO;
import com.example.duan.R;

import java.util.Calendar;

public class ThongKeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thongke, container, false);

        EditText edStart = view.findViewById(R.id.edStart);
        EditText edEnd = view.findViewById(R.id.edEnd);
        Button btnThongke = view.findViewById(R.id.btnThongke);
        TextView ivKetqua = view.findViewById(R.id.ivKetqua);

        Calendar calendar = Calendar.getInstance();

        edStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                String ngay = "";
                                String thang = "";
                                if (i2 < 10){
                                    ngay = "0"+i2;
                                }else {
                                    ngay = String.valueOf(i2);
                                }
                                if ((i1 + 1) < 10){
                                    thang = "0" + (i1 +1);
                                }else {
                                    thang = String.valueOf((i1 + 1));
                                }
                                edStart.setText(i+ "/" + thang + "/" + ngay);
                            }
                        },
                        calendar.get(calendar.YEAR),
                        calendar.get(calendar.MONTH),
                        calendar.get(calendar.DAY_OF_MONTH)
                );
                datePickerDialog.show();
            }
        });
    edEnd.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    getContext(),
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                            String ngay = "";
                            String thang = "";
                            if (i2 < 10){
                                ngay = "0"+i2;
                            }else {
                                ngay = String.valueOf(i2);
                            }
                            if ((i1 + 1) < 10){
                                thang = "0" + (i1 +1);
                            }else {
                                thang = String.valueOf((i1 + 1));
                            }
                            edEnd.setText(i+ "/" + thang + "/" + ngay);
                        }
                    },
                    calendar.get(calendar.YEAR),
                    calendar.get(calendar.MONTH),
                    calendar.get(calendar.DAY_OF_MONTH)
            );
            datePickerDialog.show();
        }
    });
    btnThongke.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ThongkeDAO thongkeDAO = new ThongkeDAO(getContext());
           String ngaybatdau = edStart.getText().toString();
           String ngayketthuc = edEnd.getText().toString();
            int doanhthu = thongkeDAO.getDoanhThu(ngaybatdau, ngayketthuc);
            ivKetqua.setText(doanhthu + "VND");
        }
    });
        return view;
    }
}
