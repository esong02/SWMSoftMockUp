package com.example.esong02.swmsoftlofi;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.BottomSheetDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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

    public String getDescription(Object task){
        Item iTask = (Item) task;
        return iTask.description;
    }

    public int getRating(Object task){
        Item iTask = (Item) task;
        return iTask.rating;
    }

    public void setRating(Object task, int r){
        Item iTask = (Item) task;
        iTask.rating = r;
    }

    public String getComment(Object task){
        Item iTask = (Item) task;
        return iTask.comments;
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
        //convertView.setBackgroundResource(R.drawable.property_header);

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

    public void clearPhotoBtn(View v, Object object){
        ImageButton i1 = (ImageButton) v;
        Item iTask = (Item) object;
        iTask.setPhoto(false);
        i1.setBackgroundResource(R.drawable.photo_button);
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.task_item,null);

        }

        final Item iTask = (Item) getChild(groupPosition,childPosition);

        final TextView description = (TextView)convertView.findViewById(R.id.componentDescription);
        EditText cText = (EditText) convertView.findViewById(R.id.commentText);
        //final Button ratingBtn = (Button) convertView.findViewById(R.id.rButton);
        //ImageButton commentBtn = (ImageButton) convertView.findViewById(R.id.commentBtn);
        ImageButton helpBtn = (ImageButton) convertView.findViewById(R.id.helpButton);
        boolean hasPhoto;
        int cRating;

        description.setText((String)iTask.getDescription());
        final String commentTxt = iTask.getComments();

        if (!commentTxt.equals("")){
            cText.setText(commentTxt);
        }else{
            cText.setText("Comments");
        }

        // add button listener for Help Button
        helpBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Dialog alertDialog = new Dialog(context);
                alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                alertDialog.setContentView(R.layout.condition_dialog);
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                alertDialog.show();
            }
        });

        description.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                final BottomSheetDialog task_sheet = new BottomSheetDialog(context);
                task_sheet.setContentView(R.layout.task_dialog);
                final Button rating1 = (Button) task_sheet.findViewById(R.id.rButton1);
                final Button rating2 = (Button) task_sheet.findViewById(R.id.rButton2);
                final Button rating3 = (Button) task_sheet.findViewById(R.id.rButton3);
                final Button rating4 = (Button) task_sheet.findViewById(R.id.rButton4);
                final Button rating5 = (Button) task_sheet.findViewById(R.id.rButton5);
                final ImageButton photoBtn = (ImageButton) task_sheet.findViewById(R.id.photoButton);

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
                        Toast.makeText(context,"Take a Picture",Toast.LENGTH_SHORT).show();
                        iTask.setRating(1);
                        rating1.setBackgroundResource(R.drawable.green_button);
                        clearRatingBtn(rating2,rating3,rating4, rating5);
                        clearPhotoBtn(photoBtn,iTask);
                    }
                });
                rating2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context,"Take a Picture",Toast.LENGTH_SHORT).show();
                        iTask.setRating(2);
                        rating2.setBackgroundResource(R.drawable.green_button);
                        clearRatingBtn(rating1,rating3,rating4, rating5);
                        clearPhotoBtn(photoBtn,iTask);
                    }
                });
                rating3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context,"Take a Picture",Toast.LENGTH_SHORT).show();
                        iTask.setRating(3);
                        rating3.setBackgroundResource(R.drawable.green_button);
                        clearRatingBtn(rating1,rating2,rating4, rating5);
                        clearPhotoBtn(photoBtn,iTask);
                    }
                });
                rating4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context,"Take a Picture",Toast.LENGTH_SHORT).show();
                        iTask.setRating(4);
                        rating4.setBackgroundResource(R.drawable.green_button);
                        clearRatingBtn(rating1,rating2,rating3, rating5);
                        clearPhotoBtn(photoBtn,iTask);
                    }
                });
                rating5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context,"Take a Picture",Toast.LENGTH_SHORT).show();
                        iTask.setRating(5);
                        rating5.setBackgroundResource(R.drawable.green_button);
                        clearRatingBtn(rating1,rating2,rating3, rating4);
                        clearPhotoBtn(photoBtn,iTask);
                    }
                });

                photoBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context,"Picture Taken!",Toast.LENGTH_SHORT).show();
                        iTask.setPhoto(true);
                        photoBtn.setBackgroundResource(R.drawable.green_button);

                        if (iTask.getRating() != 0){
                            description.setTextColor(Color.BLACK);
                            description.setTypeface(Typeface.DEFAULT);
                            description.setTextSize(14);
                            task_sheet.dismiss();
                        }else{
                            Toast.makeText(context,"Select a rating . . ",Toast.LENGTH_SHORT).show();
                        }

                    }
                });

                Toast.makeText(context,"Select a rating between 1 to 5",Toast.LENGTH_SHORT).show();
                task_sheet.show();
            }
        });


        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
