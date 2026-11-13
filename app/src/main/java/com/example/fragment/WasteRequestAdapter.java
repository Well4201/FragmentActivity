package com.example.fragment;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

public class WasteRequestAdapter extends RecyclerView.Adapter<WasteRequestAdapter.ViewHolder> {
    private final List<WasteRequest> list;

    public WasteRequestAdapter(List<WasteRequest> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_waste_request, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WasteRequest req = list.get(position);
        holder.tvResidentName.setText(req.residentName);
        holder.tvLocation.setText(req.location);
        holder.tvTime.setText(req.timestamp);
        holder.tvWeight.setText(String.format(Locale.getDefault(), "Est. Weight: %.1f kg", req.weightKg));
        holder.tvPoints.setText(String.format(Locale.getDefault(), "+%d Eco-Points", req.pointsEarned));

        holder.tvWasteBadge.setText(req.wasteType);
        if (req.wasteType.toLowerCase().contains("nabubulok") && !req.wasteType.toLowerCase().contains("di")) {
            holder.tvWasteBadge.setBackgroundColor(Color.parseColor("#10B981"));
        } else if (req.wasteType.toLowerCase().contains("nakatatapon")) {
            holder.tvWasteBadge.setBackgroundColor(Color.parseColor("#F59E0B"));
        } else {
            holder.tvWasteBadge.setBackgroundColor(Color.parseColor("#2563EB"));
        }

        holder.tvStatusBadge.setText(req.status);
        if (req.status.equalsIgnoreCase("Completed")) {
            holder.tvStatusBadge.setTextColor(Color.parseColor("#10B981"));
        } else if (req.status.equalsIgnoreCase("Rider En Route")) {
            holder.tvStatusBadge.setTextColor(Color.parseColor("#2563EB"));
        } else {
            holder.tvStatusBadge.setTextColor(Color.parseColor("#F59E0B"));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvWasteBadge, tvStatusBadge, tvResidentName, tvLocation, tvTime, tvWeight, tvPoints;

        ViewHolder(@NonNull View v) {
            super(v);
            tvWasteBadge = v.findViewById(R.id.tvWasteBadge);
            tvStatusBadge = v.findViewById(R.id.tvStatusBadge);
            tvResidentName = v.findViewById(R.id.tvResidentName);
            tvLocation = v.findViewById(R.id.tvLocation);
            tvTime = v.findViewById(R.id.tvTime);
            tvWeight = v.findViewById(R.id.tvWeight);
            tvPoints = v.findViewById(R.id.tvPoints);
        }
    }
}