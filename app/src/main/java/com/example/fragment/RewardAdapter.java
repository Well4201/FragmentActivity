package com.example.fragment;

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

public class RewardAdapter extends RecyclerView.Adapter<RewardAdapter.ViewHolder> {
    private final List<RewardItem> list;

    public RewardAdapter(List<RewardItem> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reward, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RewardItem item = list.get(position);
        holder.tvRewardTitle.setText(item.title);
        holder.tvStoreName.setText(item.storeName);
        holder.tvRewardCost.setText(String.format(Locale.getDefault(), "%d Eco-Points", item.costPoints));

        holder.btnRedeem.setOnClickListener(v -> {
            boolean success = BasuraRepository.getInstance().redeemReward(item);
            if (success) {
                Toast.makeText(v.getContext(), "Successfully redeemed: " + item.title + " at " + item.storeName + "!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(v.getContext(), "Insufficient Eco-Points for this reward.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvRewardTitle, tvStoreName, tvRewardCost;
        Button btnRedeem;

        ViewHolder(@NonNull View v) {
            super(v);
            tvRewardTitle = v.findViewById(R.id.tvRewardTitle);
            tvStoreName = v.findViewById(R.id.tvStoreName);
            tvRewardCost = v.findViewById(R.id.tvRewardCost);
            btnRedeem = v.findViewById(R.id.btnRedeem);
        }
    }
}