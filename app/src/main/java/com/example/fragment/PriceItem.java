package com.example.fragment;

public class PriceItem {
    public String name;
    public String category; // "Nareresiklo", "Nabubulok"
    public double pricePerKg;
    public int pointsPerKg;

    public PriceItem(String name, String category, double pricePerKg, int pointsPerKg) {
        this.name = name;
        this.category = category;
        this.pricePerKg = pricePerKg;
        this.pointsPerKg = pointsPerKg;
    }
}