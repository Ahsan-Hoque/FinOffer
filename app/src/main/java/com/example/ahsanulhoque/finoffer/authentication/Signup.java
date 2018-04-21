package com.example.ahsanulhoque.finoffer.authentication;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ahsanulhoque.finoffer.R;
import com.example.ahsanulhoque.finoffer.domain.UserProfile;
import com.example.ahsanulhoque.finoffer.service.UserProfileService;
import com.example.ahsanulhoque.finoffer.util.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;

import java.util.Date;


public class Signup extends AppCompatActivity {

    private EditText firstnameET;
    private EditText lastnameET;
    private EditText emailET;
    private EditText passwordET;
    private EditText confirmPasswordET;

    private Button signupBTN;

    private FirebaseAuth firebaseAuth;

    private UserProfileService userProfileService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        updateStatusBarColor("#FFFFFF");
        setContentView(R.layout.signup);

        userProfileService = new UserProfileService();

        firstnameET = (EditText) findViewById(R.id.firstnameET);
        lastnameET = (EditText) findViewById(R.id.lastnameET);
        emailET = (EditText) findViewById(R.id.emailET);
        passwordET = (EditText) findViewById(R.id.passET);
        confirmPasswordET = (EditText) findViewById(R.id.confirmPassET);

        signupBTN = (Button) findViewById(R.id.signupBTN);

        firebaseAuth = FirebaseAuth.getInstance();

        signupBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount(emailET.getText().toString(), passwordET.getText().toString());
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        updateUI(currentUser);
        //checkPasswords();
    }

    public void updateStatusBarColor(String color) {// Color must be in hexadecimal fromat
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor(color));

        }
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            /*firstnameET.setVisibility(View.GONE);
            lastnameET.setVisibility(View.GONE);
            emailET.setVisibility(View.GONE);
            passwordET.setVisibility(View.GONE);
            confirmPasswordET.setVisibility(View.GONE);
            signupBTN.setVisibility(View.GONE);*/

            
            // redirect to landing page
        } else {
            firstnameET.setVisibility(View.VISIBLE);
            lastnameET.setVisibility(View.VISIBLE);
            emailET.setVisibility(View.VISIBLE);
            passwordET.setVisibility(View.VISIBLE);
            confirmPasswordET.setVisibility(View.VISIBLE);

            signupBTN.setVisibility(View.VISIBLE);
        }
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = emailET.getText().toString();
        if (TextUtils.isEmpty(email)) {
            emailET.setError("Required!");
            valid = false;
        } else if (!Utils.isEmailValid(email)) {
            emailET.setError("Enter a valid email!");
            valid = false;
        } else {
            emailET.setError(null);
        }

        String password = passwordET.getText().toString();
        if (TextUtils.isEmpty(password)) {
            passwordET.setError("Required!");
            valid = false;
        } else if (!Utils.isPasswordValid(password)) {
            passwordET.setError("At least 6 characters required!");
            valid = false;
        } else {
            passwordET.setError(null);
        }

        String confirmPassword = confirmPasswordET.getText().toString();
        if (TextUtils.isEmpty(confirmPassword)) {
            confirmPasswordET.setError("Required.");
            valid = false;
        } else if (!Utils.isPasswordValid(confirmPassword)) {
            confirmPasswordET.setError("At least 6 characters required!");
            valid = false;
        } else if (!password.equals(confirmPassword)) {
            confirmPasswordET.setError("Confirm password not matched!");
            valid = false;
        } else {
            confirmPasswordET.setError(null);
        }

        return valid;
    }

    private void createAccount(final String email, String password) {

        if (!validateForm()) {
            return;
        }

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    updateUI(user);
                    // fill userProfile info's
                    UserProfile userProfile = new UserProfile();
                    userProfile.setFirstName(firstnameET.getText().toString().trim());
                    userProfile.setLastName(lastnameET.getText().toString().trim());
                    userProfile.setEmail(email);
                    userProfile.setCreated(new Date());

                    userProfileService.isAccountAdded(userProfile);
                } else {
                    FirebaseAuthException e = (FirebaseAuthException) task.getException();
                    e.printStackTrace();
                    Toast.makeText(Signup.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                    updateUI(null);
                }
            }
        });
    }

    /*private void checkPasswords() {
        confirmPasswordET.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                String strPass1 = passwordET.getText().toString();
                String strPass2 = confirmPasswordET.getText().toString();
                if (strPass2.length() >= 6 && !strPass1.equals(strPass2)) {
                    Toast.makeText(Signup.this, "Password not matched!", Toast.LENGTH_SHORT).show();
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
    }*/

}
