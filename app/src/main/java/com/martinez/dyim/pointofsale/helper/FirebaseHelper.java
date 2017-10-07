package com.martinez.dyim.pointofsale.helper;

import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.martinez.dyim.pointofsale.adapter.UserAdapter;
import com.martinez.dyim.pointofsale.model.User;

import java.util.ArrayList;

/**
 * Created by Dyim on 07/10/2017.
 */

public class FirebaseHelper {
    DatabaseReference db;
    FirebaseDatabase database;
    ArrayList<User> users = new ArrayList<>();
    public FirebaseHelper(DatabaseReference db) {
        this.db = db;
    }

    public void createUser(String fname, String mname, String lname) {
        User user = new User(fname, mname, lname);
        if (user != null) {
            try {
                db.child("users").push().setValue(user);
            } catch (DatabaseException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateUser(String key, String fname, String mname, String lname) {
        User user = new User(fname, mname, lname);
        if (user != null) {
            try {
                db.child("users").child(key).setValue(user);
            } catch (DatabaseException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteUser(String key) {
        db.child("users").child(key).removeValue();
    }
}
