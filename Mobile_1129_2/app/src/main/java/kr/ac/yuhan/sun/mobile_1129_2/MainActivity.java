package kr.ac.yuhan.sun.mobile_1129_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText editURL;
    WebView web1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editURL = findViewById(R.id.edit_url);
        web1 = findViewById(R.id.web1);
        Button btnGo = findViewById(R.id.btn_go);
        Button btnBack = findViewById(R.id.btn_back);
        btnGo.setOnClickListener(btnL);
        btnBack.setOnClickListener(btnL);

        web1.setWebViewClient(new MyWebViewClient());

        WebSettings webSet = web1.getSettings();
        webSet.setBuiltInZoomControls(true);
        webSet.setJavaScriptEnabled(true);

    }

    View.OnClickListener btnL = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_go:
                    web1.loadUrl(editURL.getText().toString());
                    break;
                case R.id.btn_back:
                    web1.goBack();
                    break;
            }
        }
    };

    class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }
    }
}