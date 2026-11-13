package com.example.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

public class PriceIndexAdapter extends RecyclerView.Adapter<PriceIndexAdapter.ViewHolder> {
    private final List<PriceItem> list;

    public PriceIndexAdapter(List<PriceItem> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_price_index, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PriceItem item = list.get(position);
        holder.tvMaterialName.setText(item.name);
        holder.tvCategory.setText(item.category);
        holder.tvPricePerKg.setText(String.format(Locale.getDefault(), "₱%.2f / kg", item.pricePerKg));
        holder.tvPointsPerKg.setText(String.format(Locale.getDefault(), "%d Pts / kg", item.pointsPerKg));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMaterialName, tvCategory, tvPricePerKg, tvPointsPerKg;

        ViewHolder(@NonNull View v) {
            super(v);
            tvMaterialName = v.findViewById(R.id.tvMaterialName);
            tvCategory = v.findViewById(R.id.tvCategory);
            tvPricePerKg = v.findViewById(R.id.tvPricePerKg);
            tvPointsPerKg = v.findViewById(R.id.tvPointsPerKg);
        }
    }
}