package com.example.fragment;

public class Post {
    public final String name;
    public final String initials;
    public final int avatarColor;
    public final String time;
    public final String content;
    public final boolean hasImage;
    public final int likes;
    public final int comments;
    public final int shares;

    public Post(String name, String initials, int avatarColor, String time,
                String content, boolean hasImage, int likes, int comments, int shares) {
        this.name = name;
        this.initials = initials;
        this.avatarColor = avatarColor;
        this.time = time;
        this.content = content;
        this.hasImage = hasImage;
        this.likes = likes;
        this.comments = comments;
        this.shares = shares;
    }
}