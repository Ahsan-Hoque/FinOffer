package com.example.ahsanulhoque.finoffer.service;


import com.example.ahsanulhoque.finoffer.domain.UserProfile;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class UserProfileService {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("userProfile");

    public void createUserProfile(UserProfile userProfile) {

        //String id = databaseReference.push().getKey();
        //userProfile.setId(id);

        databaseReference.child(userProfile.getId()).setValue(userProfile, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                // it is not working
            }
        });
    }

    public List<UserProfile> getUserProfiles() {
        return null;
    }

    public UserProfile getUserProfile(String id) {
        return null;
    }

}
