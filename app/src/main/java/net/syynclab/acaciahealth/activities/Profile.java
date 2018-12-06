package net.syynclab.acaciahealth.activities;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import net.syynclab.acaciahealth.R;
import net.syynclab.acaciahealth.common.Common;
import net.syynclab.acaciahealth.model.User;

public class Profile extends AppCompatActivity {

    ImageView userImage;
    TextView userName;
    TextView userBalance;
    TextView userPolicyNumber;
    TextView userEmail;
    TextView userPhoneNumber;

    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //Initialize Firebase
        firebaseAuth = FirebaseAuth.getInstance();
        String currentOnlineUser = firebaseAuth.getCurrentUser().getUid();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("User").child(currentOnlineUser);

        initializeViews();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("Name").getValue().toString();
                String email = dataSnapshot.child("Email").getValue().toString();
                String balance = dataSnapshot.child("Balance").getValue().toString();
                String policynumber = dataSnapshot.child("PolicyNumber").getValue().toString();
                String phone = dataSnapshot.child("Phone").getValue().toString();

                userName.setText(name);
                userEmail.setText(email);
                userBalance.setText(balance);
                userPolicyNumber.setText(policynumber);
                userPhoneNumber.setText(phone);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    private void initializeViews() {
        userImage = (ImageView) findViewById(R.id.userImage);
        userName = (TextView) findViewById(R.id.userName);
        userBalance = (TextView) findViewById(R.id.userBalance);
        userPolicyNumber = (TextView) findViewById(R.id.userPolicyNumber);
        userEmail = (TextView) findViewById(R.id.userEmail);
        userPhoneNumber = (TextView) findViewById(R.id.userPhoneNumber);

    }
}
