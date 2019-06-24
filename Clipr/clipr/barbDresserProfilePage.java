package com.clipr.clipr;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class barbDresserProfilePage extends AppCompatActivity {
    barbDresserProfile thisProfile;

    ImageView imgProfilePhoto;
    TextView txtName;
    TextView txtDescription;
    TextView txtRating;

    Button btnViewComments;
    Button btnSendMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stylist_profile);

        imgProfilePhoto = (ImageView) findViewById(R.id.imgProfilePic);
        txtName = (TextView) findViewById(R.id.lblName);
        txtDescription = (TextView) findViewById(R.id.lblDescription);
        txtRating = (TextView) findViewById(R.id.lblRating);

        btnViewComments = (Button) findViewById(R.id.btnViewReviews);
        btnSendMessage = (Button) findViewById(R.id.btnSendMessage);

        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            String[] passedData = extras.getStringArray("profileInfo");
            thisProfile = new barbDresserProfile(passedData[1],passedData[0],passedData[2],
                    Integer.parseInt(passedData[5]),Integer.parseInt(passedData[4]),Integer.parseInt(passedData[3]));
            txtName.setText(thisProfile.getName());
            txtDescription.setText(thisProfile.getDescription());
            txtRating.setText(thisProfile.getCurrentRating().toString());
        }

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference ref = storage.getReference().child(publicFunctions.StorageFolderNames.PROFILE_PICS).child(thisProfile.getAttachedUID()+".jpg");

        //imgProfilePhoto.setImageURI(android.net.Uri.parse("gs://big-bliss-186118.appspot.com/profilePics/User_1.jpg"));
        File thisProfilePhoto = null;

        try {
            thisProfilePhoto = File.createTempFile("profilePhoto","jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }

        ref.getFile(thisProfilePhoto).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });


        ref.getBytes(1024*1024).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap b = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                imgProfilePhoto.setImageBitmap(b);
                imgProfilePhoto.setX(0);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                int x = 0;
            }
        });


        btnViewComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent commentPage = new Intent(barbDresserProfilePage.this, com.clipr.clipr.commentPage.class);
                commentPage.putExtra("profileID",thisProfile.getAttachedUID());

                startActivity(commentPage);
            }
        });

        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
