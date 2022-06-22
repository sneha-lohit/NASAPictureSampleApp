package com.example.nasapicturesapp;

import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.ImageView;

import androidx.annotation.NonNull;
        import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class GridAdapter extends ArrayAdapter<PictureModel> {
    public GridAdapter(@NonNull Context context, ArrayList<PictureModel> courseModelArrayList) {
        super(context, 0, courseModelArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listitemView = convertView;
        if (listitemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.grid_item_layout, parent, false);
        }
        PictureModel pictureModel = getItem(position);
        ImageView courseIV = listitemView.findViewById(R.id.idIVPicture);

        Glide.with(getContext())
                .load(pictureModel.getUrl())
                .into(courseIV);
        return listitemView;
    }


}