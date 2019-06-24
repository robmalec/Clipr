package com.clipr.clipr;

import com.google.firebase.auth.FirebaseAuth;



import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;


/**
 * Created by malec_000 on 11/19/2017.
 */

public class User {

    private String username;
    private String email;
    private String phoneNumber;
    private String userID;
    private String name;
    private Boolean isBarbDresser;

    public static class standardVariableKeys{
        public static final String USERNAME_KEY = "username";
        public static final String USER_ID_KEY = "userID";
        public static final String PHONE_NUMBER_KEY = "phoneNumber";
        public static final String EMAIL_KEY = "email";
        public static final String IS_BARB_DRESSER_KEY = "isBarbDresser";
        public static final String NAME_KEY = "name";
    }

    public static class testAccountUserInfo{
        public static final String PHONE_NUMBER = "9894921870";
        public static final String TEST_UID_PREFIX = "TEST_ACCOUNT_";
    }

        //Gets the details of the user that's already logged in
    public User()
    {

    }

    public User(String username, String email, String phoneNumber, String name, Boolean isBarbDresser)
    {
        //Initially, we're going to ask people for as little contact information as we can get away with while allowing them to add it later
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.isBarbDresser = isBarbDresser;
        this.name = name;
        this.updateInDatabase();
    }

    public void updateInDatabase()
    {
        getIDOfCurrentUser();
        FirebaseDatabase.getInstance().getReference().child("Users").child(userID).setValue(this);
    }

    public static void generateTestAccount(Boolean isBarbDresser)
    {
        getTestUIDAndFinish(isBarbDresser);
    }

    private static String generateID()
    {
        String uid = testAccountUserInfo.TEST_UID_PREFIX;
        for (int x = 0; x < 15; x++)
        {
            switch (publicFunctions.getRandomInt(0,2,true))
            {
                case 0:
                    //Add a lower-case letter
                    uid += String.valueOf(publicFunctions.getRandomInt(97,122,true));
                    break;
                case 1:
                    //Add an upper-case letter
                    uid += String.valueOf(publicFunctions.getRandomInt(41,90,true));
                    break;
                case 2:
                    //Add a number
                    uid += String.valueOf(publicFunctions.getRandomInt(30,39,true));
                    break;
            }
        }
        return uid;
    }

    private static void getTestUIDAndFinish(Boolean IBD)
    {
        final Boolean isBarbDresser = IBD;
        final String uid = generateID();

        Query newQuery = publicFunctions.getRefToDatabase(DatabaseType.TYPE_USER_INFO)
                .orderByChild(standardVariableKeys.USER_ID_KEY).equalTo(uid);
                newQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (!dataSnapshot.hasChildren())
                        {
                            nameLibrary nLib = new nameLibrary();
                            String thisName = nLib.getTestAccountName();
                            String thisUsername = nLib.getTestAccountUsername(thisName);
                            String thisEmail = thisUsername+"@umich.edu";
                            String thisPhoneNumber = testAccountUserInfo.PHONE_NUMBER;

                            User u = new User();
                            u.setName(thisName);
                            u.setUsername(thisUsername);
                            u.setEmail(thisEmail);
                            u.setPhoneNumber(thisPhoneNumber);
                            u.setIsBarbDresser(isBarbDresser);
                            u.setUserID(uid);
                            publicFunctions.getRefToDatabase(DatabaseType.TYPE_USER_INFO).child(uid).setValue(u);

                            if (isBarbDresser)
                            {
                                barbDresserProfile profile = new barbDresserProfile();
                                profile.setDescription("I like to cut hair");
                                profile.setAttachedUID(uid);
                                profile.setNumOfComments(0);
                                profile.setNumOfRatings(0);
                                profile.setRatingTotal(0);
                                profile.setName(thisName);

                                //profile.addToFirebase();

                                DatabaseReference thisProfile = publicFunctions.getRefToDatabase(DatabaseType.TYPE_PROFILE_INFO)
                                        .child(uid);

                                thisProfile.child(barbDresserProfile.standardVariableKeys.DESCRIPTION_KEY).setValue(profile.getDescription());
                                thisProfile.child(barbDresserProfile.standardVariableKeys.ATTACHED_UID_KEY).setValue(profile.getAttachedUID());
                                thisProfile.child(barbDresserProfile.standardVariableKeys.NAME_KEY).setValue(profile.getName());
                                thisProfile.child(barbDresserProfile.standardVariableKeys.NUM_OF_COMMENTS_KEY).setValue(profile.getNumComments());
                                thisProfile.child(barbDresserProfile.standardVariableKeys.NUM_OF_RATINGS_KEY).setValue(profile.getNumOfRatings());
                                thisProfile.child(barbDresserProfile.standardVariableKeys.RATING_TOTAL_KEY).setValue(profile.getRatingTotal());

                                profile.createCommentSection();
                            }
                        }
                        else
                        {
                            getTestUIDAndFinish(isBarbDresser);
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

    }


    //Setters
    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    public void setUserID(String ID)
    {
        this.userID = ID;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setIsBarbDresser(Boolean isBarbDresser)
    {
        this.isBarbDresser = isBarbDresser;
    }



    //Getters
    public String getUsername()
    {
        return this.username;
    }
    public String getEmail()
    {
        return this.email;
    }
    /*
        Modifies: returns phone number in (XXX) XXX XXXX format
     */
    public String getPhoneNumber()
    {
        String phoneNumberString = this.phoneNumber;
        String finalPhoneNumber = new String();
        switch (phoneNumberString.length())
        {
            case 10:
                finalPhoneNumber = "(" + phoneNumberString.substring(0,3) + ") " + phoneNumberString.substring(3,6) + " "
                        + phoneNumberString.substring(6,10);
                break;
            case 11:
                finalPhoneNumber = phoneNumberString.substring(0,1) + " (" + phoneNumberString.substring(1,4) + ") "
                        + phoneNumberString.substring(4,7) + " " + phoneNumberString.substring(7,11);
                break;
        }
        return finalPhoneNumber;
    }
    public String getIDOfCurrentUser()
    {
        if (userID == null)
        {
            userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        }
        return userID;
    }

    public boolean getIsBarbDresser()
    {
        return isBarbDresser;
    }





}
