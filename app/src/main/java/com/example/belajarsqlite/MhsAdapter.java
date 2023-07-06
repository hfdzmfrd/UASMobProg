package com.example.belajarsqlite;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MhsAdapter extends RecyclerView.Adapter<MhsAdapter.MhsVH> {

    private ArrayList<MhsModel> mhsList;
    private final OnItemClickListener listener;
    public MhsAdapter(ArrayList<MhsModel> mhsList, OnItemClickListener listener) {
        this.mhsList = mhsList;
        this.listener = listener;
    }
    //HAFIDZ MUFRODI
    @NonNull
    @Override
    public MhsVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.item_listmhs, parent, false);

        return new MhsVH(v);
    }
    @Override
    public void onBindViewHolder(@NonNull MhsVH holder, int position) {
        int nomorUrut = (position * 2) + 1; // Menghitung angka ganjil berdasarkan posisi

        holder.tvNomorUrut.setText(String.valueOf(nomorUrut));
        holder.tvNamaVal.setText(mhsList.get(position).getNama());
        holder.tvNimVal.setText(mhsList.get(position).getNim());
        holder.tvNoHpVal.setText(mhsList.get(position).getNoHp());

        holder.bind(mhsList, position, listener);
    }
//HAFIDZ MUFRODI G.211.20.0075

    public interface OnItemClickListener{
        void OnItemClick(ArrayList<MhsModel> mhsList, int position);
    }

    public void removeItem(int position){
        this.mhsList.remove(position);
        notifyDataSetChanged();
    }
    //HAFIDZ MUFRODI
    @Override
    public int getItemCount() {
        return mhsList.size();
    }

    public class MhsVH extends RecyclerView.ViewHolder {

        private TextView tvNamaVal, tvNimVal, tvNoHpVal;
        private CardView cvItem;

        private TextView tvNomorUrut;

        public MhsVH(@NonNull View itemView) {
            super(itemView);

            tvNamaVal = itemView.findViewById(R.id.tvNamaVal);
            tvNimVal = itemView.findViewById(R.id.tvNimVal);
            tvNoHpVal = itemView.findViewById(R.id.tvNoHpVal);
            tvNomorUrut = itemView.findViewById(R.id.tvNomorUrut);
            cvItem = itemView.findViewById(R.id.cvItem);
        }
        //HAFIDZ MUFRODI
        public void bind(ArrayList<MhsModel> mhsList, int position, OnItemClickListener listener){
            cvItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnItemClick(mhsList, position);
                    notifyDataSetChanged();
                }
            });
        }
    }
}
//HAFIDZ MUFRODI