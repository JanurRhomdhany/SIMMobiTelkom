package com.example.opsmobitelkomandroid;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ViewPagerImageAdapter extends RecyclerView.Adapter<ViewPagerImageAdapter.ImageViewHolder> {

    private List<Uri> imageUris;
    private Context context;

    public ViewPagerImageAdapter(List<Uri> imageUris, Context context) {
        this.imageUris = imageUris;
        this.context = context;
    }

    public void setImageUris(Uri[] imageUris) {
        this.imageUris = Arrays.asList(imageUris);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewPagerImageAdapter.ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.showimageslayout, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPagerImageAdapter.ImageViewHolder holder, int position) {
        Uri imageUrl = imageUris.get(position);
        Glide.with(context)
                .load(imageUrl)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if (imageUris == null) return 0;
        return imageUris.size();
    }


    public class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.uploadImage);
        }
    }
//    @Override
//    public int getCount() {
//        return ImageUrls.size();
//    }



//    @NonNull
//    @Override
//    public Object instantiateItem(@NonNull ViewGroup container, int position) {
//        View view = layoutInflater.inflate(R.layout.showimageslayout, container, false);
//        ImageView imageView = view.findViewById(R.id.uploadImage);
//        imageView.setImageURI(ImageUrls.get(position));
//        container.addView(view);
//        return view;
//    }

//    @Override
//    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
//        return view == object;
//    }

//    @Override
//    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        ((RelativeLayout)object).removeView(container);
//    }
}
