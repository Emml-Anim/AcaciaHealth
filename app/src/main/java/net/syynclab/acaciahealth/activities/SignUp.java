package net.syynclab.acaciahealth.activities;

import android.app.ProgressDialog;
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

public class SignUp extends AppCompatActivity {

    EditText editTextPhoneNumber;
    EditText editTextName;
    EditText editTextPassword;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editTextPhoneNumber = (MaterialEditText) findViewById(R.id.editTextPhoneNumber);
        editTextName = (MaterialEditText) findViewById(R.id.editTextName);
        editTextPassword = (MaterialEditText) findViewById(R.id.editTextPassword);
        btnSignUp = (Button) findViewById(R.id.btnSignUpUser);


        //Initialize Firebase
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = firebaseDatabase.getReference("User");


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog progressDialog = new ProgressDialog(SignUp.this);
                progressDialog.setMessage("Please wait...");
                progressDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //Check if user already exists
                        if(dataSnapshot.child(editTextPhoneNumber.getText().toString()).exists()){
                            progressDialog.dismiss();
                            Toast.makeText(SignUp.this, "User already exist", Toast.LENGTH_SHORT).show();
                        }else {
                            progressDialog.dismiss();
                            User user = new User(editTextName.getText().toString(), editTextPassword.getText().toString());
                            table_user.child(editTextPhoneNumber.getText().toString()).setValue(user);
                            Toast.makeText(SignUp.this, "Sign up successfully", Toast.LENGTH_SHORT).show();
                            finish();
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
