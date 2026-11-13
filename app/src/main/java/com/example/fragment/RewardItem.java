package com.example.fragment;

public class RewardItem {
    public String id;
    public String title;
    public String category;
    public int costPoints;
    public String storeName;

    public RewardItem(String id, String title, String category, int costPoints, String storeName) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.costPoints = costPoints;
        this.storeName = storeName;
    }
}