package com.clipr.clipr;

import android.content.Context;
import android.graphics.Color;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by malec_000 on 11/19/2017.
 */

public class publicFunctions {
    public static class DatabaseSectionNames{
        public static final String BARB_DRESSER_PROFILES = "barbDresserProfiles";
        public static final String USERS = "Users";
        public static final String COMMENT_SECTIONS = "commentSections";
        public static final String MESSAGES = "Messages";
    }

    public static class StorageFolderNames{
        public static final String PROFILE_PIC_ICONS = "profilePicIcons";
        public static final String PROFILE_PICS = "profilePics";
        public static final String USER_PHOTO_LIBRARIES = "userLibraries";
    }

    public static class cliprColors{
        /*public static final Color BLUE = Color.valueOf;
        public static final Color RED = Color.valueOf(238,29,35);
        public static final Color LIGHT_BLUE = Color.valueOf(221,244,255);
        public static final Color LIGHT_RED = Color.valueOf(255,193,194);*/

        public static final int BLUE = Color.rgb(170,224,248);
        public static final int RED = Color.rgb(238,29,35);
        public static final int LIGHT_BLUE = Color.rgb(221,244,255);
        public static final int LIGHT_RED = Color.rgb(255,193,194);

    }

    private static class testAccountIDs{
        private static final String ID1 ="Gl15q19M5pNBu9jkrm3yVDkXwvG2";
        private static final String ID2 = "jlRkeWMzY8fKfxdtkuZEU5NUnBS2";

    }

    public static final ArrayList<String> testAccountIDs = new ArrayList<>(Arrays.asList(
            "Gl15q19M5pNBu9jkrm3yVDkXwvG2",
            "jlRkeWMzY8fKfxdtkuZEU5NUnBS2"
    ));

    public static final Boolean MASTER_TESTING_MODE = true;




    public static void makeToastText(Context c, String text) {
        Toast.makeText(c, text, Toast.LENGTH_LONG).show();
    }

    public static String formatName(String inputName)
    {
        String newInputName = "";

        for (int a = 0; a < inputName.length(); a++)
        {
            String tempChar = inputName.substring(a,(a+1));
            String nextChar = null;
            String lastChar = null;

            if ((a-1) >= 0)
            {
                lastChar = inputName.substring((a-1),a);
            }

            if (a+1 != inputName.length())
            {
                nextChar = inputName.substring((a+1),a+2);
            }
        if (Character.isUpperCase(tempChar.charAt(0))
                && (nextChar != null)
                && !Character.isUpperCase(nextChar.charAt(0))
                && (lastChar != null)
                && !lastChar.equals(" "))
        {
            newInputName += tempChar;
        }
        else
        {
            if ((lastChar == null) || (lastChar == " "))
            {
                newInputName += tempChar.toUpperCase();
            }

            }
            newInputName += tempChar.toLowerCase();
        }
        inputName = newInputName;


        String outputName = "";
        String[] nameFragments = inputName.split(" ");
        for (int x = 0; x < nameFragments.length; x++)
        {
            if (!nameFragments[x].equals(""))
            {
                outputName += nameFragments[x].substring(0,1).toUpperCase();
                outputName += nameFragments[x].substring(1);
                if (x != (nameFragments.length - 1))
                {
                    outputName += " ";
                }
            }
        }

        return outputName;
    }
    //TODO: Test checkForNonLetterCharacters function
    public static final int INPUT_TYPE_EMAIL = 1000;
    public static final int INPUT_TYPE_NAME = 1001;
    public static final int INPUT_TYPE_USERNAME = 1002;
    public static Boolean checkForNonLetterCharacters(String inputString, int inputType)
    {
        switch (inputType)
        {
            case INPUT_TYPE_EMAIL:
                for (int x = 33; x <= 47; x++)
                {
                    if ((inputString.indexOf(String.valueOf(x)) != -1) && (x != 46))
                    {
                        return false;
                    }
                }
                for (int x = 58; x <= 63; x++)
                {
                    if (inputString.indexOf(String.valueOf(x)) != -1)
                    {
                        return false;
                    }
                }
                for (int x = 91; x <= 96; x++)
                {
                    if ((inputString.indexOf(String.valueOf(x)) != -1) && (x != 95))
                    {
                        return false;
                    }
                }
                for (int x = 123; x <= 126; x++)
                {
                    if (inputString.indexOf(String.valueOf(x)) != -1)
                    {
                        return false;
                    }
                }
                break;
            case INPUT_TYPE_NAME:
                for (int x = 33; x <= 64; x++)
                {
                    if (inputString.indexOf(String.valueOf(x)) != -1)
                    {
                        return false;
                    }
                }
                for (int x = 91; x <= 96; x++)
                {
                    if (inputString.indexOf(String.valueOf(x)) != -1)
                    {
                        return false;
                    }
                }
                for (int x = 123; x <= 126; x++)
                {
                    if (inputString.indexOf(String.valueOf(x)) != -1)
                    {
                        return false;
                    }
                }
                break;
            case INPUT_TYPE_USERNAME:
                for (int x = 32; x <= 47; x++)
                {
                    if (inputString.indexOf(String.valueOf(x)) != -1)
                    {
                        return false;
                    }
                }
                for (int x = 58; x <= 64; x++)
                {
                    if (inputString.indexOf(String.valueOf(x)) != -1)
                    {
                        return false;
                    }
                }
                for (int x = 91; x <= 96; x++)
                {
                    if ((inputString.indexOf(String.valueOf(x)) != -1) && (x != 95))
                    {
                        return false;
                    }
                }
                for (int x = 123; x <= 126; x++)
                {
                    if (inputString.indexOf(String.valueOf(x)) != -1)
                    {
                        return false;
                    }
                }
                break;
        }
        return true;

    }

