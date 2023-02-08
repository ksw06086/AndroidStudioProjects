package kr.ac.yuhan.sun.mobile_2022_11_29;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class MyTabFragment extends Fragment {
    String tabName;
    Context context;

    public MyTabFragment(Context context)
    {
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle data = getArguments();
        tabName = data.getString("tabName");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LinearLayout layout = new LinearLayout(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(params);

        if(tabName.equals("첫번째탭")){
            layout.setBackgroundColor(Color.RED);
        }
        if(tabName.equals("두번째탭")){
            layout.setBackgroundColor(Color.GREEN);
        }
        if(tabName.equals("세번째탭")){
            layout.setBackgroundColor(Color.BLUE);
        }

        return layout;
    }
}