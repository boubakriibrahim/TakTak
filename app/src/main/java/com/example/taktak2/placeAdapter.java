package com.example.taktak2;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class placeAdapter extends RecyclerView.Adapter<placeAdapter.PlaceView>{

    private Activity activity;
    ArrayList<Place> placesList = new ArrayList<>();

    public placeAdapter(Activity activity, ArrayList<Place> spacesList) {
        this.activity = activity;
        this.placesList = spacesList;
    }

    @NonNull
    @Override
    public PlaceView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardplace,parent,false);

        return new PlaceView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceView holder, int position) {

        Place place = placesList.get(position);
        holder.placeName.setText(place.PlaceName);
        //holder.images.setAdapter(place.placeImages);
        //ArrayList<Image> imagesList = new ArrayList<>();

        imageAdapter imAdapter = new imageAdapter(activity,place.imagesList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity,RecyclerView.HORIZONTAL,false);
        holder.images.setLayoutManager(layoutManager);
        holder.images.setAdapter(imAdapter);

    }

    @Override
    public int getItemCount() {
        return placesList.size();
    }

    public class PlaceView extends RecyclerView.ViewHolder{

        TextView placeName;
        RecyclerView images;

        public PlaceView(@NonNull View itemView) {
            super(itemView);

            placeName = (TextView)itemView.findViewById(R.id.placename);
            images = (RecyclerView)itemView.findViewById(R.id.card2);
        }
    }


}
