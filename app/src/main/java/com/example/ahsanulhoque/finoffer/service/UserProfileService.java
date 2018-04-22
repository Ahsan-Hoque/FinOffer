package com.example.ahsanulhoque.finoffer.service;


import com.example.ahsanulhoque.finoffer.domain.UserProfile;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

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
        final UserProfile[] userProfile = {null};
        Query query = databaseReference.child(id);//orderByChild("id").equalTo(0);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    /*for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"
                        System.out.println(issue.getKey() + " = " + issue.getValue());
                    }*/
                    userProfile[0] = dataSnapshot.getValue(UserProfile.class);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return userProfile[0];
    }

}
