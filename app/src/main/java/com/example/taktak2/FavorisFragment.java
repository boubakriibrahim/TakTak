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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavorisFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavorisFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    RecyclerView recyclerSpaces;
    final ArrayList<Space> spacesList = new ArrayList<>();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FavorisFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FavorisFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FavorisFragment newInstance(String param1, String param2) {
        FavorisFragment fragment = new FavorisFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_favoris, container, false);

        recyclerSpaces = v.findViewById(R.id.recycler_spaces);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        recyclerSpaces.setLayoutManager(layoutManager);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Spaces");


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                spacesList.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                    Space space = snapshot.getValue(Space.class);
                    System.out.println(dataSnapshot.getValue());
                    System.out.println(space.getImageView());
                    System.out.println(space.getSpaceName());
                    if (space.getIsFavorite().equals("true")){
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
                Toast.makeText(getActivity(),"Something wrong happened!",Toast.LENGTH_LONG).show();
            }
        });

        


        return v ;
    }


}