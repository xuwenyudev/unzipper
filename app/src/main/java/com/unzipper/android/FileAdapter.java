package com.unzipper.android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 23229 on 2020/2/12.
 */

public class FileAdapter extends ArrayAdapter<File> {
    private int resourceId;

    public FileAdapter(Context context, int textViewResourceId, List<File> objects){
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        File file = getItem(position);
        View view;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        }
        else{
            view = convertView;
        }
        ImageView fileIcon = (ImageView)view.findViewById(R.id.file_icon);
        TextView fileName = (TextView)view.findViewById(R.id.file_name);
        fileIcon.setImageResource(file.getIconId());
        fileName.setText(file.getName());
        return view;
    }
}
