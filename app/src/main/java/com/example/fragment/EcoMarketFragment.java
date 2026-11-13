package com.example.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Locale;

public class EcoMarketFragment extends Fragment implements BasuraRepository.OnDataChangeListener {

    private TextView tvPointsBalance;
    private PriceIndexAdapter priceAdapter;
    private RewardAdapter rewardAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_eco_market, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvPointsBalance = view.findViewById(R.id.tvPointsBalance);
        RecyclerView rvPriceIndex = view.findViewById(R.id.rvPriceIndex);
        RecyclerView rvRewards = view.findViewById(R.id.rvRewards);

        rvPriceIndex.setLayoutManager(new LinearLayoutManager(requireContext()));
        priceAdapter = new PriceIndexAdapter(BasuraRepository.getInstance().getPriceItems());
        rvPriceIndex.setAdapter(priceAdapter);

        rvRewards.setLayoutManager(new LinearLayoutManager(requireContext()));
        rewardAdapter = new RewardAdapter(BasuraRepository.getInstance().getRewardItems());
        rvRewards.setAdapter(rewardAdapter);

        updatePointsUI();
        BasuraRepository.getInstance().addListener(this);
    }

    private void updatePointsUI() {
        int points = BasuraRepository.getInstance().getEcoPoints();
        tvPointsBalance.setText(String.format(Locale.getDefault(), "%d PTS", points));
    }

    @Override
    public void onDataChanged() {
        updatePointsUI();
        if (priceAdapter != null) priceAdapter.notifyDataSetChanged();
        if (rewardAdapter != null) rewardAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        BasuraRepository.getInstance().removeListener(this);
    }
}