package net.syynclab.acaciahealth.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import net.syynclab.acaciahealth.R;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        WebView webView = new WebView(this);
        setContentView(webView);
        webView.loadUrl("http://ahighana.com/about-us/");
    }
}
