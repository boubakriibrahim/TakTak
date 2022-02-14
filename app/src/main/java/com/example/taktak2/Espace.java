package com.example.taktak2;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.lang.Short.valueOf;

public class Espace extends AppCompatActivity {

    public static String espaceNom = "";
    public static String parent = "";
    public static Space favSp = new Space();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_espace);

        final ImageView image = (ImageView) findViewById(R.id.spaceImage);
        final TextView name = (TextView) findViewById(R.id.spaceName);
        final TextView description = (TextView) findViewById(R.id.spaceDescription);
        final TextView sector = (TextView) findViewById(R.id.spaceSector);
        final TextView nPers = (TextView) findViewById(R.id.nbPersons);
        final TextView time = (TextView) findViewById(R.id.nbPersons2);
        final TextView nRev = (TextView) findViewById(R.id.nbRates);
        final TextView rate = (TextView) findViewById(R.id.rate);
        final RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        final CheckBox check = (CheckBox) findViewById(R.id.switcher);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Spaces").child(parent).child("rate");
                Double r = round((ratingBar.getRating()+favSp.rate)/2,1);
                reference.setValue(r);
            }
        });


        Intent intent = getIntent();
        Bundle bundle = getIntent().getExtras();
        espaceNom = intent.getStringExtra("nomEsp");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Spaces");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                    Space space = snapshot.getValue(Space.class);


                    if (space.spaceName.equals(espaceNom)){
                        parent = snapshot.getKey();
                        favSp.setSpaceName(space.getSpaceName());
                        favSp.setImageView(space.getImageView());
                        favSp.setIsFavorite(space.getIsFavorite());
                        favSp.setSpaceDescription(space.getSpaceDescription());
                        favSp.setSpaceSector("("+space.getSpaceSector()+")");
                        favSp.setNbPersone(space.getNbPersone());
                        favSp.setRate(space.getRate());
                        favSp.setNbReviews(space.getNbReviews());
                    }

                }
                String fav = favSp.isFavorite;
                String desc = favSp.spaceDescription;
                String sect = favSp.spaceSector;
                String img = favSp.spaceImage;
                String nbPer = favSp.nbPersone;
                Double r = favSp.rate;
                int nbRates = favSp.nbReviews;

                name.setText(espaceNom);
                description.setText(desc);
                sector.setText(sect);
                rate.setText(r.toString());
                ratingBar.setRating(r.floatValue());
                nPers.setText("Number of persons There : "+nbPer);
                int timeEstimated = valueOf(nbPer)*10;

                time.setText("Time estimated : "+timeEstimated+" minutes");
                nRev.setText("("+nbRates+" Review)");

                try {
                    Picasso.get().load(img).into(image);
                } catch (Exception e){
                    e.printStackTrace();
                }
                if (fav.equals("true")){
                    check.setChecked(true);
                } else {
                    check.setChecked(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Espace.this,"Something wrong happened!",Toast.LENGTH_LONG).show();
            }
        });

    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public void linkedIn(View view) {
        //redirect to linkedIn
    }

    public void fbClick(View view) {
        startActivity(getOpenFacebookIntent());
    }
    public Intent getOpenFacebookIntent() {
        try {
            getPackageManager().getPackageInfo("com.facebook.katana", 0);
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


    public void getLocation(View view) {
    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        switch(view.getId()) {
            case R.id.switcher:
                if (checked) {
                    // Add to favourite
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Spaces").child(parent).child("isFavorite");
                    reference.setValue("true");
                    Toast.makeText(Espace.this,"Added to favourite!",Toast.LENGTH_LONG).show();
                    break;
                }
                else {
                    // Remove from favourite

                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Spaces").child(parent).child("isFavorite");
                    reference.setValue("false");
                    Toast.makeText(Espace.this,"Removed from favourite!",Toast.LENGTH_LONG).show();
                    break;
                }
        }
    }
}