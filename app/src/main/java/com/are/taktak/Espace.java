package com.are.taktak;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Espace extends AppCompatActivity {

    private DatabaseReference reference;
    private String spaceID;

    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_espace);


        this.ratingBar = (RatingBar) findViewById(R.id.ratingBar);


        Button submitButton = (Button) findViewById(R.id.ratingBtn);
        // perform click event on button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get values and then displayed in a toast
                String totalStars = "Total Stars:: " + ratingBar.getNumStars();
                String rating = "Rating :: " + ratingBar.getRating();
                Toast.makeText(getApplicationContext(), totalStars + "\n" + rating, Toast.LENGTH_LONG).show();
            }
        });


        reference = FirebaseDatabase.getInstance().getReference("Spaces");


        // spaceID = ............

        final ImageView image = (ImageView) findViewById(R.id.spaceImage);
        final TextView name = (TextView) findViewById(R.id.spaceName);
        final TextView description = (TextView) findViewById(R.id.spaceDescription);
        final TextView sector = (TextView) findViewById(R.id.spaceSector);


        /*reference.child(spaceID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Space spaceCredentials = snapshot.getValue(Space.class);

                if (spaceCredentials != null){
                    ImageView realImage = spaceCredentials.spaceImage;
                    String realName = spaceCredentials.spaceName;
                    String realDescription = spaceCredentials.spaceDescription;
                    String realSector = spaceCredentials.spaceSector;


                    // image.setImageDrawable(spaceImage); Setting image from firebase
                    name.setText(realName);
                    description.setText(realDescription);
                    sector.setText(realSector);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(Espace.this,"Something wrong happened!",Toast.LENGTH_LONG).show();
            }
        });*/




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
                    Toast.makeText(Espace.this,"Added to favourite!",Toast.LENGTH_LONG).show();
                }
            else {
                    // Remove from favourite
                    Toast.makeText(Espace.this,"Removed from favourite!",Toast.LENGTH_LONG).show();
                    break;
                }
        }
    }
}