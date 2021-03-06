package com.messaging.chatrapid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;
import com.messaging.chatrapid.Model.Users;
import com.messaging.chatrapid.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {

    ActivitySignUpBinding binding;
    private FirebaseAuth auth;
    FirebaseDatabase database;
    GoogleSignInClient mGoogleSignInClientSignUp;
    ProgressDialog createAccount;
    ProgressDialog googleSignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        getSupportActionBar().hide();

        createAccount = new ProgressDialog(SignUpActivity.this);
        createAccount.setTitle("Create Account");
        createAccount.setMessage("We're Creating your Account");

        googleSignin = new ProgressDialog(SignUpActivity.this);
        googleSignin.setTitle("SignIn");
        googleSignin.setMessage("Signing in your Account via Google");

// Configure Google Sign Up
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClientSignUp = GoogleSignIn.getClient(this, gso);


        binding.btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount.show();
                auth.createUserWithEmailAndPassword(binding.emailId.getText().toString(), binding.passwordId.getText().toString()).
                        addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        createAccount.dismiss();

                        if(task.isSuccessful()){

                            Users user = new Users(binding.userNameId.getText().toString(), binding.emailId.getText().toString(),
                                    binding.passwordId.getText().toString());

                            String id = task.getResult().getUser().getUid();
                            database.getReference().child("Users").child(id).setValue(user);
                            Toast.makeText(SignUpActivity.this, "User Create Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
        binding.tvAlreadyHavAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);

                startActivity(intent);

            }
        });
        binding.btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
                googleSignin.show();
            }
        });
        binding.phnSignupLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this,PhoneNumberActivity.class);
                startActivity(intent);
            }
        });

    }
    int RC_SIGN_IN = 65;
    private void signIn() {
        Intent signInIntent = mGoogleSignInClientSignUp.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d("TAG", "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w("TAG", "Google sign in failed", e);
                // ...
            }
        }
    }
    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithCredential:success");
                            FirebaseUser user = auth.getCurrentUser();
                            Intent intent = new Intent(SignUpActivity.this,MainActivity.class);
                            startActivity(intent);
                            googleSignin.dismiss();
                            Toast.makeText(SignUpActivity.this, "Signin with Google", Toast.LENGTH_SHORT).show();
                            // updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "signInWithCredential:failure", task.getException());
                            Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            Snackbar.make(binding.getRoot(), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                            // updateUI(null);
                        }

                        // ...
                    }
                });
    }
}