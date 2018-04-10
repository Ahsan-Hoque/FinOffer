package com.example.ahsanulhoque.finoffer.service;

import com.example.ahsanulhoque.finoffer.domain.Product;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class ProductService {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("products");

    public boolean isProductAdded(Product product) {
        final boolean[] isAdded = new boolean[1];

        String id = databaseReference.push().getKey();
        product.setId(id);

        databaseReference.child(id).setValue(product, new DatabaseReference.CompletionListener() {
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
