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

import com.rengwuxian.materialedittext.MaterialEditText;

import net.syynclab.acaciahealth.R;

public class SignIn extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    EditText editTextEmail;
    EditText editTextPassword;
    Button btnSignIn;

    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        editTextEmail = (MaterialEditText) findViewById(R.id.editTextEmail);
        editTextPassword = (MaterialEditText) findViewById(R.id.editTextPassword);
        btnSignIn = (Button) findViewById(R.id.btnSignInUser);

        progressDialog = new ProgressDialog(this);

        //Initialize Firebase
        firebaseAuth = FirebaseAuth.getInstance();

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                loginUser(email,password);

            }
        });
    }

    private void loginUser(String email, String password) {

        if(TextUtils.isEmpty(email)){
            Toast.makeText(SignIn.this, "Please Enter your Email", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(SignIn.this, "Please Enter your Password", Toast.LENGTH_SHORT).show();
        }
        else{
            progressDialog.setMessage("Logging in User...");
            progressDialog.show();

            firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(SignIn.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Intent mainIntent = new Intent(SignIn.this, Dashboard.class);
                                mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(mainIntent);
                                finish();
                            }
                            else{
                                Toast.makeText(SignIn.this, "Wrong Email or Password...Try again", Toast.LENGTH_SHORT).show();
                            }
                            progressDialog.dismiss();
                        }
                    });

        }

    }
}
