package com.are.taktak;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditProfile extends AppCompatActivity {
    private FirebaseUser user;
    private DatabaseReference reference;

    private String userID;

    private ProgressBar progressBar;

    public String realnom,realprenom,realage,realemail;

    public FirebaseAuth auth;

    public EditText nom,prenom,age,email;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        Button button = (Button) findViewById(R.id.BtnModifier);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeCredentials();
            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        nom = (EditText) findViewById(R.id.EtNomDapresBase);
        prenom = (EditText) findViewById(R.id.EtPrenomDapresLaBase);
        age = (EditText) findViewById(R.id.EtAgeDapresLaBase);
        email = (EditText) findViewById(R.id.EtAdresseDapresLaBase);


        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if (userProfile != null){
                    realnom = userProfile.name;
                    realprenom = userProfile.name2;
                    realage = userProfile.age;
                    realemail = userProfile.email;



                    nom.setText(realnom);
                    prenom.setText(realprenom);
                    age.setText(realage);
                    email.setText(realemail);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(EditProfile.this,"Something wrong happened!",Toast.LENGTH_LONG).show();
            }
        });



    }

    public void changeCredentials() {
        if(isNameChanged() || isPrenomChanged() || isAgeChanged() || isEmailChanged() ){
            Toast.makeText(this,"Data has been changed!",Toast.LENGTH_LONG).show();
            startActivity(new Intent(this,Profile.class));
        }
        else{
            Toast.makeText(this,"Data is same and can not be updated!",Toast.LENGTH_LONG).show();
        }
    }

    private boolean isEmailChanged() {
        userID = user.getUid();
        if(!realemail.equals(email.getText().toString())){
            reference.child(userID).child("email").setValue(email.getText().toString());
            realemail=email.getText().toString();
            return true;
        }else{
            return false;
        }
    }

    private boolean isAgeChanged() {
        userID = user.getUid();
        if(!realage.equals(age.getText().toString())){
            reference.child(userID).child("age").setValue(age.getText().toString());
            realage=age.getText().toString();
            return true;
        }else{
            return false;
        }
    }

    private boolean isPrenomChanged() {
        userID = user.getUid();
        if(!realprenom.equals(prenom.getText().toString())){
            reference.child(userID).child("name2").setValue(prenom.getText().toString());
            realprenom=prenom.getText().toString();
            return true;
        }else{
            return false;
        }
    }

    private boolean isNameChanged() {
        userID = user.getUid();
        if(!realnom.equals(nom.getText().toString())){
            reference.child(userID).child("name").setValue(nom.getText().toString());
            realnom=nom.getText().toString();
            return true;
        }else{
            return false;
        }
    }

    public void changePassword(View view) {

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        auth = FirebaseAuth.getInstance();
        progressBar.setVisibility(View.VISIBLE);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if (userProfile != null){

                    String realemail = userProfile.email;

                    auth.sendPasswordResetEmail(realemail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(EditProfile.this,"check your email to reset your password!",Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
                            }
                            else{
                                Toast.makeText(EditProfile.this,"Try again! Something wrong happened!",Toast.LENGTH_LONG).show();
                            }
                        }
                    });


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(EditProfile.this,"Something wrong happened!",Toast.LENGTH_LONG).show();
            }
        });

    }
}