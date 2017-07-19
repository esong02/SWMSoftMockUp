package com.example.esong02.swmsoftlofi.SampleForm.CardStackUI;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.esong02.swmsoftlofi.R;

/**
 * Created by esong02 on 2017-07-19.
 */

public class CardStackFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.task_item, null);

        Bundle b = getArguments();

        String description = b.getString("Description");
        int rating = b.getInt("Rating");
        String comments = b.getString("Comments");
        boolean hasPhoto = b.getBoolean("HasPhoto");


        TextView mText = (TextView) rootView.findViewById(R.id.componentDescription);

        mText.setText(description);

        return rootView;
    }
}

