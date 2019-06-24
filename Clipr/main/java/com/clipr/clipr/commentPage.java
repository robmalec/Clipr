package com.clipr.clipr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class commentPage extends AppCompatActivity {
    String profileID;
   private LinearLayout commentLayout;
    private Button mBtnSubmit;
    private TextView mTxtReviewContents;
    private RatingBar mRatingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_page);

        //Initialize views
        commentLayout = (LinearLayout) findViewById(R.id.commentSpace);
        mBtnSubmit = (Button) findViewById(R.id.btnSubmit);
        mTxtReviewContents = (TextView) findViewById(R.id.txtReviewContents);
        mRatingBar = (RatingBar) findViewById(R.id.ratingBar);


        Bundle extras = getIntent().getExtras();
        profileID = extras.getString("profileID");
        final String finalID = profileID;
        final ArrayList<customerReview> commentSection = new ArrayList<>();

        publicFunctions.getRefToDatabase(DatabaseType.TYPE_COMMENTS).orderByKey().equalTo(profileID)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                    String reviewContents = "";
                    String reviewAuthor = "";
                    int rating = 0;
                    int foundHelpfulScore = 0;


                    Iterable<DataSnapshot> allComments = dataSnapshot.child(finalID).getChildren();
                    for (DataSnapshot d : allComments)
                    {
                        reviewContents = d.child(customerReview.standardVariableKeys.REVIEW_CONTENTS).getValue(String.class);
                        reviewAuthor = d.child(customerReview.standardVariableKeys.REVIEW_AUTHOR).getValue(String.class);
                        rating = d.child(customerReview.standardVariableKeys.RATING).getValue(int.class);
                        foundHelpfulScore = d.child(customerReview.standardVariableKeys.FOUND_HELPFUL_SCORE).getValue(int.class);
                        commentSection.add(new customerReview(reviewContents,reviewAuthor,rating,foundHelpfulScore));
                    }

                    for (customerReview r : commentSection)
                    {
                        LinearLayout commentSpace = new LinearLayout(commentPage.this);
                        commentSpace.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,100));
                        TextView contents = new TextView(commentPage.this);
                        contents.setText(r.getReviewContents());

                        TextView txtRating = new TextView(commentPage.this);
                        txtRating.setText("This reviewer gave a rating of "+r.getReviewRating());

                        TextView foundHelpful = new TextView(commentPage.this);
                        foundHelpful.setText(r.getFoundHelpfulScore() + " people found this review helpful.");

                        commentSpace.addView(contents);
                        commentSpace.addView(txtRating);
                        commentSpace.addView(foundHelpful);

                        commentLayout.addView(commentSpace);
                    }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

        mBtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int rating = mRatingBar.getNumStars();
                customerReview review = new customerReview(mTxtReviewContents.getText().toString(),publicFunctions.getCurrentUID(),
                        rating);

                publicFunctions.getRefToDatabase(DatabaseType.TYPE_COMMENTS).child(finalID).push().setValue(review);

                publicFunctions.getRefToDatabase(DatabaseType.TYPE_PROFILE_INFO).orderByKey().equalTo(finalID)
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                               int currentRating = dataSnapshot.child(finalID).child(barbDresserProfile.standardVariableKeys.RATING_TOTAL_KEY).getValue(int.class);
                                int numOfRatings = dataSnapshot.child(finalID).child(barbDresserProfile.standardVariableKeys.NUM_OF_RATINGS_KEY).getValue(int.class);

                                numOfRatings++;
                                currentRating += rating;

                                publicFunctions.getRefToDatabase(DatabaseType.TYPE_PROFILE_INFO).child(finalID).child(barbDresserProfile.standardVariableKeys.RATING_TOTAL_KEY)
                                        .setValue(currentRating);
                                publicFunctions.getRefToDatabase(DatabaseType.TYPE_PROFILE_INFO).child(finalID).child(barbDresserProfile.standardVariableKeys.NUM_OF_RATINGS_KEY)
                                        .setValue(numOfRatings);
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });



            }
        });

    }
}
