package com.example.fragment;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

public class RiderJobAdapter extends RecyclerView.Adapter<RiderJobAdapter.ViewHolder> {
    private final List<WasteRequest> list;

    public RiderJobAdapter(List<WasteRequest> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rider_job, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WasteRequest req = list.get(position);
        holder.tvReqId.setText(req.id);
        holder.tvResidentName.setText(req.residentName);
        holder.tvLocation.setText(req.location);
        holder.tvWeight.setText(String.format(Locale.getDefault(), "%.1f kg", req.weightKg));
        holder.tvWasteType.setText(req.wasteType);
        holder.tvJobStatus.setText(req.status);

        if (req.status.equalsIgnoreCase("Completed")) {
            holder.btnAcceptJob.setVisibility(View.GONE);
            holder.btnCompleteJob.setVisibility(View.GONE);
            holder.tvJobStatus.setTextColor(Color.parseColor("#10B981"));
        } else if (req.status.equalsIgnoreCase("Rider En Route")) {
            holder.btnAcceptJob.setVisibility(View.GONE);
            holder.btnCompleteJob.setVisibility(View.VISIBLE);
            holder.tvJobStatus.setTextColor(Color.parseColor("#2563EB"));
        } else {
            holder.btnAcceptJob.setVisibility(View.VISIBLE);
            holder.btnCompleteJob.setVisibility(View.GONE);
            holder.tvJobStatus.setTextColor(Color.parseColor("#F59E0B"));
        }

        holder.btnAcceptJob.setOnClickListener(v -> {
            BasuraRepository.getInstance().updateRequestStatus(req.id, "Rider En Route", 0);
            Toast.makeText(v.getContext(), "Pickup accepted! Route navigation started.", Toast.LENGTH_SHORT).show();
        });

        holder.btnCompleteJob.setOnClickListener(v -> {
            BasuraRepository.getInstance().updateRequestStatus(req.id, "Completed", req.pointsEarned);
            Toast.makeText(v.getContext(), "Job completed! Issued " + req.pointsEarned + " Eco-Points to resident.", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvReqId, tvJobStatus, tvResidentName, tvLocation, tvWasteType, tvWeight;
        Button btnAcceptJob, btnCompleteJob;

        ViewHolder(@NonNull View v) {
            super(v);
            tvReqId = v.findViewById(R.id.tvReqId);
            tvJobStatus = v.findViewById(R.id.tvJobStatus);
            tvResidentName = v.findViewById(R.id.tvResidentName);
            tvLocation = v.findViewById(R.id.tvLocation);
            tvWasteType = v.findViewById(R.id.tvWasteType);
            tvWeight = v.findViewById(R.id.tvWeight);
            btnAcceptJob = v.findViewById(R.id.btnAcceptJob);
            btnCompleteJob = v.findViewById(R.id.btnCompleteJob);
        }
    }
}