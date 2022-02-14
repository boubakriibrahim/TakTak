package com.example.taktak2;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class imageAdapter extends RecyclerView.Adapter<imageAdapter.imageView>{

    Activity activity;
    ArrayList<String> imagesList = new ArrayList<>();

    public imageAdapter(Activity activity,ArrayList<String> imagesList) {
        this.activity = activity;
        this.imagesList = imagesList;
    }

    @NonNull
    @Override
    public imageView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardimages,parent,false);

        return new imageView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull imageView holder, int position) {

        Bitmap img=null;
        Bitmap resized = null;

        String image = imagesList.get(position);
        //String image = "https://www.entreprises-magazine.com/wp-content/uploads/2020/03/Carrefour-Coronavirus.png";
        System.out.println(image);
        //Glide.with(activity).load(image).load(holder.realImage);
//        Uri uri = Uri.parse(image);
//        holder.realImage.setImageURI(uri);
        /*try{
            InputStream srt = new java.net.URL(image).openStream();
            img = BitmapFactory.decodeStream(srt);
            resized = Bitmap.createScaledBitmap(img,200,200,true);
        } catch (Exception e){
            e.printStackTrace();
        }*/
        //holder.realImage.setImageBitmap(resized);
        try {
            Picasso.get().load(image).into(holder.realImage);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return imagesList.size();
    }

    public class imageView extends RecyclerView.ViewHolder{

        ImageView realImage;
        public imageView(@NonNull View itemView) {
            super(itemView);

            realImage = (ImageView)itemView.findViewById(R.id.image_View);

        }
    }


}
