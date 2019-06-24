package com.clipr.clipr;


import java.util.Calendar;

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

/**
 * Created by Robert Malecki on 12/5/2017.
 */

public class testFail {
    private String expectedResult;
    private String actualResult;
    private String inputTestCase;
    private String testDateTime;
    private String onFunction;

    public testFail()
    {

    }

    public testFail(String expectedResult,String actualResult, String inputTestCase, String onFunction)
    {
        this.expectedResult = expectedResult;
        this.actualResult = actualResult;
        this.inputTestCase = inputTestCase;
        this.onFunction = onFunction;
        this.testDateTime = "Date: " + Calendar.getInstance().get(Calendar.MONTH)+"/"+Calendar.getInstance().get(Calendar.DATE) + " "
        + "Time: " + Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
                + ":" + Calendar.getInstance().get(Calendar.MINUTE)
        + ":" + Calendar.getInstance().get(Calendar.SECOND) + " "
        + Calendar.getInstance().get(Calendar.AM_PM);
    }

    public static class standardVariableKeys{
        public static String EXPECTED_RESULT = "expectedResult";
        public static String ACTUAL_RESULT = "actualResult";
        public static String INPUT_TEST_CASE = "inputTestCase";
        public static String TEST_DATE_TIME = "testDateTime";
    }

    private class firebasePackage
    {
        public String pExpectedResult;
        public String pActualResult;
        public String pInputTestCase;
        public String pTestDateTime;

        public firebasePackage()
        {

        }
        public firebasePackage(String expectedResult,String actualResult,String inputTestCase,String testDateTime)
        {
            pExpectedResult = expectedResult;
            pActualResult = actualResult;
            pInputTestCase = inputTestCase;
            pTestDateTime = testDateTime;
        }
    }

    public void postToFirebase()
    {
        try {
            FirebaseDatabase.getInstance().getReference().child("testing").child("failLogs").child(onFunction).push()
                    .setValue(new firebasePackage(this.expectedResult, this.actualResult, this.inputTestCase, this.testDateTime),
                            new DatabaseReference.CompletionListener() {
                                @Override
                                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                    int x = 0;
                                }
                            });
        }
        catch (Exception e)
        {
            int z = 0;
        }
    }

}
