package com.clipr.clipr;

import android.content.Context;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Robert Malecki on 12/4/2017.
 */

public class messageInbox {
    private ArrayList<Message> messages = new ArrayList<>();

    public messageInbox(String[] messageContents, String[] messageAuthors, String[] messageDateTimes)
    {
        for (int x = 0; x < messageContents.length; x++)
        {
            messages.add(new Message(messageAuthors[x],messageContents[x],
                    getDateFromString(messageDateTimes[x])));
        }
    }

    public messageInbox()
    {

    }

    public messageInbox getMessagesFromUID(String UID)
    {
        messageInbox inbox = new messageInbox();
        for (Message m : messages)
        {
            if (m.getSenderID().equals(UID))
            {
                inbox.addMessage(m.getMessageContents(),m.getSenderID(),m.getMessageDateTime());
            }
        }
        return inbox;
    }

    public void addInbox(messageInbox inbox)
    {
        for (Message m : inbox.getAllMessages())
        {
            messages.add(m);
        }
    }

    public void transferNewMessages(String[] messageContents, String[] messageAuthors, String[] messageDateTimes,Context c)
    {
        if (messageContents != null) {
            for (int x = 0; x < messageContents.length; x++) {
                messages.add(new Message(messageAuthors[x], messageContents[x],
                        getDateFromString(messageDateTimes[x])));
            }
        }
        moveToLocalStorage(c);

    }

    public void addMessage(String messageContents, String senderID, String messageDateTime)
    {
        messages.add(new Message(senderID,messageContents,getDateFromString(messageDateTime)));
    }

    public void addMessage(String messageContents, String senderID, Calendar messageDateTime)
    {
        Boolean messageAlreadyExists = false;
        for (Message m : messages)
        {
            if (messageContents.equals("Message from Jimmy!"))
            {
                int a = 0;
            }
            if (messageContents.equals(m.getMessageContents()))
            {
                messageAlreadyExists = true;
            }
        }
        if (!messageAlreadyExists)
        {
            messages.add(new Message(senderID,messageContents,messageDateTime));
        }
    }

    public void loadMessagesFromLocaStorage()
    {

    }

    public void moveToLocalStorage(Context c)
    {
        final String ROOT_MESSAGE_DIR = c.getFilesDir().toString()+"/"
                +publicFunctions.DatabaseSectionNames.MESSAGES;
        for (Message m : messages)
        {

        }
    }

    private void deleteMessagesOnServer()
    {

    }

    public static Calendar getDateFromString(String input)
    {
        String[] inputFrags = input.split("_");


        int year = Integer.parseInt(inputFrags[0]),
                month = Integer.parseInt(inputFrags[1]),
                date = Integer.parseInt(inputFrags[2]),
                hours = Integer.parseInt(inputFrags[3]),
                minutes = Integer.parseInt(inputFrags[4]),
                seconds = Integer.parseInt(inputFrags[5]);


        Calendar thisDate = new Calendar() {
            @Override
            protected void computeTime() {

            }

            @Override
            protected void computeFields() {

            }

            @Override
            public void add(int field, int amount) {

            }

            @Override
            public void roll(int field, boolean up) {

            }

            @Override
            public int getMinimum(int field) {
                return 0;
            }

            @Override
            public int getMaximum(int field) {
                return 0;
            }

            @Override
            public int getGreatestMinimum(int field) {
                return 0;
            }

            @Override
            public int getLeastMaximum(int field) {
                return 0;
            }
        };
        if (hours > 12)
        {
            hours -= 12;
            thisDate.set(Calendar.AM_PM,Calendar.PM);
        }
        else
        {
            thisDate.set(Calendar.AM_PM,Calendar.AM);
        }

        thisDate.set(year,month,date,hours,minutes,seconds);
        return thisDate;
    }

    public static String getStringFromDate(Calendar inputDate)
    {
        String hours = "";

        if (inputDate.get(Calendar.AM_PM) == Calendar.PM)
        {
            hours = Integer.toString(12 + inputDate.get(Calendar.HOUR_OF_DAY));
        }
        else
        {
            hours = Integer.toString(inputDate.get(Calendar.HOUR_OF_DAY));
        }


        return (Integer.toString(inputDate.get(Calendar.YEAR))
        + "_" + Integer.toString(inputDate.get(Calendar.MONTH))
        + "_" + Integer.toString(inputDate.get(Calendar.DAY_OF_MONTH))
        + "_" + hours
        + "_" + Integer.toString(inputDate.get(Calendar.MINUTE))
        + "_" + Integer.toString(inputDate.get(Calendar.SECOND)));

    }

    //Getters
    public String[] getMessageContentsArray()
    {
        String[] contentsArray = new String[messages.size()];
        int index = 0;
        for (Message m : messages)
        {
            contentsArray[index] = m.getMessageContents();
            index++;
        }
        return contentsArray;
    }

    public String[] getMessageSenderArray()
    {
        String[] senderArray = new String[messages.size()];
        int index = 0;
        for (Message m : messages)
        {
            senderArray[index] = m.getSenderID();
            index++;
        }
        return senderArray;
    }

    public String[] getMessageDateTimeArray()
    {
        String[] dateTimeArray = new String[messages.size()];
        int index = 0;
        for (Message m : messages)
        {
            dateTimeArray[index] = getStringFromDate(m.getMessageDateTime());
            index++;
        }
        return dateTimeArray;
    }

    public Message[] getMessagesOrderedByTime()
    {
        ArrayList<Message> originalMessages = new ArrayList<>();

        for (Message m : getAllMessages())
        {
            originalMessages.add(m);
        }

        Message[] messageArray = new Message[messages.size()];
        int index = 0;
        int messageArrayIndex = 0;
        int lastArrayIndex = messages.size() - 1;
        Message earliest = messages.get(index);
        while (messageArray[lastArrayIndex] == null)
        {
            Calendar thisMessageTime = messages.get(index).getMessageDateTime();
            Calendar earliestMessageTime = earliest.getMessageDateTime();
            int comp = thisMessageTime.compareTo(earliestMessageTime);



            if (thisMessageTime.before(earliest.getMessageDateTime()))
            {
                earliest = messages.get(index);
            }
            index++;
            if (index == messages.size())
            {
                messages.remove(earliest);
                messageArray[messageArrayIndex] = earliest;
                messageArrayIndex++;
                index = 0;
                if (messages.size() != 0) {
                    earliest = messages.get(index);
                }
            }
        }

        for (Message m : originalMessages)
        {
            messages.add(m);
        }



        return messageArray;
    }

    public ArrayList<Message> getAllMessages()
    {
        return messages;
    }

    public HashMap<String,Message> getMostRecentFromEachUsername()
    {
        HashMap<String,Message> result = new HashMap<>();
        for (Message m : messages)
        {
            if (!result.containsKey(m.getSenderID()))
            {
                result.put(m.getSenderID(),m);
            }
            else if (result.get(m.getSenderID()).getMessageDateTime().before(m.getMessageDateTime()))
            {
                result.remove(m.getSenderID());
                result.put(m.getSenderID(),m);
            }
        }
        return result;
    }

    public int getNumMessages()
    {
        return messages.size();
    }

}

