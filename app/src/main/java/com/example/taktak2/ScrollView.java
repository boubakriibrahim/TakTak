package com.example.taktak2;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class ScrollView extends AppCompatActivity {

    RecyclerView recyclerplaces;
    final ArrayList<Place> placesList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view);

        recyclerplaces = findViewById(R.id.spacesmodel);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerplaces.setLayoutManager(layoutManager);


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("images");


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                placesList.clear();

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Place place = new Place();

                    String key = Objects.requireNonNull(snapshot.getKey()).toString();
                    Images images = snapshot.getValue(Images.class);


                    place.setPlaceName(key);
                    place.imagesList.add(images.img1);
                    place.imagesList.add(images.img2);
                    place.imagesList.add(images.img3);
                    place.imagesList.add(images.img4);
                    place.imagesList.add(images.img5);

                    System.out.println(place.imagesList.get(4));
                    /*String value = Objects.requireNonNull(snapshot.getValue()).toString();
                    //System.out.println(value);

                    value = value.substring(1,value.length()-1);
                    String[] listStrings = value.split(",");

                    for (int i=0;i<listStrings.length;i++){
                        String[] listStrings2 = listStrings[i].split("=");
                        //System.out.println(listStrings2[1]);
                        Image image = new Image("nomImage",listStrings2[1]);

                    }*/

                    placesList.add(place);

                    recyclerplaces.setAdapter(new placeAdapter(ScrollView.this,placesList));
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ScrollView.this,"Something wrong happened!",Toast.LENGTH_LONG).show();
            }
        });

    }
}