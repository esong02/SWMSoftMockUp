package com.example.esong02.swmsoftlofi.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

/**
 * Created by esong02 on 2017-06-12.
 */

import com.example.esong02.swmsoftlofi.Models.*;
import com.example.esong02.swmsoftlofi.R;

public class ItemListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<String> listDataHeader;
    private HashMap<String,List<Item>> listHashMap;
    private static boolean grey = false;
    private ImageView completeIcn;
    private Button rating1;
    private Button rating2;
    private Button rating3;
    private Button rating4;
    private Button rating5;
    private ImageButton photoBtn;
    //private Item iTask;

    public ItemListAdapter(Context context, List<String> listDataHeader, HashMap<String, List<Item>> listHashMap) {
        this.context = context;
        this.listDataHeader = listDataHeader;
        this.listHashMap = listHashMap;
    }

    @Override
    public int getGroupCount() {
        return listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listHashMap.get(listDataHeader.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listDataHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listHashMap.get(listDataHeader.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.component_item,null);
        }

        TextView lblListHeader = (TextView) convertView.findViewById(R.id.componentName);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);
        completeIcn = (ImageView) convertView.findViewById(R.id.completeIcon);

        if (checkComplete(headerTitle)){
            completeIcn.setBackgroundResource(R.drawable.green_button);
        }else{
            completeIcn.setBackgroundResource(R.color.transparent);
        }

        return convertView;
    }

    public void clearAllRatingBtn(){

        /*
        b1.setBackgroundResource(R.drawable.rating_button);
        b2.setBackgroundResource(R.drawable.rating_button);
        b3.setBackgroundResource(R.drawable.rating_button);
        b4.setBackgroundResource(R.drawable.rating_button);
        b5.setBackgroundResource(R.drawable.rating_button);
        */
        rating1.setTextColor(Color.BLACK);
        rating2.setTextColor(Color.BLACK);
        rating3.setTextColor(Color.BLACK);
        rating4.setTextColor(Color.BLACK);
        rating5.setTextColor(Color.BLACK);

    }

    public void clearPhotoBtn(Item iTask){
        /*
        ImageButton i1 = (ImageButton) v;
        Item iTask = (Item) object;
        */
        //i1.setBackgroundResource(R.drawable.rating_button);
        iTask.setPhoto(false);

        photoBtn.setImageResource(R.drawable.ic_photo_camera_white_48dp);
    }

    public boolean checkComplete(String gName){

        List<Item> g = listHashMap.get(gName);
        for (int i = 0; i < g.size(); i++){
            if(g.get(i).isComplete() == false){
                return false;
            }
        }
        return true;
    }

    public void btnEnable(boolean isTrue){
        photoBtn.setEnabled(isTrue);
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        //Log.d("CV","Child View"+childPosition);

        final Item iTask = (Item) getChild(groupPosition,childPosition);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.task_item, null);

            final TextView description = (TextView)convertView.findViewById(R.id.componentDescription);
            EditText cText = (EditText) convertView.findViewById(R.id.commentText);
            ImageButton helpBtn = (ImageButton) convertView.findViewById(R.id.helpButton);

            description.setText(iTask.getDescription());

            // add button listener for Help Button
            helpBtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {

                    final Dialog alertDialog = new Dialog(context);
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

            rating1 = (Button) convertView.findViewById(R.id.rButton1);
            rating2 = (Button) convertView.findViewById(R.id.rButton2);
            rating3 = (Button) convertView.findViewById(R.id.rButton3);
            rating4 = (Button) convertView.findViewById(R.id.rButton4);
            rating5 = (Button) convertView.findViewById(R.id.rButton5);
            photoBtn = (ImageButton) convertView.findViewById(R.id.photoButton);

            //reset field
            clearAllRatingBtn();
            photoBtn.setImageResource(R.drawable.ic_photo_camera_white_48dp);
            //photoBtn.setBackgroundResource(R.drawable.circle_button);

            if (iTask.getRating() == 1){
                //rating1.setBackgroundResource(R.drawable.green_button);
                rating1.setTextColor(Color.BLUE);
            }else if (iTask.getRating() == 2){
                //rating2.setBackgroundResource(R.drawable.green_button);
                rating2.setTextColor(Color.BLUE);
            }else if (iTask.getRating() == 3){
                //rating3.setBackgroundResource(R.drawable.green_button);
                rating3.setTextColor(Color.BLUE);
            }else if (iTask.getRating() == 4){
                //rating4.setBackgroundResource(R.drawable.green_button);
                rating4.setTextColor(Color.BLUE);
            }else if (iTask.getRating() == 5){
                //rating5.setBackgroundResource(R.drawable.green_button);
                rating5.setTextColor(Color.BLUE);
            }

            if (iTask.confirmPhoto()){
                //photoBtn.setBackgroundResource(R.drawable.green_button);
                photoBtn.setImageResource(R.drawable.ic_photo_camera_black_48dp);
            }

            rating1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("Rating","Position "+childPosition);
                    iTask.setRating(1);
                    //rating1.setBackgroundResource(R.drawable.green_button);
                    clearAllRatingBtn();
                    clearPhotoBtn(iTask);
                    //clearPhotoBtn(photoBtn,iTask);
                    rating1.setTextColor(Color.BLUE);
                }
            });
            rating2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iTask.setRating(2);
                    //rating2.setBackgroundResource(R.drawable.green_button);
                    clearAllRatingBtn();
                    clearPhotoBtn(iTask);
                    //clearPhotoBtn(photoBtn,iTask);
                    rating2.setTextColor(Color.BLUE);
                }
            });
            rating3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iTask.setRating(3);
                    //rating3.setBackgroundResource(R.drawable.green_button);
                    clearAllRatingBtn();
                    clearPhotoBtn(iTask);
                    //clearPhotoBtn(photoBtn,iTask);
                    rating3.setTextColor(Color.BLUE);
                }
            });
            rating4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iTask.setRating(4);
                    clearAllRatingBtn();
                    clearPhotoBtn(iTask);
                    //clearPhotoBtn(photoBtn,iTask);
                    //rating4.setBackgroundResource(R.drawable.green_button);
                    rating4.setTextColor(Color.BLUE);
                }
            });
            rating5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iTask.setRating(5);
                    //rating5.setBackgroundResource(R.drawable.green_button);
                    clearAllRatingBtn();
                    //clearPhotoBtn(photoBtn,iTask);
                    clearPhotoBtn(iTask);
                    rating5.setTextColor(Color.BLUE);
                }
            });

            photoBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iTask.setPhoto(true);
                    Log.d("Picture","Position " + childPosition);
                    //photoBtn.setBackgroundResource(R.drawable.green_button);
                    photoBtn.setImageResource(R.drawable.ic_photo_camera_black_48dp);
                    Toast.makeText(context,"Picture Taken!",Toast.LENGTH_SHORT).show();

                    if (iTask.getRating() == 0){
                        Toast.makeText(context,"Select a rating . . ",Toast.LENGTH_SHORT).show();
                    }else{
                        iTask.setComplete(true);
                        if (checkComplete(iTask.getName())){
                            Toast.makeText(context,"Task Complete!",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });

        }else{
            Log.d("Null","False");
        }

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
