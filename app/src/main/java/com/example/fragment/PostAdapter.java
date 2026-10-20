package com.example.fragment;

import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private final List<Post> posts;

    public PostAdapter(List<Post> posts) {
        this.posts = posts;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_post, parent, false);
        return new PostViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder h, int position) {
        Post p = posts.get(position);

        h.tvInitials.setText(p.initials);
        h.tvInitials.setBackgroundTintList(ColorStateList.valueOf(p.avatarColor));
        h.tvName.setText(p.name);
        h.tvTime.setText(p.time);
        h.tvContent.setText(p.content);
        h.imgPost.setVisibility(p.hasImage ? View.VISIBLE : View.GONE);
        h.tvLikes.setText(String.valueOf(p.likes));
        h.tvCommentsShares.setText(String.format(Locale.getDefault(),
                "%d comments · %d shares", p.comments, p.shares));
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    static class PostViewHolder extends RecyclerView.ViewHolder {
        final TextView tvInitials, tvName, tvTime, tvContent, tvLikes, tvCommentsShares;
        final ImageView imgPost;

        PostViewHolder(@NonNull View v) {
            super(v);
            tvInitials = v.findViewById(R.id.tvInitials);
            tvName = v.findViewById(R.id.tvName);
            tvTime = v.findViewById(R.id.tvTime);
            tvContent = v.findViewById(R.id.tvContent);
            tvLikes = v.findViewById(R.id.tvLikes);
            tvCommentsShares = v.findViewById(R.id.tvCommentsShares);
            imgPost = v.findViewById(R.id.imgPost);
        }
    }
}