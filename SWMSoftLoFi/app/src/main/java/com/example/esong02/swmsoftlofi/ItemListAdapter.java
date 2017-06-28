package com.example.esong02.swmsoftlofi;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.support.design.widget.BottomSheetDialog;
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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

/**
 * Created by esong02 on 2017-06-12.
 */

public class ItemListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<String> listDataHeader;
    private HashMap<String,List<Item>> listHashMap;
    private static boolean grey = false;
    private ImageView completeIcn;

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
        //completeIcn.setBackgroundResource(R.color.transparent);

        if (checkComplete(headerTitle)){
            //Log.d("If Name exists: ",iTask.getName());
            //Toast.makeText(context,"Task Complete!",Toast.LENGTH_SHORT).show();
            //Log.d("Group Check: ",headerTitle);
            completeIcn.setBackgroundResource(R.drawable.green_button);
        }else{
            completeIcn.setBackgroundResource(R.color.transparent);
        }

        //convertView.setBackgroundResource(R.drawable.info_background);
        return convertView;
    }

    public void clearRatingBtn(View r1, View r2, View r3, View r4){
        Button b1 = (Button) r1;
        Button b2 = (Button) r2;
        Button b3 = (Button) r3;
        Button b4 = (Button) r4;

        b1.setBackgroundResource(R.drawable.rating_button);
        b2.setBackgroundResource(R.drawable.rating_button);
        b3.setBackgroundResource(R.drawable.rating_button);
        b4.setBackgroundResource(R.drawable.rating_button);
    }

    public void clearAllRatingBtn(View r1, View r2, View r3, View r4, View r5){
        Button b1 = (Button) r1;
        Button b2 = (Button) r2;
        Button b3 = (Button) r3;
        Button b4 = (Button) r4;
        Button b5 = (Button) r5;

        b1.setBackgroundResource(R.drawable.rating_button);
        b2.setBackgroundResource(R.drawable.rating_button);
        b3.setBackgroundResource(R.drawable.rating_button);
        b4.setBackgroundResource(R.drawable.rating_button);
        b5.setBackgroundResource(R.drawable.rating_button);
    }

    public void clearPhotoBtn(View v, Object object){
        ImageButton i1 = (ImageButton) v;
        Item iTask = (Item) object;
        iTask.setPhoto(false);
        i1.setBackgroundResource(R.drawable.rating_button);
    }

    public boolean checkComplete(String gName){
        //Log.d("If Name exists: ",gName);

        List<Item> g = listHashMap.get(gName);
        for (int i = 0; i < g.size(); i++){
            if(g.get(i).isComplete() == false){
                return false;
            }
        }
        return true;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.task_item,null);
        }


        /*
        if (grey == false) {
            convertView.setBackgroundColor(Color.argb(100, 255, 255, 255));
            grey = true;
        }else{
            convertView.setBackgroundColor(Color.argb(25, 0, 0, 0));
            grey = false;
        }
        */

        final Item iTask = (Item) getChild(groupPosition,childPosition);

        final TextView description = (TextView)convertView.findViewById(R.id.componentDescription);
        EditText cText = (EditText) convertView.findViewById(R.id.commentText);
        ImageButton helpBtn = (ImageButton) convertView.findViewById(R.id.helpButton);

        description.setText((String)iTask.getDescription());

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

        final Button rating1 = (Button) convertView.findViewById(R.id.rButton1);
        final Button rating2 = (Button) convertView.findViewById(R.id.rButton2);
        final Button rating3 = (Button) convertView.findViewById(R.id.rButton3);
        final Button rating4 = (Button) convertView.findViewById(R.id.rButton4);
        final Button rating5 = (Button) convertView.findViewById(R.id.rButton5);
        final ImageButton photoBtn = (ImageButton) convertView.findViewById(R.id.photoButton);

        //reset field
        clearAllRatingBtn(rating1, rating2, rating3, rating4, rating5);
        //clearPhotoBtn(photoBtn,iTask);
        photoBtn.setBackgroundResource(R.drawable.rating_button);
        Log.d("Current btn mapped to:", iTask.getName());
        //completeIcn.setBackgroundResource(R.color.transparent);

        if (iTask.getRating() == 1){
            rating1.setBackgroundResource(R.drawable.green_button);
        }else if (iTask.getRating() == 2){
            rating2.setBackgroundResource(R.drawable.green_button);
        }else if (iTask.getRating() == 3){
            rating3.setBackgroundResource(R.drawable.green_button);
        }else if (iTask.getRating() == 4){
            rating4.setBackgroundResource(R.drawable.green_button);
        }else if (iTask.getRating() == 5){
            rating5.setBackgroundResource(R.drawable.green_button);
        }

        if (iTask.getPhoto()){
            photoBtn.setBackgroundResource(R.drawable.green_button);
        }

        rating1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iTask.setRating(1);
                rating1.setBackgroundResource(R.drawable.green_button);
                clearRatingBtn(rating2,rating3,rating4, rating5);
                clearPhotoBtn(photoBtn,iTask);
            }
        });
        rating2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iTask.setRating(2);
                rating2.setBackgroundResource(R.drawable.green_button);
                clearRatingBtn(rating1,rating3,rating4, rating5);
                clearPhotoBtn(photoBtn,iTask);
            }
        });
        rating3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iTask.setRating(3);
                rating3.setBackgroundResource(R.drawable.green_button);
                clearRatingBtn(rating1,rating2,rating4, rating5);
                clearPhotoBtn(photoBtn,iTask);
            }
        });
        rating4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iTask.setRating(4);
                rating4.setBackgroundResource(R.drawable.green_button);
                clearRatingBtn(rating1,rating2,rating3, rating5);
                clearPhotoBtn(photoBtn,iTask);
            }
        });
        rating5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iTask.setRating(5);
                rating5.setBackgroundResource(R.drawable.green_button);
                clearRatingBtn(rating1,rating2,rating3, rating4);
                clearPhotoBtn(photoBtn,iTask);
            }
        });

        photoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iTask.setPhoto(true);
                photoBtn.setBackgroundResource(R.drawable.green_button);
                Toast.makeText(context,"Picture Taken!",Toast.LENGTH_SHORT).show();
                //List<Item> g = listHashMap.get(iTask.getName());
                //Toast.makeText(context,iTask.getName() + g.size(),Toast.LENGTH_SHORT).show();

                if (iTask.getRating() == 0){
                    Toast.makeText(context,"Select a rating . . ",Toast.LENGTH_SHORT).show();
                }else{
                    iTask.setComplete(true);
                    if (checkComplete(iTask.getName())){
                        //Log.d("If Name exists: ",iTask.getName());
                        Toast.makeText(context,"Task Complete!",Toast.LENGTH_SHORT).show();
                        //completeIcn.setBackgroundResource(R.drawable.green_button);
                    }
                }
            }
        });

        //convertView.setBackgroundResource(R.drawable.info_background);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
