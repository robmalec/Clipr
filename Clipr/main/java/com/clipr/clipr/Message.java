package com.clipr.clipr;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Calendar;


/**
 * Created by Robert Malecki on 12/3/2017.
 */



public class Message {
    public static class standardVariableKeys{
        public static final String MESSAGE_CONTENTS = "messageContents";
    }


    private String senderID;
    private String messageContents;
    private Calendar messageDateTime;

    public Message()
    {

    }
    public Message(String senderID, String messageContents, Calendar messageTime)
    {
        this.senderID = senderID;
        this.messageDateTime = messageTime;
        this.messageContents = messageContents;
    }




    public String getMessageContents()
    {
        return messageContents;
    }
    public String getSenderID()
    {
        return senderID;
    }

    public Calendar getMessageDateTime()
    {
        return  messageDateTime;
    }
    public String getDateTimeString()
    {
        return messageInbox.getStringFromDate(messageDateTime);
    }



    public static void uploadExampleMessages()
    {
        //publicFunctions.getRefToDatabase(DatabaseType.TYPE_MESSAGES).child(publicFunctions.getPrimaryTestID())
    }
}