    public static String getPrimaryTestID()
    {
        return "TEST_ACCOUNT_776238701119989343711111445723551";
    }

    public static String getCurrentUID()
    {
        if (FirebaseAuth.getInstance().getCurrentUser() != null)
        {
            return FirebaseAuth.getInstance().getCurrentUser().getUid();
        }
        else
        {
            return getPrimaryTestID();
        }
    }


    public static DatabaseReference getRefToDatabase (DatabaseType type)
    {
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference childRef = null;
        switch (type)
        {
            case TYPE_USER_INFO:
                childRef = rootRef.child(DatabaseSectionNames.USERS);
                break;
            case TYPE_PROFILE_INFO:
                childRef = rootRef.child(DatabaseSectionNames.BARB_DRESSER_PROFILES);
                break;
            case TYPE_COMMENTS:
                childRef = rootRef.child(DatabaseSectionNames.COMMENT_SECTIONS);
                break;
            case TYPE_MESSAGES:
                childRef = rootRef.child(DatabaseSectionNames.MESSAGES);
                break;
        }
        return childRef;
    }

    public static StorageReference getRefToStorage (StorageType type)
    {
        String userID = "";
        if (FirebaseAuth.getInstance().getCurrentUser() == null)
        {
            userID = getPrimaryTestID();
        }
        else
        {
            userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        }

        StorageReference rootRef = FirebaseStorage.getInstance().getReference();
        StorageReference childRef = rootRef;
        switch (type)
        {
            case PHOTO_ON_POST:
                break;
            case PROFILE_PHOTO:
                childRef = rootRef.child(StorageFolderNames.PROFILE_PICS).child(userID+".jpg");
                break;
            case PROFILE_PHOTO_ICON:
                childRef = rootRef.child(StorageFolderNames.PROFILE_PIC_ICONS).child(userID+".jpg");
                break;
            case STORED_PHOTO_LIBRARY:
                break;
        }
        return childRef;
    }

