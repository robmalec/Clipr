package com.clipr.clipr;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class messageCenter extends AppCompatActivity {
static messageInbox allMessages = new messageInbox();
LinearLayout allMessagesLayout;
RelativeLayout messagePreviewLayout;
TextView previewSenderText;
TextView previewMessageText;
FloatingActionButton btnSendNewMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_center);



        Bundle extras = getIntent().getExtras();
        String[] newMessageContents = extras.getStringArray("newMessageContents");
        String[] newMessageSenders = extras.getStringArray("newMessageSenders");
        String[] newMessageDateTimes = extras.getStringArray("newMessageDateTimes");
        allMessages.transferNewMessages(newMessageContents,newMessageSenders,newMessageDateTimes,messageCenter.this);



         messagePreviewLayout = (RelativeLayout) findViewById(R.id.exampleMessagePreviewLayout);
         previewSenderText = (TextView) findViewById(R.id.senderText);
         previewMessageText = (TextView) findViewById(R.id.messageContents);
         allMessagesLayout = (LinearLayout) findViewById(R.id.layoutAllMessages);
         btnSendNewMessage = (FloatingActionButton) findViewById(R.id.btnSendNewMessage);

        ArrayList<String> senderUIDs = new ArrayList<>();

        for (Message m : allMessages.getAllMessages())
        {
            if (!senderUIDs.contains(m.getSenderID()))
            {
             senderUIDs.add(m.getSenderID());
            }
        }

        String[] senderUIDArray = new String[senderUIDs.size()];
        for (int a = 0; a < senderUIDArray.length; a++)
        {
            senderUIDArray[a] = senderUIDs.get(a);
        }

        final String[] finalUIDArray = senderUIDArray;


        messagePreviewLayout.setVisibility(View.INVISIBLE);

        getUsernamesFromUID(0,senderUIDArray, new HashMap<String, String>());

        btnSendNewMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(messageCenter.this,messagesActivity.class));
            }
        });

    }

    private void getUsernamesFromUID(int inputIndex, String[] UIDArray, final HashMap<String,String> usernames)
    {
        final String[] finalUIDArray = UIDArray;
        final int thisInputIndex = inputIndex;
        final int nextIndex = inputIndex + 1;

        publicFunctions.getRefToDatabase(DatabaseType.TYPE_USER_INFO).orderByKey().equalTo(UIDArray[thisInputIndex])
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String thisUID = dataSnapshot.child(finalUIDArray[thisInputIndex]).getKey();
                        String thisUsername = dataSnapshot.child(finalUIDArray[thisInputIndex]).child(User.standardVariableKeys.USERNAME_KEY).getValue(String.class);
                        usernames.put(thisUID,thisUsername);
                        if (nextIndex == finalUIDArray.length)
                        {










                            for (int a = 0; a < finalUIDArray.length; a++)
                            {
                                final String finalUID = finalUIDArray[a];

                                final RelativeLayout tempPreviewLayout = new RelativeLayout(messageCenter.this);
                                tempPreviewLayout.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT));
                                tempPreviewLayout.setBackgroundColor(publicFunctions.cliprColors.LIGHT_BLUE);

                                final ImageView profilePic = new ImageView(messageCenter.this);




                                final OnFailureListener failure = new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                        TextView tempSenderText = new TextView(messageCenter.this);
                                        tempSenderText.setText(usernames.get(finalUID));
                                        tempSenderText.setTextSize(15);

                                        TextView tempMessageText = new TextView(messageCenter.this);
                                        Message thisMessage = allMessages.getMostRecentFromEachUsername().get(finalUID);
                                        tempMessageText.setText(thisMessage.getMessageContents());


                                        tempPreviewLayout.addView(tempSenderText);
                                        //tempPreviewLayout.addView(tempMessageText);
                                        tempPreviewLayout.addView(profilePic);

                                        tempPreviewLayout.setVisibility(View.VISIBLE);

                                        final Intent i = new Intent(messageCenter.this,messagesActivity.class);

                                        messageInbox fromThisUID = allMessages.getMessagesFromUID(finalUID);
                                        String[] senders = fromThisUID.getMessageSenderArray();
                                        String[] contents = fromThisUID.getMessageContentsArray();
                                        String[] dateTimes = fromThisUID.getMessageDateTimeArray();

                                        i.putExtra("senders",senders);
                                        i.putExtra("contents",contents);
                                        i.putExtra("dateTimes",dateTimes);
                                        i.putExtra("username",usernames.get(finalUID));

                                        tempPreviewLayout.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                startActivity(i);
                                            }
                                        });

                                        allMessagesLayout.addView(tempPreviewLayout);
                                    }
                                };


                                publicFunctions.getRefToStorage(StorageType.PROFILE_PHOTO,finalUIDArray[a])
                                        .getBytes(1024 * 1024).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                                    @Override
                                    public void onSuccess(byte[] bytes) {
                                        profilePic.setImageBitmap(BitmapFactory.decodeByteArray(bytes,0,bytes.length));
                                        failure.onFailure(new Exception());

                                    }
                                }).addOnFailureListener(failure);





                            }
                            {
                                TextView thisLayoutSenderText = (TextView) messagePreviewLayout.getChildAt(messagePreviewLayout.indexOfChild(previewSenderText));
                                //TODO: Finish adding new message previews to message center
                            }
                            //Add all messages to inbox screen
                        }
                        else
                        {
                            getUsernamesFromUID(nextIndex,finalUIDArray,usernames);
                        }


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

    }


    private void updateUsernameTextView(TextView usernameView)
    {

    }
}
