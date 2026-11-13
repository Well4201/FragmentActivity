package com.example.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Locale;

public class ProfileFragment extends Fragment implements BasuraRepository.OnDataChangeListener {

    private TextView tvFollowersCount;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvFollowersCount = view.findViewById(R.id.tvFollowersCount);
        View btnEditProfile = view.findViewById(R.id.btnEditProfile);
        View btnShare = view.findViewById(R.id.btnShare);
        View itemSavedPosts = view.findViewById(R.id.itemSavedPosts);
        View itemSettingsPrivacy = view.findViewById(R.id.itemSettingsPrivacy);
        View itemLogout = view.findViewById(R.id.itemLogout);

        updatePointsUI();
        BasuraRepository.getInstance().addListener(this);

        if (btnEditProfile != null) {
            btnEditProfile.setOnClickListener(v -> Toast.makeText(requireContext(), "Edit profile clicked", Toast.LENGTH_SHORT).show());
        }
        if (btnShare != null) {
            btnShare.setOnClickListener(v -> Toast.makeText(requireContext(), "Share profile link copied!", Toast.LENGTH_SHORT).show());
        }
        if (itemSavedPosts != null) {
            itemSavedPosts.setOnClickListener(v -> Toast.makeText(requireContext(), "Saved posts list", Toast.LENGTH_SHORT).show());
        }
        if (itemSettingsPrivacy != null) {
            itemSettingsPrivacy.setOnClickListener(v -> Toast.makeText(requireContext(), "Settings & Privacy opened", Toast.LENGTH_SHORT).show());
        }
        if (itemLogout != null) {
            itemLogout.setOnClickListener(v -> Toast.makeText(requireContext(), "Logged out", Toast.LENGTH_SHORT).show());
        }
    }

    private void updatePointsUI() {
        if (tvFollowersCount != null) {
            int pts = BasuraRepository.getInstance().getEcoPoints();
            tvFollowersCount.setText(String.format(Locale.getDefault(), "%d", pts));
        }
    }

    @Override
    public void onDataChanged() {
        updatePointsUI();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        BasuraRepository.getInstance().removeListener(this);
    }
}