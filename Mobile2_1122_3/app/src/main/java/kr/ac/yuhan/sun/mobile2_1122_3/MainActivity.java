package kr.ac.yuhan.sun.mobile2_1122_3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TabActivity;
import android.os.Bundle;
import android.widget.TabHost;

@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabHost tabHost = getTabHost();

        TabHost.TabSpec tab1 = tabHost.newTabSpec("t1").setIndicator("하울");
        tab1.setContent(R.id.l1);
        tabHost.addTab(tab1);

        TabHost.TabSpec tab2 = tabHost.newTabSpec("t2").setIndicator("피카츄");
        tab2.setContent(R.id.l2);
        tabHost.addTab(tab2);

        TabHost.TabSpec tab3 = tabHost.newTabSpec("t3").setIndicator("산");
        tab3.setContent(R.id.l3);
        tabHost.addTab(tab3);

        tabHost.setCurrentTab(1);

    }
}