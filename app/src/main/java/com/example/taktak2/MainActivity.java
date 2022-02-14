package com.example.taktak2;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayAdapter<String> arrayAdapter;       // toolBar

    private ChipNavigationBar chipNavigationBar; // navBar
    private Fragment fragment = null;          // ---


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN); // Mettre l'application en fullscreen
        ListView listView = findViewById(R.id.my_list);  // utilisé pour searchView
        List<String> mylist = new ArrayList<>();
        /*mylist.add("Target1"); mylist.add("Localisation2") ;
        mylist.add("Goal3"); mylist.add("Path4");
        mylist.add("Target5"); mylist.add("Localisation6");*/
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mylist );
        listView.setAdapter(arrayAdapter);
        Toolbar toolbar = findViewById(R.id.main_toolbar);
        TextView toolBarTitle = findViewById(R.id.Title_text);
        toolbar.setTitle(""); // Supprimer le titre actuel
        toolbar.setTitle("TAKTAK");
        setSupportActionBar(toolbar);
        SectorFragment sectorFragment = new SectorFragment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.container,sectorFragment).commit();
        SectorFragment sectorFragment1 = new SectorFragment();
        FragmentManager manager1 = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.container,sectorFragment1).commit();
        SectorFragment sectorFragment2 = new SectorFragment();
        FragmentManager manager2 = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.container,sectorFragment2).commit();
        SectorFragment sectorFragment3 = new SectorFragment();
        FragmentManager manager3 = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.container,sectorFragment3).commit();


        // nav_Bar
        chipNavigationBar = findViewById(R.id.ChipNavigation);
        chipNavigationBar.setItemSelected(R.id.home, true);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();
        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                switch (i) {
                    case R.id.home:
                        fragment = new HomeFragment();
                        break;

                    case R.id.secteur:
                        fragment = new SectorFragment();
                        break;
                    case R.id.favoris:
                        fragment = new FavorisFragment();
                        break;

                    case R.id.profile:
                        fragment = new ProfileFragment();
                        break;

                }

                if (fragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
                }
            }
        });


    }



    // Pour searchView & listView ..
   @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.tool_bar,menu);
        MenuItem menuItem = menu.findItem(R.id.search_icon);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search Here!");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                arrayAdapter.getFilter().filter(newText);
                return true;
            }
        });





        return super.onCreateOptionsMenu(menu);
    }

    public void changeCredentials1(View view) {
        Intent intent = new Intent(this,EditProfile.class);
        startActivity(intent);


    }
    public void getLocation(View view) {
    }
    public void onCheckboxClicked(View v) {
        boolean checked = ((CheckBox) v).isChecked();

        switch(v.getId()) {
            case R.id.switcher:
                if (checked) {
                    // Add to favourite
                    Toast.makeText(this,"Added to favourite!",Toast.LENGTH_LONG).show();
                }
                else {
                    // Remove from favourite
                    Toast.makeText(this,"Removed from favourite!",Toast.LENGTH_LONG).show();
                    break;
                }
        }
    }
    public void linkedIn(View v) {
        //redirect to linkedIn
    }

    public void fbClick(View view) {
        startActivity(getOpenFacebookIntent());
    }
    public Intent getOpenFacebookIntent() {
        try {
            this.getPackageManager().getPackageInfo("com.facebook.katana", 0);
            return new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/209793105724365"));
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/association.robotique.ensi"));
        }
    }

    public void instaClick(View view) {
        Uri uri = Uri.parse("http://instagram.com/_u/association.robotique.ensi");
        Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

        likeIng.setPackage("com.instagram.android");

        try {
            startActivity(likeIng);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://instagram.com/association.robotique.ensi")));
        }
    }


    public void favoritePlace(View view) {

        TextView nomEspace = (TextView) view.findViewById(R.id.text_Space_Name);
        String nom = nomEspace.getText().toString();
        Intent intent = new Intent(this,Espace.class);
        intent.putExtra("nomEsp",nom);
        startActivity(intent);

    }

    public void favoritePlace2(View view) {

        TextView nomEspace = (TextView) view.findViewById(R.id.placename);
        String nom = nomEspace.getText().toString();
        Intent intent = new Intent(this,Espace.class);
        intent.putExtra("nomEsp",nom);
        startActivity(intent);

    }
}