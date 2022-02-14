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

public class FavoriteSpaces extends AppCompatActivity {

    RecyclerView recyclerSpaces;
    final ArrayList<Space> spacesList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_spaces);

        recyclerSpaces = findViewById(R.id.recycler_spaces);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerSpaces.setLayoutManager(layoutManager);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Spaces");


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                spacesList.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                    Space space = new Space();
                    String string = Objects.requireNonNull(snapshot.getValue()).toString();
                    //System.out.println(string);
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
                    System.out.println(listStrings[3]);
                    if (listStrings[3].equals("true")){
                        spacesList.add(space);}


                    recyclerSpaces.setAdapter(new SpaceAdapter(spacesList));
                }
                if (spacesList.isEmpty()){
                    Space space = new Space();
                    space.setSpaceDescription("You haven't selected any place yet.");
                    space.setSpaceSector("To select your places just click the star icon located next to each place.");
                    spacesList.add(space);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(FavoriteSpaces.this,"Something wrong happened!",Toast.LENGTH_LONG).show();
            }
        });

    }
}