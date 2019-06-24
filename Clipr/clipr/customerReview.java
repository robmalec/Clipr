package com.clipr.clipr;


/**
 * Created by malec_000 on 10/27/2017.
 */

public class customerReview {
    private String ReviewContents;
    private String ReviewAuthor; //ReviewAuthor is the UserID of the person that left the review
    private int rating;
    private int FoundHelpfulScore;

    public static class standardVariableKeys
    {
        public static final String FOUND_HELPFUL_SCORE = "foundHelpfulScore";
        public static final String REVIEW_AUTHOR = "reviewAuthor";
        public static final String REVIEW_CONTENTS = "reviewContents";
        public static final String RATING = "rating";
    }





    customerReview()
    {

    }

    customerReview(String reviewContents,String reviewAuthor,int commentRating)
    {
        ReviewContents=reviewContents;
        ReviewAuthor=reviewAuthor;
        rating=commentRating;
        FoundHelpfulScore=0;

    }
    customerReview(String reviewContents,String reviewAuthor,int commentRating,int foundHelpfulScore)
    {
        ReviewContents=reviewContents;
        ReviewAuthor=reviewAuthor;
        rating=commentRating;
        this.FoundHelpfulScore=foundHelpfulScore;

    }


    //Getters
    public String getReviewContents()
    {
        return ReviewContents;
    }
    public String getReviewAuthor()
    {
        return ReviewAuthor;
    }
    public int getFoundHelpfulScore()
    {
        return FoundHelpfulScore;
    }
    public int getReviewRating()
    {
        return rating;
    }


    //Setters
    public void reviewFoundHelpful(Boolean response)
    {
        if (response)
        {
            FoundHelpfulScore++;
        }
        else
        {
            FoundHelpfulScore--;
        }
    }



}
