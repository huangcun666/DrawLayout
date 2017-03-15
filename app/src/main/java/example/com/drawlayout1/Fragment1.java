package example.com.drawlayout1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Adminstrator on 2017/2/17.
 */

public class Fragment1 extends Fragment {
    private TextView tv;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.fragment1,container,false);
        tv= (TextView) view.findViewById(R.id.tv);
        String string=  getArguments().getString("text");
        tv.setText(string);
        return view;
    }
}
