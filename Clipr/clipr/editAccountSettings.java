package com.clipr.clipr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

public class editAccountSettings extends AppCompatActivity {

    private EditText txtName;
    private EditText txtUsername;
    private EditText txtPhoneNumber;
    private EditText txtEmail;
    private Button btnSave;

    private String loadedName;
    private String loadedUsername;
    private String loadedEmail;
    private String loadedPhoneNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account_settings);


        btnSave = (Button) findViewById(R.id.btnSave);
        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtName = (EditText) findViewById(R.id.txtName);
        txtPhoneNumber = (EditText) findViewById(R.id.txtPhoneNumber);
        txtEmail = (EditText) findViewById(R.id.txtEmailAddress);

        String id;
        if (publicFunctions.MASTER_TESTING_MODE)
        {
            id = publicFunctions.getPrimaryTestID();
        }
        else
        {
            id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        }

         Query q = FirebaseDatabase.getInstance().getReference().child(publicFunctions.DatabaseSectionNames.USERS)
                .orderByChild(User.standardVariableKeys.USER_ID_KEY).equalTo(id);
        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DataSnapshot thisAccount = dataSnapshot.child(publicFunctions.getPrimaryTestID());
                loadedName = thisAccount.child(User.standardVariableKeys.NAME_KEY).getValue(String.class);
                txtName.setText(loadedName);

                loadedUsername = thisAccount.child(User.standardVariableKeys.USERNAME_KEY).getValue(String.class);
                txtUsername.setText(loadedUsername);

                loadedEmail = thisAccount.child(User.standardVariableKeys.EMAIL_KEY).getValue(String.class);
                txtEmail.setText(loadedEmail);

                loadedPhoneNumber = thisAccount.child(User.standardVariableKeys.PHONE_NUMBER_KEY).getValue(String.class);
                txtPhoneNumber.setText(loadedPhoneNumber);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference thisUser = FirebaseDatabase.getInstance().getReference()
                        .child(publicFunctions.DatabaseSectionNames.USERS).child(publicFunctions.getPrimaryTestID());
                if (!txtEmail.getText().toString().equals(loadedEmail))
                {

                }





            }
        });
    }
}
