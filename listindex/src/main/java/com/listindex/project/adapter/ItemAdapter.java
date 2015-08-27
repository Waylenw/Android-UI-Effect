package com.listindex.project.adapter;

/**
 * Created by waylenwang on 2015/8/27.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.listindex.project.Item;
import com.listindex.project.R;
import com.listindex.project.view.IndexView;

import java.util.ArrayList;
import java.util.Random;

public class  ItemAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Item> itemArray = new ArrayList<Item>();

    private IndexView indexView=null;

    public ItemAdapter(Context context,ArrayList<Item> itemArray,IndexView indexView) {
        this.context = context;
        this.itemArray=itemArray;
        this.indexView=indexView;
    }

    @Override
    public int getCount() {
        return itemArray.size();
    }

    @Override
    public Object getItem(int position) {
        return itemArray.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.item, null, false);

            holder.picView = (ImageView) convertView.findViewById(R.id.pic);
            holder.nameView = (TextView) convertView
                    .findViewById(R.id.name);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Item item = itemArray.get(position);
        String name = item.getName();
        holder.picView.setImageResource(item.getPic());
        holder.nameView.setText(name);


        if(null!=indexView){
            int pos=itemArray.get(position).getFirstChar()-'a'+1;
            indexView.changeTextViewState(pos,true);
        }
        return convertView;
    }

    private class ViewHolder {
        ImageView picView;
        TextView nameView;
}

}