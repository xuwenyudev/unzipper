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

public class FileAdapter extends ArrayAdapter<FileItem> {
    private int resourceId;

    public FileAdapter(Context context, int textViewResourceId, List<FileItem> objects){
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        FileItem file = getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.fileIcon = (ImageView)view.findViewById(R.id.file_icon);
            viewHolder.fileName = (TextView)view.findViewById(R.id.file_name);
            view.setTag(viewHolder);
        }
        else{
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        }
        viewHolder.fileIcon.setImageResource(file.getIconId());
        viewHolder.fileName.setText(file.getName());
        return view;
    }

    class ViewHolder {
        ImageView fileIcon;
        TextView fileName;
    }
}
