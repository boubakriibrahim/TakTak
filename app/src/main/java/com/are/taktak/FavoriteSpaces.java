package com.are.taktak;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class FavoriteSpaces extends AppCompatActivity {

    private DatabaseReference reference,reference2;

    RecyclerView recyclerSpaces;
    final ArrayList<Space> spacesList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_spaces);

        recyclerSpaces = findViewById(R.id.recycler_spaces);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerSpaces.setLayoutManager(layoutManager);

        //List<String> strings = new ArrayList<>();

        reference = FirebaseDatabase.getInstance().getReference("Spaces");


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //int cpt=1;
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Space space = new Space();
                    space.setSpaceName("nom");
                    String string = snapshot.getValue().toString();
                    System.out.println(string);
                    string = string.substring(1,string.length()-1);
                    String[] listStrings = string.split(",");

                    for (int i = 0;i<listStrings.length;i++){
                        String[] methods = listStrings[i].split("=");
                        listStrings[i] = methods[1];
                    }

                    for (int cpt=1; cpt<6;cpt++){
                        if (cpt==1)
                            space.setSpaceName(listStrings[0]);
                        if (cpt==2) {
                            space.setSpaceDescription(listStrings[1]);
                        }
                        if (cpt==3)
                            space.setSpaceSector(listStrings[2]);
                        if (cpt==4) {
                            space.setIsFavorite(listStrings[3]);
                        }
                    }
                    spacesList.add(space);
                    recyclerSpaces.setAdapter(new SpaceAdapter(spacesList));
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        /*reference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot2) {

                int cpt=1;
                for(DataSnapshot snapshot2 : dataSnapshot2.getChildren()){
                    String string = snapshot2.getValue().toString();

                    //strings.add(string);
                                *//*if (space.getIsFavorite() == "true")
                                     spacesList.add(space);*//*
                    space.setSpaceName(string);
                    if (cpt==1)
                        space.setIsFavorite(string);
                    if (cpt==2) {
                        space.setSpaceDescription(string);
                        System.out.println("space on boucle"+space.getSpaceDescription());
                    }
                    if (cpt==4)
                        space.setSpaceName(string);
                    if (cpt==5)
                        space.setSpaceSector(string);

                    cpt++;

                }
                System.out.println("ba3d acolade el for : "+space.getSpaceDescription());

                spacesList.add(space);
                recyclerSpaces.setAdapter(new SpaceAdapter(spacesList));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/



        //reference2 = reference.child("Space1");



        /*for (int i = 1 ; i < 20 ; i++){
            Space s = new Space();
            s.setSpaceName("Space name "+ i);
            s.setSpaceDescription("Space Description "+i);
            s.setSpaceSector("Space Sector "+i);
            spacesList.add(s);
        }*/


    }
}