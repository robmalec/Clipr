package com.clipr.clipr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.security.cert.TrustAnchor;

public class mainNavigationPage extends AppCompatActivity {
    Button btnBarbDresserSearch;
    Button btnSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_navigation_page);
        final messageInbox inbox = new messageInbox();

        //TODO: Close the MainActivity

        btnBarbDresserSearch = (Button) findViewById(R.id.btnStylistSearch);
        btnBarbDresserSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mainNavigationPage.this,BarbDresserSearch.class));
            }
        });

        btnSettings = (Button) findViewById(R.id.btnAccountSettings);
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mainNavigationPage.this,editAccountSettings.class));
            }
        });

        final Button btnMessageCenter = (Button) findViewById(R.id.btnMessageCenter);
        btnMessageCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent messageView = new Intent(mainNavigationPage.this,messageCenter.class);
                messageView.putExtra("newMessageContents",inbox.getMessageContentsArray());
                messageView.putExtra("newMessageSenders",inbox.getMessageSenderArray());
                messageView.putExtra("newMessageDateTimes",inbox.getMessageDateTimeArray());
                startActivity(messageView);
            }
        });

        final String uid = publicFunctions.getPrimaryTestID();//TODO: Switch this to publicFunctions.getCurrentUID()

        //Getting messages
        publicFunctions.getRefToDatabase(DatabaseType.TYPE_MESSAGES).orderByKey().equalTo(publicFunctions.getPrimaryTestID())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        DataSnapshot ds = dataSnapshot.child(uid);

                        int numNewMessages = 0;
                        Iterable<DataSnapshot> messageSenders = ds.getChildren();
                        Iterable<DataSnapshot> newMessagesFromSender = null;
                        for (DataSnapshot sender : messageSenders)
                        {
                            newMessagesFromSender = sender.getChildren();
                            for (DataSnapshot message : newMessagesFromSender)
                            {
                                String strSender = sender.getKey();
                                String strDateTime = message.getKey();
                                String strMessage = message.getValue(String.class);
                                inbox.addMessage(strMessage,strSender,strDateTime);
                                numNewMessages++;
                            }
                        }

                        if (numNewMessages != 0)
                        {
                            String temp = btnMessageCenter.getText().toString();
                            btnMessageCenter.setText(temp + " " + numNewMessages + " new messages");
                            btnMessageCenter.setVisibility(View.VISIBLE);
                            btnMessageCenter.setEnabled(true);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });





    }
}
