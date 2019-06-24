package com.clipr.clipr;

import android.app.ActionBar;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class messagesActivity extends AppCompatActivity {
    EditText txtNewMessage;
    EditText txtRecipientUsername;
    LinearLayout layoutConvo;
    FloatingActionButton btnSendNewMessage;

    Boolean txtNewMessageClicked;
    Boolean textYetChanged = false;
    Boolean doneLoading = false;


    long timeAtLastTextEdit = 0;
    long timeBeforeListenerSet = 0;
    long timeAtEnter = 0;
    long lastMessageShownTime = 0;


    String lastMessageAdded = "";

    ChildEventListener lMessageToUser;
    ChildEventListener lMessageFromUser;

    Message[] thisConvo;
    messageInbox convo = null;
    String otherPersonUsername = null;
    String otherPersonUID = null;
    Boolean messageChildListenersSet = false;

    DatabaseReference myInbox;
    DatabaseReference otherInbox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        txtNewMessageClicked = false;

        txtNewMessage = (EditText) findViewById(R.id.txtNewMessage);
        txtRecipientUsername = (EditText) findViewById(R.id.txtRecipientUsername);
        txtRecipientUsername.setTextColor(Color.BLUE);
        layoutConvo = (LinearLayout) findViewById(R.id.layoutConvo);
        btnSendNewMessage = (FloatingActionButton) findViewById(R.id.btnSendNewMessage);

        convo = new messageInbox();
        final Bundle extras = getIntent().getExtras();
        final long timeAtOnCreate = Calendar.getInstance().getTimeInMillis();

        lMessageToUser = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                long timeNow = Calendar.getInstance().getTimeInMillis();
                long difference = 0;

                if (extras == null)
                {
                    difference = timeNow - timeAtEnter;
                }
                else
                {
                    difference = timeNow - timeAtOnCreate;
                }

                if (difference >= 3000)
                {
                    showMessage(new Message(otherPersonUID, dataSnapshot.getValue(String.class), messageInbox.getDateFromString(dataSnapshot.getKey())));
                }

                int a = 0;

            }


            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        lMessageFromUser = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                long timeNow = Calendar.getInstance().getTimeInMillis();
                long difference = 0;
                if (extras == null)
                {
                    difference = timeNow - timeAtEnter;
                }
                else
                {
                    difference = timeNow - timeAtOnCreate;
                }

                if (difference >= 3000) {
                    showMessage(new Message(publicFunctions.getCurrentUID(), dataSnapshot.getValue(String.class), messageInbox.getDateFromString(dataSnapshot.getKey())));
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        final View.OnClickListener sendMessageListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long timeBeforeSleep = Calendar.getInstance().getTimeInMillis();
                long difference = timeBeforeSleep - timeAtEnter;
                if ((difference) < 3000) {
                    try {
                        Thread.sleep(3000 - difference + 2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }



                if ((thisConvo.length == 0) && (otherPersonUID != null))
                {
                    publicFunctions.getRefToDatabase(DatabaseType.TYPE_MESSAGES).child(publicFunctions.getCurrentUID())
                            .child(otherPersonUID).child(messageInbox.getStringFromDate(Calendar.getInstance())).setValue("///INITIAL_MESSAGE///");
                }
                publicFunctions.getRefToDatabase(DatabaseType.TYPE_MESSAGES).child(otherPersonUID)
                        .child(publicFunctions.getCurrentUID()).child(messageInbox.getStringFromDate(Calendar.getInstance()))
                        .setValue(txtNewMessage.getText().toString());
                txtNewMessage.setText("");
            }
        };

        View.OnKeyListener enterListener = new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == 66)
                {
                    timeAtEnter = Calendar.getInstance().getTimeInMillis();
                    final String tempOtherPersonUsername = txtRecipientUsername.getText().toString();
                    final Query q = publicFunctions.getRefToDatabase(DatabaseType.TYPE_USER_INFO).orderByChild(User.standardVariableKeys.USERNAME_KEY)
                            .equalTo(tempOtherPersonUsername);
                    q.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.hasChildren()) {
                                txtRecipientUsername.setTextColor(Color.BLACK);

                                String thisPersonUID = "";

                                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                                for (DataSnapshot ds : children) {
                                    thisPersonUID = ds.getKey();
                                }

                                final String finalThisPersonUID = thisPersonUID;
                                otherPersonUID = thisPersonUID;
                                otherPersonUsername = tempOtherPersonUsername;

                                //Getting messages to
                                final Query messagesTo = publicFunctions.getRefToDatabase(DatabaseType.TYPE_MESSAGES).child(thisPersonUID)
                                        .orderByKey().equalTo(publicFunctions.getCurrentUID());
                                messagesTo.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        Iterable<DataSnapshot> ds = dataSnapshot.child(publicFunctions.getCurrentUID()).getChildren();
                                        for (DataSnapshot snapshot : ds) {
                                            convo.addMessage(snapshot.getValue(String.class), publicFunctions.getCurrentUID(), messageInbox.getDateFromString(snapshot.getKey()));
                                        }
                                        //Getting messages from
                                        final Query messagesFrom = publicFunctions.getRefToDatabase(DatabaseType.TYPE_MESSAGES).child(publicFunctions.getCurrentUID())
                                                .orderByKey().equalTo(finalThisPersonUID);

                                        messagesFrom.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                Iterable<DataSnapshot> ds = dataSnapshot.child(finalThisPersonUID).getChildren();
                                                for (DataSnapshot snapshot : ds) {
                                                    convo.addMessage(snapshot.getValue(String.class), tempOtherPersonUsername, messageInbox.getDateFromString(snapshot.getKey()));
                                                }

                                                thisConvo = convo.getMessagesOrderedByTime();
                                                myInbox = publicFunctions.getRefToDatabase(DatabaseType.TYPE_MESSAGES).child(publicFunctions.getCurrentUID()).child(otherPersonUID);
                                                otherInbox = publicFunctions.getRefToDatabase(DatabaseType.TYPE_MESSAGES).child(otherPersonUID).child(publicFunctions.getCurrentUID());

                                                for (int z = 0; z < thisConvo.length; z++) {
                                                    showMessage(thisConvo[z]);
                                                }
                                                doneLoading = true;
                                                btnSendNewMessage.setOnClickListener(sendMessageListener);
                                                setNewMessageListeners();

                                            }

                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {

                                            }
                                        });


                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });


                            } else {
                                txtRecipientUsername.setTextColor(Color.BLUE);
                                q.removeEventListener(this);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                return false;

            }
        };

        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int a = 0;
            }

            @Override
            public void afterTextChanged(Editable s) {
                layoutConvo.removeAllViews();
                doneLoading = false;


            }
        };

        if (extras == null   )
        {
            //TODO: Write code here for setting the sender textbox to the name of the person the message recipient
        String username = txtRecipientUsername.getText().toString();
        //.addTextChangedListener(watcher);
        txtRecipientUsername.setOnKeyListener(enterListener);
        }
        else
        {

            String[] senderIDs = extras.getStringArray("senders");
            String[] contents = extras.getStringArray("contents");
            String[] dateTimes = extras.getStringArray("dateTimes");
            otherPersonUsername = extras.getString("username");
            otherPersonUID = senderIDs[0];

            txtRecipientUsername.setText(otherPersonUsername);
            txtRecipientUsername.setEnabled(false);

            convo = new messageInbox(contents, senderIDs, dateTimes);
            myInbox = publicFunctions.getRefToDatabase(DatabaseType.TYPE_MESSAGES).child(publicFunctions.getCurrentUID()).child(otherPersonUID);
            otherInbox = publicFunctions.getRefToDatabase(DatabaseType.TYPE_MESSAGES).child(otherPersonUID).child(publicFunctions.getCurrentUID());
            btnSendNewMessage.setOnClickListener(sendMessageListener);


            publicFunctions.getRefToDatabase(DatabaseType.TYPE_MESSAGES).child(senderIDs[0]).orderByKey()
                    .equalTo(publicFunctions.getCurrentUID()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    DataSnapshot ds = dataSnapshot.child(publicFunctions.getCurrentUID());
                    long a = ds.getChildrenCount();
                    Iterable<DataSnapshot> it = ds.getChildren();
                    for (DataSnapshot d : it) {
                        String message = d.getValue(String.class);
                        String messageDate = d.getKey();
                        String messageSender = publicFunctions.getCurrentUID();
                        convo.addMessage(message, messageSender, messageDate);
                    }

                    thisConvo = convo.getMessagesOrderedByTime();

                    for (int e = 0; e < thisConvo.length; e++) {
                        //Write code to display each message to the user here
                        showMessage(thisConvo[e]);
                    }
                    doneLoading = true;
                    setNewMessageListeners();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }


        txtNewMessage.setOnClickListener(new View.OnClickListener()
             {
                 @Override
                 public void onClick(View v) {
                     if (!txtNewMessageClicked) {
                         txtNewMessage.setText("");
                     }
                 }
             }
        );

    }
    public void showMessage(Message m)
    {
        if (lastMessageShownTime == 0)
        {
            lastMessageShownTime = Calendar.getInstance().getTimeInMillis() - 300;
        }
        long currentTime = Calendar.getInstance().getTimeInMillis();
        long difference = currentTime - lastMessageShownTime;
        if ((difference >= 300) || !doneLoading)
        {
            RelativeLayout layout = new RelativeLayout(messagesActivity.this);
            layout.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT));

            String thisID = publicFunctions.getCurrentUID();
            TextView messageSender = new TextView(messagesActivity.this);
            if (m.getSenderID() == publicFunctions.getCurrentUID())
            {
                messageSender.setText("You: " + m.getMessageContents());
                layout.setBackgroundColor(publicFunctions.cliprColors.LIGHT_BLUE);

                messageSender.setTranslationX(0);

            }
            else
            {
                layout.setBackgroundColor(publicFunctions.cliprColors.LIGHT_RED);
                messageSender.setText(otherPersonUsername+": " + m.getMessageContents());
                messageSender.setTranslationX(0);
            }

            layout.addView(messageSender);
            layoutConvo.addView(layout);
        }
        else
        {
            int a = 0;
        }
        lastMessageShownTime = currentTime;






    }
    public void setNewMessageListeners()
    {
    timeBeforeListenerSet = Calendar.getInstance().getTimeInMillis();
        if ((myInbox != null) && (otherInbox != null))
        {
            myInbox.addChildEventListener(lMessageToUser);
            otherInbox.addChildEventListener(lMessageFromUser);
        }
        messageChildListenersSet = true;
    }
    public void unsetNewMessageListeners()
    {
        messageChildListenersSet = false;
        if ((myInbox != null ) && (otherInbox != null))
        {
            myInbox.removeEventListener(lMessageToUser);
            otherInbox.removeEventListener(lMessageFromUser);
        }


    }

}
