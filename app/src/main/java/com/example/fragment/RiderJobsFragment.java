package com.example.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RiderJobsFragment extends Fragment implements BasuraRepository.OnDataChangeListener {

    private RiderJobAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_rider_jobs, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView rvRiderJobs = view.findViewById(R.id.rvRiderJobs);
        rvRiderJobs.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new RiderJobAdapter(BasuraRepository.getInstance().getRequests());
        rvRiderJobs.setAdapter(adapter);

        BasuraRepository.getInstance().addListener(this);
    }

    @Override
    public void onDataChanged() {
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        BasuraRepository.getInstance().removeListener(this);
    }
}