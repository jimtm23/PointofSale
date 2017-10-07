package com.martinez.dyim.pointofsale;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.martinez.dyim.pointofsale.adapter.UserAdapter;
import com.martinez.dyim.pointofsale.helper.FirebaseHelper;
import com.martinez.dyim.pointofsale.model.User;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private EditText firstName, middleName, lastName, key;
    private Button btnSave;
    private ListView userList;
    private static FirebaseDatabase firebaseDatabase;
    private FirebaseHelper db;
    private static UserAdapter userAdapter;
    ArrayList<User> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(firebaseDatabase == null) {
            firebaseDatabase = FirebaseDatabase.getInstance();
        }
        db = new FirebaseHelper(firebaseDatabase.getReference());

        firstName = (EditText) findViewById(R.id.input_firstname);
        middleName = (EditText) findViewById(R.id.input_middlename);
        lastName = (EditText) findViewById(R.id.input_lastname);
        key = (EditText) findViewById(R.id.key);
        btnSave = (Button) findViewById(R.id.btnSave);
        userList = (ListView) findViewById(R.id.userList);
        list = new ArrayList<>();
        userAdapter = new UserAdapter(this, list);
        userList.setAdapter(userAdapter);
        userAdapter.setTextField(key, firstName, middleName, lastName, btnSave);
        addUserChangeListener();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fname = firstName.getText().toString();
                String mname = middleName.getText().toString();
                String lname = lastName.getText().toString();
                String keyId = key.getText().toString();
                if (keyId.isEmpty()) {
                    db.createUser(fname, mname, lname);
                } else {
                    db.updateUser(keyId, fname, mname, lname);
                }
                firstName.setText("");
                middleName.setText("");
                lastName.setText("");
                key.setText("");
                InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        });
    }

    private void addUserChangeListener() {

        firebaseDatabase.getReference().child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
                    String key = childSnapshot.getKey();
                    User user = childSnapshot.getValue(User.class);
                    list.add(new User(key, user.getFirstName(),user.getMiddleName(),user.getLastName()));
                    userAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