    public static StorageReference getRefToStorage (StorageType type, String uid)
    {
        String userID = uid;

        StorageReference rootRef = FirebaseStorage.getInstance().getReference();
        StorageReference childRef = rootRef;
        switch (type)
        {
            case PHOTO_ON_POST:
                break;
            case PROFILE_PHOTO:
                childRef = rootRef.child(StorageFolderNames.PROFILE_PICS).child(userID+".jpg");
                break;
            case PROFILE_PHOTO_ICON:
                childRef = rootRef.child(StorageFolderNames.PROFILE_PIC_ICONS).child(userID+".jpg");
                break;
            case STORED_PHOTO_LIBRARY:
                break;
        }
        return childRef;
    }

    public static void createNewTestAccounts(Boolean isBarbDresser, int numTestAccounts)
    {
        for (int x = 0; x < numTestAccounts; x++)
        {
            User.generateTestAccount(isBarbDresser);
        }

    }

    public static String generateTestUID()
    {
        return "uid";

    }


    public static int pickColorRandomlyFromGroup(String choice)
    {
        ArrayList<android.graphics.Color> colors = new ArrayList<>();
        switch (choice)
        {
            case "Orange":
                colors.add(Color.valueOf(255,200,118));
                colors.add(Color.valueOf(232,164,108));
                colors.add(Color.valueOf(255,169,131));
                colors.add(Color.valueOf(232,128,108));
                colors.add(Color.valueOf(255,121,118));
                break;
        }
        android.graphics.Color c = colors.get((int)(Math.random() * colors.size()));

        return android.graphics.Color.rgb(c.red(),c.green(),c.blue());
    }

    public static int getRandomInt(int upperBound, Boolean inclusiveBound)
    {
        int result = 0;
        if (!inclusiveBound)
        {
            upperBound--;
        }

        if (upperBound > 0)
        {
            double randomNum = Math.random() * upperBound;
            float numToRound = (float) randomNum;
            result = Math.round(numToRound);
        }
        return result;

    }

    public static int getRandomInt(int lowerBound, int upperBound, boolean inclusiveBounds)
    {
        int result = 0;
        if (!inclusiveBounds)
        {
            lowerBound++;
            upperBound--;
        }

        if (upperBound > lowerBound)
        {
            double randomNum = Math.random() * (upperBound - lowerBound);
            float numToRound = (float) randomNum;
            result = lowerBound + (Math.round(numToRound));
        }
        else if (upperBound == lowerBound)
        {
            result = lowerBound;
        }
        else
        {
            //TODO: Find out why function isn't returning 0 when lower bound > upper bound
            result = 0;
        }
        return result;
    }

    public static Boolean getRandomBool()
    {
        return (getRandomInt(1,true) == 1);
    }


    public static class testingFunctions {

        public static String testAllUserFunctions() {
            /*TODO: As soon as this code is a fully functional prototype, finish this testing function

            User U = new User();

            //Testing u.getPhoneNumber
            String[] inputPhoneNumbers =
                    {"9894921870",
                    "19894921870"};

            String[] expectedResults =
                    {"(989) 492 1870",
                    "1 (989) 492 1870"};

            String tempTestResult = new String();

            if (inputPhoneNumbers.length == expectedResults.length) {
                for (int x = 0; x < inputPhoneNumbers.length; x++) {
                    tempTestResult = testGetPhoneNumber(U, inputPhoneNumbers[x], expectedResults[x]);
                    if (!tempTestResult.equals("pass")) {
                        return getFailMessage(inputPhoneNumbers[x], tempTestResult, expectedResults[x]);
                    }
                }
            }

            FirebaseAuth mAuth = FirebaseAuth.getInstance();


            //Test other functions here
            //formatName("   Alex Carson");
            //TODO: Come up with a more formalized way to test each individual function without needing to test all of them

            ArrayList<barbDresserProfile> testProfiles = new ArrayList<>();
            testProfiles.add(new barbDresserProfile("I like to cut hair","Mary Alexander",
                    DatabaseSectionNames.TEST_ACCOUNT_ID_PREFIX + Integer.toString(testProfiles.size() + 1),
                    0,0,0));
            testProfiles.add(new barbDresserProfile("I like to cut hair","John Rockefeller",
                    DatabaseSectionNames.TEST_ACCOUNT_ID_PREFIX + Integer.toString(testProfiles.size() + 1),
                    0,0,0));
            testProfiles.add(new barbDresserProfile("I like to cut hair","Johnny Rockefeller",
                    DatabaseSectionNames.TEST_ACCOUNT_ID_PREFIX + Integer.toString(testProfiles.size() + 1),
                    0,0,0));

            for (barbDresserProfile p : testProfiles)
            {
                p.addToFirebase();
            }


            //Boolean b = testGenerateRandomInts();
            ArrayList<String> testNames = testGetTestAccountNames();
            ArrayList<String> testUsernames = testGetTestAccountUsernames();
            */

            //testCreateTestAccounts();

            return "All user functions pass";
        }

