package com.martinez.dyim.pointofsale.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;
import com.martinez.dyim.pointofsale.R;
import com.martinez.dyim.pointofsale.helper.FirebaseHelper;
import com.martinez.dyim.pointofsale.model.User;

import java.util.ArrayList;

/**
 * Created by Dyim on 07/10/2017.
 */

public class UserAdapter extends BaseAdapter {

    ArrayList<User> userList;
    LayoutInflater inflater;
    EditText key, fname, mname, lname;
    Button btnSave;
    private Context context;

    public UserAdapter(Context context, ArrayList<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public User getItem(int position) {
        return userList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        if (inflater == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.adapter_users, parent, false);
        }

        TextView userName = (TextView) convertView.findViewById(R.id.userName);
        ImageButton edit = (ImageButton) convertView.findViewById(R.id.btn_edit);
        ImageButton delete = (ImageButton) convertView.findViewById(R.id.btn_delete);
        final User user = getItem(position);
        userName.setText(user.getFullName());

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                key.setText(user.getId());
                fname.setText(user.getFirstName());
                mname.setText(user.getMiddleName());
                lname.setText(user.getLastName());
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseHelper db = new FirebaseHelper(FirebaseDatabase.getInstance().getReference());
                db.deleteUser(user.getId());
            }
        });

        return convertView;
    }

    public void setTextField(EditText key, EditText fname, EditText mname, EditText lname, Button btnSave){
        this.key = key;
        this.fname = fname;
        this.mname = mname;
        this.lname = lname;
    }
}
