package com.clipr.clipr;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class BarbDresserSearch extends AppCompatActivity {
    Button btnSearch;
    EditText txtSearchQuery;
    LinearLayout viewSearchResults;

    private static final int SEARCH_RELATED_NAMES = 1;
    private static final int SEARCH_BY_USERNAME = 2;
    private static final int SEARCH_BY_LAST_NAME = 3;
    private static final int POPULATE_RESULT_VIEW = 0;

    com.clipr.clipr.nameLibrary nameLibrary;
    ArrayList<barbDresserProfile> searchResults;
    String[] namesToSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barb_dresser_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nameLibrary = new nameLibrary();
        searchResults = new ArrayList<>();


        btnSearch = (Button) findViewById(R.id.btnSearch);
        txtSearchQuery = (EditText) findViewById(R.id.txtSearchQuery);
        viewSearchResults = (LinearLayout) findViewById(R.id.layoutSearchResults);


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputSearchQuery = txtSearchQuery.getText().toString();
                int indexOfSpace = inputSearchQuery.indexOf(" ");

                if (indexOfSpace >= 2)
                {
                    namesToSearch = nameLibrary.getRelatedNames(publicFunctions.formatName(inputSearchQuery));
                    runRecursiveSearch(SEARCH_RELATED_NAMES,0);
                }
                else if (indexOfSpace == -1)
                {

                }
                else
                {

                }
                runSearch(txtSearchQuery.getText().toString());


                namesToSearch = nameLibrary.getRelatedNames(txtSearchQuery.getText().toString());
            }
        });
    }

    private void runRecursiveSearch(int searchPhase,int nameIndex)
    {
        switch (searchPhase)
        {
            case SEARCH_RELATED_NAMES:
                final int currentIndex = nameIndex;
                final int nextIndex = nameIndex + 1;
                FirebaseDatabase.getInstance().getReference().child(publicFunctions.DatabaseSectionNames.BARB_DRESSER_PROFILES).orderByChild(barbDresserProfile.standardVariableKeys.NAME_KEY).equalTo(namesToSearch[nameIndex])
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                String currentName = namesToSearch[currentIndex];

                                if (dataSnapshot.hasChildren())
                                {
                                    Iterable<DataSnapshot> list = dataSnapshot.getChildren();

                                    for (DataSnapshot d : list)
                                    {

                                        String name = d.child(barbDresserProfile.standardVariableKeys.NAME_KEY).getValue(String.class);
                                        String description = d.child(barbDresserProfile.standardVariableKeys.DESCRIPTION_KEY).getValue(String.class);
                                        String attachedUID = d.getKey();
                                        int ratingTotal = d.child(barbDresserProfile.standardVariableKeys.RATING_TOTAL_KEY).getValue(int.class);
                                        int numOfRatings = d.child(barbDresserProfile.standardVariableKeys.NUM_OF_RATINGS_KEY).getValue(int.class);
                                        int numOfComments = d.child(barbDresserProfile.standardVariableKeys.NUM_OF_COMMENTS_KEY).getValue(int.class);
                                        searchResults.add(new barbDresserProfile(description,name,attachedUID,ratingTotal,numOfRatings,numOfComments));


                                    }


                                }
                                if (nextIndex == namesToSearch.length)
                                {
                                    //Load all search results here


                                    runRecursiveSearch(POPULATE_RESULT_VIEW,0);
                                }
                                else
                                {
                                    runRecursiveSearch(SEARCH_RELATED_NAMES,nextIndex);
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });




                break;
            case SEARCH_BY_USERNAME:

                break;
            case SEARCH_BY_LAST_NAME:

                break;
            case POPULATE_RESULT_VIEW:
                for (final barbDresserProfile profile : searchResults)
                {
                    RelativeLayout resultView = new RelativeLayout(BarbDresserSearch.this);
                    resultView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
                    resultView.setBackgroundColor(publicFunctions.cliprColors.LIGHT_BLUE);

                    final ImageView profilePhoto = new ImageView(BarbDresserSearch.this);

                    StorageReference ref = FirebaseStorage.getInstance().getReference().child(publicFunctions.StorageFolderNames.PROFILE_PICS)
                            .child(profile.getAttachedUID()+".jpg");
                    ref.getBytes(1024*1024).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                        @Override
                        public void onSuccess(byte[] bytes) {
                            Bitmap b = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                            profilePhoto.setImageBitmap(b);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });


                    profilePhoto.setImageResource(R.drawable.common_google_signin_btn_icon_dark);
                    profilePhoto.setLayoutParams(new LinearLayout.LayoutParams(100,100));
                    resultView.addView(profilePhoto);

                    TextView nameOfBarber = new TextView(BarbDresserSearch.this);
                    nameOfBarber.setText(profile.getName());
                    nameOfBarber.setTextSize(30);
                    nameOfBarber.setX(100);
                    nameOfBarber.setY((25) - (nameOfBarber.getHeight()/2));

                    resultView.addView(nameOfBarber);


                    resultView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent resultPage = new Intent(BarbDresserSearch.this,barbDresserProfilePage.class);
                            String[] profileInfo = {profile.getName(),
                                    profile.getDescription(),
                                    profile.getAttachedUID(),
                                    Integer.toString(profile.getNumComments()),
                                    Integer.toString(profile.getNumOfRatings()),
                                    Integer.toString(profile.getRatingTotal())};
                            resultPage.putExtra("profileInfo",profileInfo);
                            startActivity(resultPage);
                        }
                    });

                    viewSearchResults.addView(resultView);
                }
                int x= 0;

                break;
        }
    }


   private void runSearch(String name)
   {
       Query searchQuery = FirebaseDatabase.getInstance().getReference().child("barbDresserProfiles").orderByChild("name")
               .equalTo(name);
       ValueEventListener eventListener =  new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {
               Boolean exists = dataSnapshot.exists();
               /*
               TODO: Add code here that adds all search results to the display along with a profile pic and allows the user to click on them to see their profile
                */
           }

           @Override
           public void onCancelled(DatabaseError databaseError) {

           }
       };
       searchQuery.addListenerForSingleValueEvent(eventListener);
   }

}
