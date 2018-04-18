package com.example.ahsanulhoque.finoffer.service;


import com.example.ahsanulhoque.finoffer.domain.UserProfile;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserProfileService {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("userProfile");

    public boolean isAccountAdded(UserProfile userProfile) {
        final boolean[] isAdded = new boolean[1];

        String id = databaseReference.push().getKey();
        userProfile.setId(id);

        databaseReference.child(id).setValue(userProfile, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError != null) {
                    isAdded[0] = true;
                } else {
                    isAdded[0] = false;
                }
            }
        });

        return isAdded[0];
    }

}
