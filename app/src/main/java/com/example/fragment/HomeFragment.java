package com.example.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.btnAddImage).setOnClickListener(v -> {
            // open image picker
        });

        List<Post> posts = new ArrayList<>();
        posts.add(new Post("Maria Santos", "MS", Color.parseColor("#D9785F"),
                "2 min ago",
                "Finally finished my first Android app! It took 3 weeks, a lot of coffee "
                        + "and many Stack Overflow tabs.",
                true, 128, 24, 5));
        posts.add(new Post("Carlo Reyes", "CR", Color.parseColor("#4A90D9"),
                "25 min ago",
                "Tip for beginners: learn RecyclerView early. Almost every screen needs a list.",
                false, 64, 11, 2));

        RecyclerView rv = view.findViewById(R.id.rvPosts);
        rv.setLayoutManager(new LinearLayoutManager(requireContext()));
        rv.setAdapter(new PostAdapter(posts));
    }
}



/*package com.example.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.material.imageview.ShapeableImageView;


public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ShapeableImageView avatar = view.findViewById(R.id.imgAvatar);
        //EditText etPost = view.findViewById(R.id.etPost);
        ImageButton btnAddImage = view.findViewById(R.id.btnAddImage);

        btnAddImage.setOnClickListener(v -> {
            // open image picker
        });
    }
}
*/
