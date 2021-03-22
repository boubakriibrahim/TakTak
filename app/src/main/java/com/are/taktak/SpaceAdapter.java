package com.are.taktak;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SpaceAdapter extends RecyclerView.Adapter<SpaceAdapter.SpaceView>{

    ArrayList<Space> spacesList = new ArrayList<>();

    public SpaceAdapter(ArrayList<Space> spacesList) {
        this.spacesList = spacesList;
    }

    @NonNull
    @Override
    public SpaceView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favoritespace,parent,false);

        return new SpaceView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpaceView holder, int position) {

        Space space = spacesList.get(position);
        holder.textSpaceName.setText(space.getSpaceName());
        holder.textSpaceDescription.setText(space.getSpaceDescription());
        holder.textSpaceSector.setText(space.getSpaceSector());
        //holder.imageSpace.setImageURI(space.getImageView());


    }

    @Override
    public int getItemCount() {
        return spacesList.size();
    }

    public class SpaceView extends RecyclerView.ViewHolder{

        TextView textSpaceName,textSpaceDescription,textSpaceSector;
        //ImageView imageSpace;
        public SpaceView(@NonNull View itemView) {
            super(itemView);

            textSpaceName = (TextView)itemView.findViewById(R.id.text_Space_Name);
            textSpaceDescription = (TextView)itemView.findViewById(R.id.text_Space_Description);
            textSpaceSector = (TextView)itemView.findViewById(R.id.text_Space_Sector);
            //imageSpace = (ImageView)itemView.findViewById(R.id.image_View);

        }
    }

}
