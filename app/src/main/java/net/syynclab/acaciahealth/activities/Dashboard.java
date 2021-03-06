package net.syynclab.acaciahealth.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import net.syynclab.acaciahealth.R;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        CardView btnAbout = (CardView) findViewById(R.id.btnAbout);
        CardView faq = (CardView) findViewById(R.id.faq);
        CardView providers = (CardView) findViewById(R.id.providers);

        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent About = new Intent(Dashboard.this, About.class);
                startActivity(About);
            }
        });

        faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent faq = new Intent(Dashboard.this, Profile.class);
                startActivity(faq);
            }
        });

        providers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent providers = new Intent(Dashboard.this, GreaterAccra.class);
                startActivity(providers);
            }
        });
    }
}
