package net.syynclab.acaciahealth.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;
import net.syynclab.acaciahealth.R;

public class SignUp extends AppCompatActivity {

    EditText editTextPhoneNumber;
    EditText editTextEmail;
    EditText editTextName;
    EditText editTextPassword;
    Button btnSignUp;

    ProgressDialog progressDialog;

    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //InitializeViews
        editTextEmail = (MaterialEditText) findViewById(R.id.editTextEmail);
        editTextPhoneNumber = (MaterialEditText) findViewById(R.id.editTextPhoneNumber);
        editTextName = (MaterialEditText) findViewById(R.id.editTextName);
        editTextPassword = (MaterialEditText) findViewById(R.id.editTextPassword);

        //InitializeButton
        btnSignUp = (Button) findViewById(R.id.btnSignUpUser);

        //Initialize ProgressDialog
        progressDialog = new ProgressDialog(this);


        //Initialize Firebase
        firebaseAuth = FirebaseAuth.getInstance();


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = editTextEmail.getText().toString().trim();
                final String phone = editTextPhoneNumber.getText().toString().trim();
                final String name = editTextName.getText().toString().trim();
                final String password = editTextPassword.getText().toString().trim();

                registerUser(email, phone, name, password);
            }
        });
    }

    private void registerUser(final String email, final String phone, final String name, final String password) {
        if (TextUtils.isEmpty(email)){
            Toast.makeText(SignUp.this, "Please enter email", Toast.LENGTH_SHORT).show();
        }

        if (TextUtils.isEmpty(phone)){
            Toast.makeText(SignUp.this, "Please enter phone number", Toast.LENGTH_SHORT).show();
        }

        if (TextUtils.isEmpty(name)){
            Toast.makeText(SignUp.this, "Please enter name", Toast.LENGTH_SHORT).show();
        }

        if (TextUtils.isEmpty(password)){
            Toast.makeText(SignUp.this, "Password can't be empty", Toast.LENGTH_SHORT).show();
        }

        else {
            progressDialog.setMessage("Registering User...");
            progressDialog.show();

            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                String currentOnlineUser = firebaseAuth.getCurrentUser().getUid();
                                databaseReference = FirebaseDatabase.getInstance().getReference().child("User").child(currentOnlineUser);

                                databaseReference.child("Name").setValue(name);
                                databaseReference.child("Email").setValue(email);
                                databaseReference.child("Phone").setValue(phone);
                                databaseReference.child("Password").setValue(password);
                                databaseReference.child("Balance").setValue("0.00");
                                databaseReference.child("PolicyNumber").setValue("0000");
                                databaseReference.child("UserPicture").setValue("default_image")
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()){
                                                    Intent mainIntent = new Intent(SignUp.this, Dashboard.class);
                                                    mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                    startActivity(mainIntent);
                                                    finish();
                                                }
                                            }
                                        });
                            }
                            else{
                                Toast.makeText(SignUp.this, "An Error Occured", Toast.LENGTH_SHORT).show();
                            }
                            progressDialog.dismiss();
                        }
                    });
        }

    }
}
