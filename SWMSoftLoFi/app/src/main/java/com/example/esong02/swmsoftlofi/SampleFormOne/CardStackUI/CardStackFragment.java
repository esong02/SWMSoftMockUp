package com.example.esong02.swmsoftlofi.SampleFormOne.CardStackUI;


import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.esong02.swmsoftlofi.R;

/**
 * Created by esong02 on 2017-07-19.
 */

public class CardStackFragment extends Fragment {

    private Button rating1;
    private Button rating2;
    private Button rating3;
    private Button rating4;
    private Button rating5;
    private ImageButton photoBtn;
    private Bundle b;

    //saved data
    public TextView descriptionTxt;
    public EditText cText;
    public int rating;

    public void clearAllRatingBtn(){
        rating1.setTextColor(Color.BLACK);
        rating2.setTextColor(Color.BLACK);
        rating3.setTextColor(Color.BLACK);
        rating4.setTextColor(Color.BLACK);
        rating5.setTextColor(Color.BLACK);

    }

    public void clearPhotoBtn(){
        photoBtn.setImageResource(R.drawable.ic_photo_camera_white_48dp);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.task_item, null);

        b = getArguments();

        String description = b.getString("Description");
        rating = b.getInt("Rating");
        String comments = b.getString("Comments");
        boolean hasPhoto = b.getBoolean("HasPhoto");

        descriptionTxt = (TextView) rootView.findViewById(R.id.componentDescription);
        cText = (EditText) rootView.findViewById(R.id.commentText);
        ImageButton helpBtn = (ImageButton) rootView.findViewById(R.id.helpButton);

        descriptionTxt.setText(description);

        // add button listener for Help Button
        helpBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                final Dialog alertDialog = new Dialog(getContext());
                alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                alertDialog.setContentView(R.layout.condition_dialog);
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                final ImageButton subCBtn = (ImageButton)alertDialog.findViewById(R.id.subCancelButton);
                alertDialog.show();

                subCBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });

            }
        });

        rating1 = (Button) rootView.findViewById(R.id.rButton1);
        rating2 = (Button) rootView.findViewById(R.id.rButton2);
        rating3 = (Button) rootView.findViewById(R.id.rButton3);
        rating4 = (Button) rootView.findViewById(R.id.rButton4);
        rating5 = (Button) rootView.findViewById(R.id.rButton5);
        photoBtn = (ImageButton) rootView.findViewById(R.id.photoButton);

        //reset field
        clearAllRatingBtn();
        photoBtn.setImageResource(R.drawable.ic_photo_camera_white_48dp);

        if (rating == 1){
            rating1.setTextColor(Color.BLUE);
        }else if (rating == 2){
            rating2.setTextColor(Color.BLUE);
        }else if (rating == 3){
            rating3.setTextColor(Color.BLUE);
        }else if (rating == 4){
            rating4.setTextColor(Color.BLUE);
        }else if (rating == 5){
            rating5.setTextColor(Color.BLUE);
        }

        if (hasPhoto){
            photoBtn.setImageResource(R.drawable.ic_photo_camera_black_48dp);
        }

        rating1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAllRatingBtn();
                clearPhotoBtn();
                rating1.setTextColor(Color.BLUE);
            }
        });
        rating2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAllRatingBtn();
                clearPhotoBtn();
                rating2.setTextColor(Color.BLUE);
            }
        });
        rating3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAllRatingBtn();
                clearPhotoBtn();
                rating3.setTextColor(Color.BLUE);
            }
        });
        rating4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAllRatingBtn();
                clearPhotoBtn();
                rating4.setTextColor(Color.BLUE);
            }
        });
        rating5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAllRatingBtn();
                clearPhotoBtn();
                rating5.setTextColor(Color.BLUE);
            }
        });

        photoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                photoBtn.setImageResource(R.drawable.ic_photo_camera_black_48dp);
                Toast.makeText(getContext(),"Picture Taken!",Toast.LENGTH_SHORT).show();

            }
        });

        return rootView;
    }
}

