package com.example.duan.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan.DAO.PhieumuonDAO;
import com.example.duan.R;
import com.example.duan.model.PhieuMuon;

import java.util.ArrayList;

public class PhieuMuonAdapter extends RecyclerView.Adapter<PhieuMuonAdapter.ViewHolder>{

    private ArrayList<PhieuMuon> list;
    private Context context;

    public PhieuMuonAdapter(ArrayList<PhieuMuon> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recycler_phieumuon,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvMaPM.setText("Mã PM:" + list.get(position).getMapm());
        holder.tvMatv.setText("Mã TV:" + list.get(position).getMatv());
        holder.tvTenTV.setText("Tên TV:" + list.get(position).getTentv());
        holder.tvMaTT.setText("Mã TT:" + list.get(position).getMatt());
        holder.tvTenTT.setText("Tên TT:" + list.get(position).getTentt());
        holder.tvMaSach.setText("Mã SACH:" + list.get(position).getMasach());
        holder.tvTenSach.setText("Tên SÁCH:" + list.get(position).getTensach());
        holder.tvNgay.setText("Ngày:" + list.get(position).getNgay());
        String trangthai ="";
        if(list.get(position).getTrasach()==1){
            trangthai = "Đã trả sách";
            holder.btnTraSach.setVisibility(View.GONE);
        }else {
            trangthai = "Chưa trả sách";
            holder.btnTraSach.setVisibility(View.VISIBLE);
        }
        holder.tvTrangThai.setText("Trạng Thái:" + trangthai);
        holder.tvTien.setText("Tiền:" + list.get(position).getTienthue());

        holder.btnTraSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhieumuonDAO phieumuonDAO = new PhieumuonDAO(context);
                boolean kiemtra = phieumuonDAO.thaydoiTrangThai(list.get(holder.getAdapterPosition()).getMapm());
                if(kiemtra){
                    list.clear();
                    list = phieumuonDAO.getDSPhieuMuon();
                    notifyDataSetChanged();
                }else {
                    Toast.makeText(context, "Thay đổi trạng thái không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvMaPM, tvMatv, tvTenTV, tvMaTT, tvTenTT, tvMaSach, tvTenSach, tvNgay, tvTrangThai, tvTien;
        Button btnTraSach;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            tvMaPM =itemView.findViewById(R.id.tvMaPM);
            tvMatv =itemView.findViewById(R.id.tvMaTV);
            tvTenTV =itemView.findViewById(R.id.tvTenTV);
            tvMaTT =itemView.findViewById(R.id.tvMaTT);
            tvTenTT =itemView.findViewById(R.id.tvTenTT);
            tvMaSach =itemView.findViewById(R.id.tvMaSach);
            tvTenSach =itemView.findViewById(R.id.tvTenSach);
            tvNgay =itemView.findViewById(R.id.tvNgay);
            tvTrangThai =itemView.findViewById(R.id.tvTrangThai);
            tvTien =itemView.findViewById(R.id.tvTien);
            btnTraSach =itemView.findViewById(R.id.btnTraSach);
        }
    }
}