        public static String testGetPhoneNumber(User u, String inputPhoneNumber, String expectedResult)
        {
            /*TODO: Rewrite as testEquivalence
            that takes as arguments an Object inputValue, an Object expectedResult and a User.function
            functionBeingTested
            TODO: Overload equivalence operators for all user-defined classes for testEquivalence function compatibility
             */
            u.setPhoneNumber(inputPhoneNumber);
            String functionResult = u.getPhoneNumber();
            if ((functionResult.equals(expectedResult)))
            {
                return "pass";
            }
            else
            {
                return functionResult;
            }
        }

        public static Boolean testGenerateRandomInts()
        {
            int resultNum = 0;
            int testResult = 0;
            int upperBound = 0;
            int lowerBound = 0;
            Boolean success = true;

            int[] upperBoundArray = {3,4,5,6,7,8,9,10,11,12,13,14,15};
            int[] lowerBoundArray = {-10,-9,-8,-7,-6,-5,-4,-3,-2,-1,0,1,2,3,4,5,6,7,8,9,10};

            int resultWithinBounds = 0;

            //[Test result, upper bound, within bound]
            ArrayList<Integer[]> results = new ArrayList<>();


            for (int x = 0; x < upperBoundArray.length; x++) {
                upperBound = upperBoundArray[x];
                testResult = getRandomInt(upperBound,true);
                if ((testResult >= lowerBound) && (testResult <= upperBound)) {
                    resultWithinBounds = 1;
                } else {
                    resultWithinBounds = 0;
                }
                results.add(new Integer[]{getRandomInt(upperBound,true), upperBound, lowerBound, resultWithinBounds});
            }

            for (int x = 0; x < lowerBoundArray.length; x++)
            {
                for (int y = 0; y < upperBoundArray.length; y++)
                {
                    lowerBound = lowerBoundArray[x];
                    upperBound = upperBoundArray[y];
                    testResult = getRandomInt(lowerBound,upperBound,true);
                    if ((testResult >= lowerBound) && (testResult <= upperBound)) {
                        resultWithinBounds = 1;
                    } else {
                        resultWithinBounds = 0;
                    }
                    results.add(new Integer[]{getRandomInt(upperBound,true), upperBound, lowerBound, resultWithinBounds});
                }
            }

            for (Integer[] i : results)
            {
                if (i[3] != 1)
                {
                    success = false;
                }
            }

            return success;
        }

        public static ArrayList<String> testGetTestAccountNames()
        {
            ArrayList<String> names = new ArrayList<>();
            nameLibrary nLib = new nameLibrary();

            for (int x = 0; x < 30; x++)
            {
                names.add(nLib.getTestAccountName());
            }

            return names;
        }

        public static ArrayList<String> testGetTestAccountUsernames()
        {
            nameLibrary nLib = new nameLibrary();
            ArrayList<String> usernames = new ArrayList<>();

            for (int x = 0; x < 50; x++) {
                usernames.add(nLib.getTestAccountUsername(nLib.getTestAccountName()));
            }
            return usernames;
        }

        public static void testCreateTestAccounts()
        {
            User.generateTestAccount(true);
        }



    }

    /*
    Requires: inputValue is an object for which there's a way to convert this input value into
    a string written below
    Modifies: Nothing
    Effects: Returns a message stating the input values and expected values for which the tests failed
     */
    private static String getFailMessage(Object inputValue, Object testResult, String expectedResult) {
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
}

