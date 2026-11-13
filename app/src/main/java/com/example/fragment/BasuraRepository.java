package com.example.fragment;

import java.util.ArrayList;
import java.util.List;

public class BasuraRepository {
    private static BasuraRepository instance;

    private final List<WasteRequest> requests = new ArrayList<>();
    private final List<PriceItem> priceItems = new ArrayList<>();
    private final List<RewardItem> rewardItems = new ArrayList<>();
    private int ecoPoints = 450;
    private String userRole = "Resident"; // or "Collector Rider"

    public interface OnDataChangeListener {
        void onDataChanged();
    }

    private final List<OnDataChangeListener> listeners = new ArrayList<>();

    private BasuraRepository() {
        seedSampleData();
    }

    public static synchronized BasuraRepository getInstance() {
        if (instance == null) {
            instance = new BasuraRepository();
        }
        return instance;
    }

    public void addListener(OnDataChangeListener listener) {
        listeners.add(listener);
    }

    public void removeListener(OnDataChangeListener listener) {
        listeners.remove(listener);
    }

    private void notifyListeners() {
        for (OnDataChangeListener listener : listeners) {
            listener.onDataChanged();
        }
    }

    private void seedSampleData() {
        requests.add(new WasteRequest("REQ-101", "Wesley Faisan", "Di-nabubulok / Nareresiklo", 4.5, "Brgy. Estefania, Bacolod", "Pending", 45, "10 mins ago"));
        requests.add(new WasteRequest("REQ-102", "Maria Santos", "Nabubulok", 3.0, "Mandalagan, Bacolod", "Rider En Route", 24, "25 mins ago"));
        requests.add(new WasteRequest("REQ-103", "Juan Dela Cruz", "Di-nabubulok / Nareresiklo", 6.0, "Brgy. Villamonte, Bacolod", "Completed", 60, "1 hr ago"));
        requests.add(new WasteRequest("REQ-104", "Elena Torres", "Nakatatapon", 2.0, "Bata, Bacolod", "Pending", 10, "2 hrs ago"));

        priceItems.add(new PriceItem("PET Plastic Bottles", "Nareresiklo", 15.00, 15));
        priceItems.add(new PriceItem("Aluminum & Tin Cans", "Nareresiklo", 50.00, 50));
        priceItems.add(new PriceItem("Cardboard & Paper", "Nareresiklo", 10.00, 10));
        priceItems.add(new PriceItem("Scrap Metal & Wires", "Nareresiklo", 30.00, 30));
        priceItems.add(new PriceItem("Organic Bio-Waste", "Nabubulok", 8.00, 8));

        rewardItems.add(new RewardItem("REW-1", "1kg Premium Jasmine Rice", "Groceries", 100, "Aling Nena's Sari-Sari Store"));
        rewardItems.add(new RewardItem("REW-2", "Instant Coffee 3-in-1 (10s)", "Groceries", 30, "Mang Juan Store"));
        rewardItems.add(new RewardItem("REW-3", "Mobile Load ₱50", "Utilities", 50, "Bacolod Eco Hub"));
        rewardItems.add(new RewardItem("REW-4", "Cooking Oil 500ml", "Groceries", 70, "Aling Nena's Sari-Sari Store"));
    }

    public List<WasteRequest> getRequests() {
        return requests;
    }

    public List<PriceItem> getPriceItems() {
        return priceItems;
    }

    public List<RewardItem> getRewardItems() {
        return rewardItems;
    }

    public int getEcoPoints() {
        return ecoPoints;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
        notifyListeners();
    }

    public void addRequest(WasteRequest request) {
        requests.add(0, request);
        notifyListeners();
    }

    public void updateRequestStatus(String reqId, String newStatus, int addPoints) {
        for (WasteRequest req : requests) {
            if (req.id.equals(reqId)) {
                req.status = newStatus;
                if (newStatus.equals("Completed")) {
                    ecoPoints += addPoints;
                }
                break;
            }
        }
        notifyListeners();
    }

    public boolean redeemReward(RewardItem reward) {
        if (ecoPoints >= reward.costPoints) {
            ecoPoints -= reward.costPoints;
            notifyListeners();
            return true;
        }
        return false;
    }
}