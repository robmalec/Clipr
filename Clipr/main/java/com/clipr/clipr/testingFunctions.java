package com.clipr.clipr;

import android.content.Context;
import android.content.Intent;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.all.All;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Callable;

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

public class testingFunctions {
    private Context c;
    private HashMap<Callable<Object>,Boolean> funcsToTest;
    private Boolean everyFunctionPasses = true;
    private String errorMessages = "";
    private LinearLayout messageLayout;
    private static ArrayList<String> outputMessages = new ArrayList<>();


    public testingFunctions(Context c) {
        this.c = c;
        messageLayout = new LinearLayout(c);
    }

    public void testFunctions()
    {
        ArrayList<Object[]> testCases = new ArrayList<>();
        ArrayList<Object> expectedResults = new ArrayList<>();
        ArrayList<Object> testResults = new ArrayList<>();
        Object[] testResultsArray = null;
        Object[] expectedResultsArray = null;


        String funcToTest = "";



        /*1.)



         Set this funcToTest variable to the name of the function that we're testing, with the variable types it takes in parenthesis at the end
        Explaination of data types:
        String: any chain of letters, spaces, or special symbols
        int: an integer number
        double: a rational number with a decimal

        IMPORTANT: Remember to replace every period . in funcToTest with an underscore _
        */

        //1.)Name of the function we're testing goes between the "" below, REPLACE ALL PERIODS WITH UNDERSCORES
        funcToTest = "exampleFunctions_exampleTakingIntAndString(int,String)";

        //1.) Different test cases go here, copy and paste the surrounding textCases.add stuff
        testCases.add(new Object[]{3,"String"});
        testCases.add(new Object[]{6,"String"});
        testCases.add(new Object[]{12,"String"});

        //2.) Add the result that we're expecting to receive from the function being tested to expectedResults, in the same order as they were added above
        expectedResults.add("Success");
        expectedResults.add("Success");
        expectedResults.add("Failure");

        //3.) Run the function and add its actual result to the testResults array list, copying and pasting the test cases exactly as they were entered in the testCases
        testResults.add(exampleFunctions.exampleTakingIntAndString(3,"String"));
        testResults.add(exampleFunctions.exampleTakingIntAndString(6,"String"));
        testResults.add(exampleFunctions.exampleTakingIntAndString(12,"String"));

        //4.) This last step analyzes the results, compares them to expectations, posts to firebase if any cases fail, and clears the arrayLists.

        testResultsArray = convertListToArray(testResults);
        expectedResultsArray = convertListToArray(expectedResults);

        testFunction(testCases,testResultsArray,expectedResultsArray,funcToTest);

        testCases.clear();
        testResults.clear();
        expectedResults.clear();

        //Example function taking 2 ints

        //Repeat the above for each function in functionsToTest.java

        funcToTest = "exampleFunctions_exampleTaking2Ints(int,int)";
        testCases.add(new Object[]{1,3});
        testCases.add(new Object[]{6,2});
        testCases.add(new Object[]{9,0});

        expectedResults.add(1);
        expectedResults.add(6);
        expectedResults.add(9);

        testResults.add(exampleFunctions.exampleTaking2Ints(1,3));
        testResults.add(exampleFunctions.exampleTaking2Ints(6,2));
        testResults.add(exampleFunctions.exampleTaking2Ints(9,0));

        //Copy and paste this code to the end of every set of test results added.

        testResultsArray = convertListToArray(testResults);
        expectedResultsArray = convertListToArray(expectedResults);

        testFunction(testCases,testResultsArray,expectedResultsArray,funcToTest);

        testCases.clear();
        testResults.clear();
        expectedResults.clear();

        //checkForNonLetterCharacters

        funcToTest = "functionsToTest_checkForNonLetterCharacters(String,int)";

        final int INPUT_TYPE_EMAIL = 1000;
        final int INPUT_TYPE_NAME = 1001;
        final int INPUT_TYPE_USERNAME = 1002;

        testCases.add(new Object[]{"robmalec@umich.edu",INPUT_TYPE_EMAIL});
        testCases.add(new Object[]{"robmale4@umich.edu",INPUT_TYPE_EMAIL});
        testCases.add(new Object[]{"robmal!!@notARealEmail.edu",INPUT_TYPE_EMAIL});
        testCases.add(new Object[]{"Oshri Olsberg",INPUT_TYPE_NAME});
        testCases.add(new Object[]{"Oshri Olsberg!",INPUT_TYPE_NAME});
        testCases.add(new Object[]{"Fuck you I'm gonna try to put a name and apostraphe in my username anyways!",INPUT_TYPE_USERNAME});
        testCases.add(new Object[]{"legalUsername92",INPUT_TYPE_USERNAME});

        expectedResults.add(true);
        expectedResults.add(true);
        expectedResults.add(false);
        expectedResults.add(true);
        expectedResults.add(false);
        expectedResults.add(false);
        expectedResults.add(true);

        testResults.add(functionsToTest.checkForNonLetterCharacters("robmalec@umich.edu",INPUT_TYPE_EMAIL));
        testResults.add(functionsToTest.checkForNonLetterCharacters("robmale4@umich.edu",INPUT_TYPE_EMAIL));
        testResults.add(functionsToTest.checkForNonLetterCharacters("robmal!!@notARealEmail.edu",INPUT_TYPE_EMAIL));
        testResults.add(functionsToTest.checkForNonLetterCharacters("Oshri Olsberg",INPUT_TYPE_NAME));
        testResults.add(functionsToTest.checkForNonLetterCharacters("Oshri Olsberg!",INPUT_TYPE_NAME));
        testResults.add(functionsToTest.checkForNonLetterCharacters("Fuck you I'm gonna try to put a space and apostraphe in my username anyways!",INPUT_TYPE_USERNAME));
        testResults.add(functionsToTest.checkForNonLetterCharacters("legalUsername92",INPUT_TYPE_USERNAME));


        testResultsArray = convertListToArray(testResults);
        expectedResultsArray = convertListToArray(expectedResults);

        testFunction(testCases,testResultsArray,expectedResultsArray,funcToTest);

        testCases.clear();
        testResults.clear();
        expectedResults.clear();



























        //KEEP ALL TEST CASES ABOVE THIS LINE

        if (!everyFunctionPasses)
        {
            outputMessages.add("Some cases failed, data posted to Firebase");
        }
        else
        {
            outputMessages.add("all cases passed");
        }

        Intent resultIntent = new Intent(c,testActivity.class);

        resultIntent.putExtra("outputMessages",outputMessages);

        c.startActivity(resultIntent);
    }

