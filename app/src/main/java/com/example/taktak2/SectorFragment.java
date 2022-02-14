package com.example.taktak2;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SectorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SectorFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public SectorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SectorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SectorFragment newInstance(String param1, String param2) {
        SectorFragment fragment = new SectorFragment();
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
        View view = inflater.inflate(R.layout.fragment_sector, container, false);
        LinearLayout restaurants =(LinearLayout) view.findViewById(R.id.restaurantsLayout);
        LinearLayout café = (LinearLayout) view.findViewById(R.id.caféLayout);
        LinearLayout servicesPubliques = (LinearLayout) view.findViewById(R.id.servicePubliquesLayout);
        LinearLayout servicesSanitaires = (LinearLayout) view.findViewById(R.id.servicesSanitairesLayout);
        restaurants.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                       /* Intent intent = new Intent(getActivity(), Restau.class);
                        startActivity(intent);*/
                        RestauFragment restauFragment = new RestauFragment();
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.container,restauFragment);
                        transaction.commit();
                    }
                });


        café.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                       /* Intent intent = new Intent(getActivity(), Cafe.class);
                        startActivity(intent);*/
                        CafeFragment cafeFragment = new CafeFragment() ;
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.container,cafeFragment);
                        transaction.commit();


                    }
                });

        servicesPubliques.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                       /* Intent intent = new Intent(getActivity(), Services_publiques.class);
                        startActivity(intent);*/
                        ServicesPubliquesFragment servicesPubliquesFragment = new ServicesPubliquesFragment();
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.container,servicesPubliquesFragment);
                        transaction.commit();
                    }
                });
        servicesSanitaires.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        /*Intent intent = new Intent(getActivity(), Service_Sanitaires.class);
                        startActivity(intent);*/
                        ServicesSanitairesFragment servicesSanitairesFragment = new ServicesSanitairesFragment();
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.container,servicesSanitairesFragment);
                        transaction.commit();
                    }
                });

        return view;
    }
    
}
