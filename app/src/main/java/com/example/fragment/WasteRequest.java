package com.example.fragment;

public class WasteRequest {
    public String id;
    public String residentName;
    public String wasteType; // "Nabubulok", "Di-nabubulok / Nareresiklo", "Nakatatapon"
    public double weightKg;
    public String location;
    public String status; // "Pending", "Rider En Route", "Completed"
    public int pointsEarned;
    public String timestamp;

    public WasteRequest(String id, String residentName, String wasteType, double weightKg, String location, String status, int pointsEarned, String timestamp) {
        this.id = id;
        this.residentName = residentName;
        this.wasteType = wasteType;
        this.weightKg = weightKg;
        this.location = location;
        this.status = status;
        this.pointsEarned = pointsEarned;
        this.timestamp = timestamp;
    }
}