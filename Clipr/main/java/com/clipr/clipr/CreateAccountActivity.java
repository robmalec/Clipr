package com.clipr.clipr;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.ResultCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CreateAccountActivity extends AppCompatActivity {

    private TextView mTxtName;
    private TextView mTxtUsername;
    private TextView mTxtPassword;
    private TextView mTxtEmail;
    private TextView mTxtPhoneNumber;
    private CheckBox mChkIsBarbDresser;
    private Button mBtnLogin;


    String name;
    String username;
    String password;
    String email;
    String phoneNumber;
    Boolean isBarbDresser;

    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();


    List<String> accountCreationErrors = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //Initializing database reference
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference();
        mAuth = FirebaseAuth.getInstance();



        //Initializing textViews
        mTxtName = (TextView) findViewById(R.id.txtName);
        mTxtEmail = (TextView) findViewById(R.id.txtEmail);
        mTxtPassword = (TextView) findViewById(R.id.txtPassword);
        mTxtUsername = (TextView) findViewById(R.id.txtUsername);
        mTxtPhoneNumber = (TextView) findViewById(R.id.txtPhoneNum);
        mChkIsBarbDresser = (CheckBox) findViewById(R.id.chkIsHairdresser);

        //Initializing login button
        mBtnLogin = (Button) findViewById(R.id.btnSignIn);
        Button mBtnCreateAccount = (Button) findViewById(R.id.btnCreateAccount);

        mBtnCreateAccount.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                name = publicFunctions.formatName(mTxtName.getText().toString());
                username = mTxtUsername.getText().toString();
                password = mTxtPassword.getText().toString();
                email = mTxtEmail.getText().toString();
                phoneNumber = mTxtPhoneNumber.getText().toString();
                isBarbDresser = mChkIsBarbDresser.isChecked();
                createNewAccount();
            }
        });

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreateAccountActivity.this,LoginActivity.class));
            }
        });
    }

        private void createNewAccount() {
            //TODO: Add the rest of the provided user information to the created account
            //TODO: Add EULA
            //TODO: Make sure account doesn't already exist with same email address
            //TODO: Code the sending of a confirmation email

            checkAccountValidity();


        }
        private void createBarbDresserProfile()
        {
            //TODO: Call an activity to get additional information from barbDresser
            String description = "I like to cut hair";
            barbDresserProfile profile = new barbDresserProfile(description,name);
            String userID = profile.getAttachedUID();

            try {
                profile.addToFirebase();
            }
            catch (Exception e)
            {
                int x = 0;
            }
            mDatabase.child(publicFunctions.DatabaseSectionNames.COMMENT_SECTIONS).child(userID).child("comment"+profile.getNumComments())
                    .setValue(new customerReview("Sample comment",userID,5));

        }
        private void createCommentSection()
        {

        }
        private void addUserData()
        {
            String userID = mAuth.getCurrentUser().getUid();
            User newUser = new User(username,email,phoneNumber,name,isBarbDresser);


            mDatabase.child(publicFunctions.DatabaseSectionNames.USERS).child(userID).setValue(newUser);
            publicFunctions.makeToastText(CreateAccountActivity.this,"User data added successfully");
            if (newUser.getIsBarbDresser())
            {
                createBarbDresserProfile();
            }

        }

         private void checkAccountValidity()
        {
            if (password.length()==0)
            {
                accountCreationErrors.add("Please add a password");
            }

            if (username.length()==0)
            {
                accountCreationErrors.add("Please add your username");
            }
            else if (!publicFunctions.checkForNonLetterCharacters(username,publicFunctions.INPUT_TYPE_EMAIL))
            {
                accountCreationErrors.add("Please remove any spaces and special characters other than _ from username");
            }

            //Checks if it's an @umich.edu email
            if (email.length()==0)
            {
                accountCreationErrors.add("Please add your email address");
            }
            else
            {
                if (!email.contains("@umich.edu"))
                {
                    accountCreationErrors.add("Please provide an @umich.edu email address");
                }
                if (!publicFunctions.checkForNonLetterCharacters(email,publicFunctions.INPUT_TYPE_EMAIL))
                {
                    accountCreationErrors.add("Please provide a valid email without any symbols or spaces");
                }
            }

            if (phoneNumber.length()==0)
            {
                accountCreationErrors.add("Please add your phone number");
            }
            else if (phoneNumber.length()!= 10)
            {
                accountCreationErrors.add("Please provide a phone number with only an area code");
            }

            if (name.length() == 0)
            {
                accountCreationErrors.add("Please add your name");
            }
            if (!name.contains(" "))
            {
                accountCreationErrors.add("Please provide your full name");
            }
            if (!publicFunctions.checkForNonLetterCharacters(name,publicFunctions.INPUT_TYPE_NAME))
            {
                accountCreationErrors.add("Please remove any special characters or numbers from your name");
            }


            Query q = FirebaseDatabase.getInstance().getReference().child("Users").orderByChild("email").equalTo(email);

         ValueEventListener listener = new ValueEventListener() {
             @Override
             public void onDataChange(DataSnapshot dataSnapshot) {
                 if (dataSnapshot.exists())
                 {
                     accountCreationErrors.add("Account already exists with this email");
                 }

                 if (accountCreationErrors.size()==0)
                 {
                     makeAccount();
                     addUserData();
                 }
                 else
                 {
                     String errorsToShow = "";
                     for(String error : accountCreationErrors)
                     {
                         errorsToShow += error + "\n";
                     }
                     publicFunctions.makeToastText(CreateAccountActivity.this,errorsToShow);
                     //Create dialog with account creation errors
                     accountCreationErrors.clear();
                 }

             }

             @Override
             public void onCancelled(DatabaseError databaseError) {

             }
         };

         q.addListenerForSingleValueEvent(listener);
        }
        private void makeAccount()
        {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        //publicFunctions.makeToastText(CreateAccountActivity.this,"Account creation successful");
                        FirebaseUser user = mAuth.getCurrentUser();
                    }
                }
            });
        }

}
