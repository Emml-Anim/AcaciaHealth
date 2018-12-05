package net.syynclab.acaciahealth.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import net.syynclab.acaciahealth.model.User;
import net.syynclab.acaciahealth.R;

public class SignIn extends AppCompatActivity {

    EditText editTextPhoneNumber;
    EditText editTextPassword;
    Button btnSignIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        editTextPhoneNumber = (MaterialEditText) findViewById(R.id.editTextPhoneNumber);
        editTextPassword = (MaterialEditText) findViewById(R.id.editTextPassword);
        btnSignIn = (Button) findViewById(R.id.btnSignInUser);

        //Initialize Firebase
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = firebaseDatabase.getReference("User");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog progressDialog = new ProgressDialog(SignIn.this);
                progressDialog.setMessage("Please wait...");
                progressDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        //Check if the user exists in Database
                        if (dataSnapshot.child(editTextPhoneNumber.getText().toString()).exists()) {


                            // Get User Information
                            User user = dataSnapshot.child(editTextPhoneNumber.getText().toString()).getValue(User.class);
                            if (user.getPassword().equals(editTextPassword.getText().toString())) {
                                {
                                    Intent dashboard = new Intent(SignIn.this, GreaterAccra.class);
                                    net.syynclab.acaciahealth.common.Common.currentUser = user;
                                    startActivity(dashboard);
                                    finish();
                                }

                            } else {
                                Toast.makeText(SignIn.this, "Sign in failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            progressDialog.dismiss();
                            Toast.makeText(SignIn.this, "User does not exist", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
    }
}
