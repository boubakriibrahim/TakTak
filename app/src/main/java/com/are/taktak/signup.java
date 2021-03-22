package com.are.taktak;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class signup extends AppCompatActivity {



    private FirebaseAuth mAuth;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Intent intent = getIntent();

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();


    }



    public void registerAcc(View view) {
        EditText editText1 = (EditText) findViewById(R.id.editTextTextPersonName);
        String name = editText1.getText().toString().trim();

        EditText editText2 = (EditText) findViewById(R.id.editTextTextPersonName2);
        String name2 = editText2.getText().toString().trim();

        EditText editText3 = (EditText) findViewById(R.id.editTextTextEmailAddress);
        String mail = editText3.getText().toString().trim();

        EditText editText4 = (EditText) findViewById(R.id.editTextTextPassword);
        String pass = editText4.getText().toString().trim();

        EditText editText5 = (EditText) findViewById(R.id.editTextAge);
        String age = editText2.getText().toString().trim();

        if (name.isEmpty()){
            editText1.setError("First Name is required !");
            editText1.requestFocus();
            return;
        }
        if (name2.isEmpty()){
            editText2.setError("Second Name is required !");
            editText2.requestFocus();
            return;
        }
        if (age.isEmpty()){
            editText5.setError("Age is required !");
            editText5.requestFocus();
            return;
        }
        if (mail.isEmpty()){
            editText1.setError("Email is required !");
            editText1.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()){
            editText3.setError("Please provide a valid email !");
            editText3.requestFocus();
            return;
        }
        if (pass.isEmpty()){
            editText4.setError("Password is required !");
            editText4.requestFocus();
            return;
        }
        if (pass.length() < 6){
            editText4.setError("Min password length should be 6 characters !");
            editText4.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(mail,pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            User user = new User(name,name2,age,mail);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()){
                                        FirebaseUser newUser = FirebaseAuth.getInstance().getCurrentUser();
                                        newUser.sendEmailVerification();
                                        Toast.makeText(signup.this,"User has been registered successfully!",Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                        //redirect to loginActivity
                                        startActivity(new Intent(signup.this, loginActivity.class));
                                    }else{
                                        Toast.makeText(signup.this,"Failed to register! Try again!",Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(signup.this,"Failed to register! Try again!",Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });


    }

    public void loginAccount(View v) {
        Intent intent = new Intent(this, loginActivity.class);
        startActivity(intent);
    }

    public void linkedIn(View view) {
        startActivity(new Intent(this,Espace.class));
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
}