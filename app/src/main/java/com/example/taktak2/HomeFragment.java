package com.example.taktak2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;


public class HomeFragment extends Fragment {

    RecyclerView recyclerplaces;
    final ArrayList<Place> placesList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerplaces = v.findViewById(R.id.spacesmodel1);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
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


                    placesList.add(place);

                    recyclerplaces.setAdapter(new placeAdapter(getActivity(),placesList));
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(),"Something wrong happened!",Toast.LENGTH_LONG).show();
            }
        });

        return v;
    }
}