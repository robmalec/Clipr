package com.clipr.clipr;

import android.renderscript.Element;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static com.clipr.clipr.barbDresserProfile.standardVariableKeys.DESCRIPTION_KEY;
import static com.clipr.clipr.barbDresserProfile.standardVariableKeys.NAME_KEY;
import static com.clipr.clipr.barbDresserProfile.standardVariableKeys.NUM_OF_COMMENTS_KEY;
import static com.clipr.clipr.barbDresserProfile.standardVariableKeys.NUM_OF_RATINGS_KEY;
import static com.clipr.clipr.barbDresserProfile.standardVariableKeys.RATING_TOTAL_KEY;

/**
 * Created by malec_000 on 11/20/2017.
 */

public class barbDresserProfile
{
    private String name;
    private String description;
    private String attachedUID;
    private int ratingTotal;
    private int numOfRatings;
    private int numOfComments;

    public static class standardVariableKeys
    {
        public static final String DESCRIPTION_KEY = "Description";
        public static final String NAME_KEY = "Name";
        public static final String RATING_TOTAL_KEY = "RatingTotal";
        public static final String NUM_OF_RATINGS_KEY = "NumOfRatings";
        public static final String NUM_OF_COMMENTS_KEY = "NumOfComments";
        public static final String ATTACHED_UID_KEY = "AttachedUID";
    }



    public barbDresserProfile()
    {

    }
    public barbDresserProfile(String description, String name)
    {
        this.description = description;
        this.name = name;
        this.attachedUID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        ratingTotal = 0;
        numOfRatings = 0;
        numOfComments = 0;
    }
    public barbDresserProfile(String description, String name, String attachedUID)
    {
        this.description = description;
        this.name = name;
        this.attachedUID = attachedUID;
        ratingTotal = 0;
        numOfRatings = 0;
        numOfComments = 0;
    }



    public barbDresserProfile(barbDresserProfile profile)
    {
        this.description = profile.getDescription();
        this.numOfRatings = profile.getNumOfRatings();
        this.name = profile.getName();
        this.attachedUID = profile.attachedUID;
        this.ratingTotal = profile.ratingTotal;
        this.numOfComments = profile.numOfComments;
    }
    public barbDresserProfile(String description, String name, String attachedUID,int ratingTotal,int numOfRatings,int numOfComments)
    {
        this.description = description;
        this.name = name;
        this.attachedUID = attachedUID;
        this.ratingTotal = ratingTotal;
        this.numOfRatings = numOfRatings;
        this.numOfComments = numOfComments;
    }

    public void addToFirebase()
    {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String UID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child(publicFunctions.DatabaseSectionNames.BARB_DRESSER_PROFILES).child(getAttachedUID());
        //FirebaseDatabase.getInstance().getReference().child(publicFunctions.DatabaseSectionNames.USERS).child("User_1").setValue(new User("Username","email","8888888",true));

        firebasePackage p = new firebasePackage(description,name,attachedUID,numOfRatings,ratingTotal,numOfComments);
        ref.setValue(p);
    }

    private static class firebasePackage
    {
        String Description;
        String Name;
        String AttachedUID;
        int NumOfRatings;
        int RatingTotal;
        int NumOfComments;
        public firebasePackage()
        {

        }
        public firebasePackage(String description,String name,String attachedUID,int numOfRatings,int ratingTotal,int numOfComments)
        {
            this.Description = description;
            this.Name = name;
            this.AttachedUID = attachedUID;
            this.NumOfRatings = numOfRatings;
            this.RatingTotal = ratingTotal;
            this.NumOfComments = numOfComments;
        }
    }

    public void createCommentSection()
    {
        customerReview sampleComment = new customerReview("Sample comment",publicFunctions.getPrimaryTestID(),5);
        publicFunctions.getRefToDatabase(DatabaseType.TYPE_COMMENTS).child(attachedUID).push().setValue(sampleComment);
        int z = 9;

    }

    //Setters and getters
    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setRatingTotal(int ratingTotal)
    {
        this.ratingTotal = ratingTotal;
    }

    public void setNumOfRatings(int numOfRatings)
    {
        this.numOfRatings = numOfRatings;
    }

    public void setAttachedUID(String uid)
    {
        this.attachedUID = uid;
    }

    public void setNumOfComments(int numOfComments)
    {
        this.numOfComments = numOfComments;
    }

    public void setName(String name)
    {
        this.name = name;
    }


    public String getDescription(){
        return description;
    }

    public Double getCurrentRating()
    {
        return (((double) ratingTotal)/((double) getNumOfRatings()));
    }

    public int getNumOfRatings(){
        return numOfRatings;
    }

    public int getNumComments()
    {
        return numOfComments;
    }
    public int getRatingTotal() { return ratingTotal;}
    public String getName(){return name;}
    public String getAttachedUID(){return attachedUID;}

}
