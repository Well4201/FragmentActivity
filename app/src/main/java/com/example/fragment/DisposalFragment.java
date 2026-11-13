package com.example.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DisposalFragment extends Fragment implements BasuraRepository.OnDataChangeListener {

    private WasteRequestAdapter adapter;
    private RadioButton rbNareresiklo, rbNabubulok;
    private EditText etWeight, etLocation;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_disposal, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rbNareresiklo = view.findViewById(R.id.rbNareresiklo);
        rbNabubulok = view.findViewById(R.id.rbNabubulok);
        etWeight = view.findViewById(R.id.etWeight);
        etLocation = view.findViewById(R.id.etLocation);
        Button btnSubmitRequest = view.findViewById(R.id.btnSubmitRequest);
        RecyclerView rvWasteRequests = view.findViewById(R.id.rvWasteRequests);

        rvWasteRequests.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new WasteRequestAdapter(BasuraRepository.getInstance().getRequests());
        rvWasteRequests.setAdapter(adapter);

        btnSubmitRequest.setOnClickListener(v -> submitDisposalRequest());

        BasuraRepository.getInstance().addListener(this);
    }

    private void submitDisposalRequest() {
        String weightStr = etWeight.getText().toString().trim();
        String location = etLocation.getText().toString().trim();

        if (TextUtils.isEmpty(weightStr)) {
            etWeight.setError("Please enter weight in kg");
            return;
        }

        if (TextUtils.isEmpty(location)) {
            etLocation.setError("Please enter pickup address");
            return;
        }

        double weight = Double.parseDouble(weightStr);
        String wasteType = "Di-nabubulok / Nareresiklo";
        int ptsPerKg = 10;

        if (rbNabubulok.isChecked()) {
            wasteType = "Nabubulok";
            ptsPerKg = 8;
        } else if (!rbNareresiklo.isChecked()) {
            wasteType = "Nakatatapon";
            ptsPerKg = 5;
        }

        int estimatedPoints = (int) (weight * ptsPerKg);
        String reqId = "REQ-" + (System.currentTimeMillis() % 10000);

        WasteRequest req = new WasteRequest(reqId, "Wesley Faisan", wasteType, weight, location, "Pending", estimatedPoints, "Just now");
        BasuraRepository.getInstance().addRequest(req);

        etWeight.setText("");
        etLocation.setText("");
        Toast.makeText(requireContext(), "Disposal request logged! Nearby riders notified.", Toast.LENGTH_SHORT).show();
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