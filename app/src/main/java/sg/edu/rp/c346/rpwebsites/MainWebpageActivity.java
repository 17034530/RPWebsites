package sg.edu.rp.c346.rpwebsites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainWebpageActivity extends AppCompatActivity {
    WebView wvPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_webpage);

        wvPage = findViewById(R.id.webView);
        wvPage.setWebViewClient(new WebViewClient());

        Intent intentReceived = getIntent();
        String url = intentReceived.getStringExtra("URL");
        wvPage.loadUrl(url);
        wvPage.getSettings().setBuiltInZoomControls(true);
        wvPage.getSettings().setAllowFileAccess(false);
    }
}
