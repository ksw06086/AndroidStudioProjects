package kr.ac.yuhan.sun.mobile_2022_11_29;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.SpinnerAdapter;

public class MainActivity extends AppCompatActivity implements ActionBar.TabListener {
    ActionBar.Tab tab1, tab2, tab3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar bar = getSupportActionBar();
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        tab1 = bar.newTab();
        tab1.setText("첫번째탭");
        tab1.setTabListener(this);
        bar.addTab(tab1);

        tab2 = bar.newTab();
        tab2.setText("두번째탭");
        tab2.setTabListener(this);
        bar.addTab(tab2);

        tab3 = bar.newTab();
        tab3.setText("세번째탭");
        tab3.setTabListener(this);
        bar.addTab(tab3);
    }

    MyTabFragment[] myFrags = new MyTabFragment[3];

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        MyTabFragment myTabFrag = null;
        if(myFrags[tab.getPosition()] == null){
            myTabFrag = new MyTabFragment(this);
            Bundle data = new Bundle();
            data.putString("tabName",tab.getText().toString());
            myTabFrag.setArguments(data);
            myFrags[tab.getPosition()] = myTabFrag;
        } else {
            myTabFrag = myFrags[tab.getPosition()];

        }

        ft.replace(android.R.id.content, myTabFrag);
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
}