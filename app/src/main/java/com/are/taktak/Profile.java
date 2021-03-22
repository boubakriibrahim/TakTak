package com.are.taktak;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference reference;

    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Button button = (Button)findViewById(R.id.BtnModifier);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openactivity2();
            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        final TextView nom = (TextView) findViewById(R.id.TvNomDapresBase);
        final TextView prenom = (TextView) findViewById(R.id.TvPrenomDapresLaBase);
        final TextView age = (TextView) findViewById(R.id.TvAgeDapresLaBase);
        final TextView email = (TextView) findViewById(R.id.TvAdresseDapresLaBase);



        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if (userProfile != null){
                    String realnom = userProfile.name;
                    String realprenom = userProfile.name2;
                    String realage = userProfile.age;
                    String realemail = userProfile.email;


                    nom.setText(realnom);
                    prenom.setText(realprenom);
                    age.setText(realage);
                    email.setText(realemail);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(Profile.this,"Something wrong happened!",Toast.LENGTH_LONG).show();
            }
        });

    }
    public void openactivity2(){
        Intent intent = new Intent(this,EditProfile.class);
        startActivity(intent);
    }
}