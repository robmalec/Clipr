package com.clipr.clipr;

import android.app.Activity;

/**
 * Created by malec_000 on 11/15/2017.
 */
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.ResultCodes;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.ValueEventListener;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;





public class MainActivity extends Activity implements EasyPermissions.PermissionCallbacks {
    //For demonstration purposes, we'll have people login with a standard username and password
    //After demonstration, using facebook login support

    //Checking if username and password file already exist on the phone
    private static final int RC_SIGN_IN = 123;
    private static final int RC_CREATE_ACCOUNT = 321;

    //Return codes signalling which activity to run first for firstActivity action
    private static final int GOTO_CREATE_ACCOUNT_ACTIVITY = 0;
    private static final int GOTO_LOGIN_ACTIVITY = 1;
    private static final int GOTO_AUTO_LOGIN = 2;

    public static final String TEST_ACCOUNT_ID = "MWUOyrZvkJW6P6WayquwL0eNfZQ2";

    //For development purposes, set as true to always start the app the chosen page

    //For development purposes, app will first run user account function tests if set to true
    private static final Boolean testingMode = false;

    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;




    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //FirebaseDatabase.getInstance().getReference().child("test").setValue("I can still upload things");

        Boolean goStraightToActivity = true;
        Intent activityToTest = new Intent(MainActivity.this,mainNavigationPage.class);


        if (testingMode && publicFunctions.MASTER_TESTING_MODE) {
            testingFunctions TF = new testingFunctions(MainActivity.this);
            TF.testFunctions();
        }
        else if (goStraightToActivity && publicFunctions.MASTER_TESTING_MODE)
        {
            startActivity(activityToTest);
        }
        else
        {

                mAuth = FirebaseAuth.getInstance();
                mDatabase = FirebaseDatabase.getInstance();
                int activity = firstActivity(savedInstanceState);
                activity = GOTO_LOGIN_ACTIVITY;
                if (activity == GOTO_CREATE_ACCOUNT_ACTIVITY)
                {
                    startActivity(new Intent(this,CreateAccountActivity.class));
                }
                else if (activity == GOTO_AUTO_LOGIN)
                {
                    startActivity(new Intent(this,mainNavigationPage.class));
                }
                else if (activity == GOTO_LOGIN_ACTIVITY)
                {
                    List<AuthUI.IdpConfig> providers = new ArrayList<>();
                    providers.add(new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build());
                    /*providers = Arrays.asList(
                     new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                     new AuthUI.IdpConfig.Builder(AuthUI.PHONE_VERIFICATION_PROVIDER).build(),
                     new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build(),
                     new AuthUI.IdpConfig.Builder(AuthUI.FACEBOOK_PROVIDER).build(),
                     new AuthUI.IdpConfig.Builder(AuthUI.TWITTER_PROVIDER).build());*/


// Create and launch sign-in intent


                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setAvailableProviders(providers)
                                    .build(),
                            RC_SIGN_IN);
                }


            }
        }











    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode)
        {
            case RC_SIGN_IN:

                IdpResponse response = IdpResponse.fromResultIntent(data);

                if (resultCode == ResultCodes.OK) {
                    // Successfully signed in
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    // ...
                } else {
                    // Sign in failed, check response for error code
                    // ...
                }
            break;
            case RC_CREATE_ACCOUNT:
                if (resultCode == ResultCodes.OK)
                {
                    mAuth = FirebaseAuth.getInstance();
                    mDatabase = FirebaseDatabase.getInstance();

                }
                break;
        }
    }
    @Override
    public void onPermissionsGranted(int requestCode, List<String> list) {
        // Do nothing.
    }

    /**
     * Callback for when a permission is denied using the EasyPermissions
     * library.
     * @param requestCode The request code associated with the requested
     *         permission
     * @param list The requested permission list. Never null.
     */
    @Override
    public void onPermissionsDenied(int requestCode, List<String> list) {
        // Do nothing.
    }
    /*
    Requires: forceCreateAccount != null
    Modifies: Nothing
    Effects: Returns the first activity to which the user will be directed
     */
    private int firstActivity(Bundle savedInstanceState)
    {
        Boolean firstTime = firstTimeUsingApp(savedInstanceState);
        if ( firstTime)
        {
            return GOTO_CREATE_ACCOUNT_ACTIVITY;
        }
        else if (autoLoginInfoSaved())
        {
            return GOTO_AUTO_LOGIN;
        }
        else
        {
            return GOTO_LOGIN_ACTIVITY;
        }
    }

    /*
    Requires:
    Modifies:
    Effects: Returns true if this is the first time the app has been logged into
     */
    private Boolean firstTimeUsingApp(Bundle savedInstanceState)
    {
        Boolean UIDExists = true;
        Boolean firstTime = false;
        try
        {
            String s = mAuth.getCurrentUser().getUid();
        }
        catch (java.lang.NullPointerException e)
        {
            UIDExists = false;
        }

        if (savedInstanceState == null && !UIDExists)
        {
            firstTime = true;
        }

        return firstTime;
    }

    /*
    Requires:
    Modifies:
    Effects: Returns true if the user has their login info saved in order to log in automatically
     */
    private Boolean autoLoginInfoSaved()
    {
        FirebaseUser user = mAuth.getCurrentUser();
        //TODO: Write code for finding if the user has saved login info
        if (mAuth.getCurrentUser() != null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }




}
