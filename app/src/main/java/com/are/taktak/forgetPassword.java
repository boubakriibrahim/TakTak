package com.are.taktak;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgetPassword extends AppCompatActivity {

    private EditText emailEditText;
    private Button button;
    private ProgressBar progressBar;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgetpassword);

        Intent intent = getIntent();

        emailEditText = (EditText) findViewById(R.id.editTextTextEmailAddress);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        auth = FirebaseAuth.getInstance();

    }


    public void resetPass(View view) {
        String email = emailEditText.getText().toString().trim();

        if (email.isEmpty()){
            emailEditText.setError("L'email est requis!");
            emailEditText.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEditText.setError("Please enter a valid email!");
            emailEditText.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(forgetPassword.this,"check your email to reset your password!",Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                    //redirect to loginActivity
                    startActivity(new Intent(forgetPassword.this, loginActivity.class));
                }
                else{
                    Toast.makeText(forgetPassword.this,"Try again! Something wrong happened!",Toast.LENGTH_LONG).show();
                }
            }
        });
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