    private Object[] convertListToArray(ArrayList<Object> list)
    {
        int z = 0;
        Object[] resultArray = new Object[list.size()];
        for (Object o : list)
        {
            resultArray[z] = o;
            z++;
        }
        return resultArray;
    }

    private void addTextBoxToIntent(String text)
    {

    }

    private void testFunction(ArrayList<Object[]> testCases, Object[] testResults, Object[] expectedResults, String funcToTest)
    {
        String thisFuncErrorMessages = "";
        Boolean AllPasses = true;

        ArrayList<String> failMessages = new ArrayList<>();
        outputMessages.add("   in " + funcToTest);


        if (!(expectedResults[0].getClass() == testResults[0].getClass()))
        {
            thisFuncErrorMessages += "Expected results and test results are not of same variable type \n";
        }

        if (!(testCases.size() > 0))
        {
            thisFuncErrorMessages += "No test cases were given \n";
        }

        if (!(expectedResults.length > 0))
        {
            thisFuncErrorMessages += "No expected results were given \n";
        }

        if (!(testResults.length > 0))
        {
            thisFuncErrorMessages += "No test results were given \n";
        }

        if (expectedResults.length != testCases.size())
        {
            thisFuncErrorMessages += "Numbers of test cases and expected results do not match \n";
        }

        if (expectedResults.length != testResults.length)
        {
            thisFuncErrorMessages += "Numbers of test results and expected results do not match \n";
        }

        String[] errorFrags = thisFuncErrorMessages.split("\n");
        for (int a = 0; a < errorFrags.length; a++)
        {
            outputMessages.add(errorFrags[a]);
        }

        if (thisFuncErrorMessages.equals(""))
        {
            int  z = 0;
            for (Object[] o : testCases)
            {
                String s = testResults[z].getClass().toString();
                Boolean resultIsString = (testResults[z].getClass() == String.class);
                Boolean resultEqualsExpectation = false;
                if (resultIsString)
                {
                    if (testResults[z].equals(expectedResults[z]))
                    {
                        resultEqualsExpectation = true;
                    }
                }
                else
                {
                    if (testResults[z] == expectedResults[z])
                    {
                        resultEqualsExpectation = true;
                    }
                }


             if (!resultEqualsExpectation)
             {
                 everyFunctionPasses = false;
                 String failedTestCases = "";
                 for (int a = 0; a < o.length; a++)
                 {
                     failedTestCases += o[a].getClass().toString() + " " + o[a].toString() + ", ";
                 }
                 new testFail(expectedResults[z].toString(),testResults[z].toString(),failedTestCases.toString(),funcToTest)
                         .postToFirebase();

             }
             z++;
            }
        }

    }
    private static String getFailMessage(Object inputValue, Object testResult, Object expectedResult) {
        String finalInputValue = new String();
        String finalTestResult = new String();

        if (inputValue.getClass() == Integer.class) {
            finalInputValue = Integer.toString((int) inputValue);
        } else if (inputValue.getClass() == String.class) {
            finalInputValue = String.valueOf(inputValue);
        }

        if (testResult.getClass() == Integer.class) {
            finalTestResult = Integer.toString((int) testResult);
        } else if (testResult.getClass() == String.class) {
            finalTestResult = String.valueOf(testResult);
        }

        return ("Test failed with input value: " + finalInputValue + ",\n " +
                "test result value: " + finalTestResult + ",\n" +
                "and expected value: " + expectedResult);
    }

    private static class exampleFunctions
    {
        static String exampleTakingIntAndString(int x, String y)
        {
            return "Success";
        }
        static int exampleTaking2Ints(int a, int b)
        {
            return a;
        }
    }


}
