package com.clipr.clipr;

/**
 * Created by Robert Malecki on 12/5/2017.
 */

public class functionsToTest {
    /*
    This function will return a "true" if any string of letters it's given is made entirely out of letters and numbers,
    with exceptions depending on what's being sent to it.  Test cases such as "Hello!","...e)*()*", and things like that
    will return false.  If it's an email address, there's an exception for numbers, periods and the @ sign.  Usernames
    can't have any special characters or spaces.

    By the way, you need to tell the function the difference between an email, name and username by setting the second
    variable given to the function as one of the three things below
     */
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

    /*
    This function takes a name that people may enter and corrects any mistakes in proper name capitalization
    they've made.  If they capitalized mulstiple letters in the middle of the word, forgot to capitalize the first letter
    of any words in their name, entered it in all caps or didn't add any caps at all, it should fix them.
    If someone entered a single capital letter in the middle of any of the names though, it should leave them.

    For example, if someone entered leBron james, it should return as LeBron James

    Not sure what happens if you enter special characters or symbols or numbers.  If the app starts crashing it's probably because you've done this.
     */

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





}
