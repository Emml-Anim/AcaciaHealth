package net.syynclab.acaciahealth.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import net.syynclab.acaciahealth.R;

public class Claims extends AppCompatActivity {

    FloatingActionMenu claims_floating_action_menu;
    FloatingActionButton claims_upload;

    EditText editTextName;
    EditText editTextEmail;
    EditText editTextPolicyNumber;
    EditText editTextPhoneNumber;
    EditText editTextAddress;
    EditText editTextClaimDetails;

    Button btnMakeClaim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claims);

        //Toolbar
        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Initialize Views
        initViews();

        claims_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void initViews() {
        //Floating action bar
        claims_floating_action_menu = (FloatingActionMenu) findViewById(R.id.claims_floating_action_menu);
        claims_upload = (FloatingActionButton) findViewById(R.id.claims_upload);

        //EditText
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextEmail = (EditText)findViewById(R.id.editTextEmail);
        editTextPolicyNumber = (EditText)findViewById(R.id.editTextPolicyNumber);
        editTextPhoneNumber = (EditText)findViewById(R.id.editTextPhoneNumber);
        editTextAddress = (EditText)findViewById(R.id.editTextAddress);
        editTextClaimDetails = (EditText)findViewById(R.id.editTextClaimDetails);

        //Button
        btnMakeClaim = (Button) findViewById(R.id.btnMakeClaim);
    }

}
