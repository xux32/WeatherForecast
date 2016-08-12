package com.example.xux32.weatherfroecast.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;

import com.example.xux32.weatherfroecast.R;
import com.example.xux32.weatherfroecast.data.WeatherInfo;
import com.google.gson.Gson;

/**
 * Created by xux32 on 2016/8/10.
 */
public class WeatherOtherAdapter implements ExpandableListAdapter {


    WeatherInfo info;
    TextView mText;
    TextView mTextInfo;
    Context context;

    public WeatherOtherAdapter(WeatherInfo str, TextView mText, TextView mTextInfo, Context context) {
        this.mText = mText;
        this.context = context;
        this.mTextInfo = mTextInfo;
       this.info = str;
    }

    @Override
    public int getChildrenCount(int i) {
        return 1;
    }

    @Override
    public int getGroupCount() {
        return info.getResult().get(0).getFuture().size() - 1;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1 + 1;
    }

    @Override
    public long getGroupId(int i) {
        return i + 1;
    }

    @Override
    public Object getChild(int i, int i1) {
        return info.getResult().get(0).getFuture().get(i1 + 1);
    }

    @Override
    public Object getGroup(int i) {
        return info.getResult().get(0).getFuture().get(i + 1);
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {

        VewChildHolder childHolder;
        if (view == null) {
            childHolder = new VewChildHolder();
            view = LayoutInflater.from(context).inflate(R.layout.item_weather_other_child, null);
            childHolder.mTextChild = (TextView) view.findViewById(R.id.id_text);
            view.setTag(childHolder);
        } else {
            childHolder = (VewChildHolder) view.getTag();
        }
        //if(info.getResult().get(0).getFuture().get(i).getWeek().equals("今天")) {

        //}//else{
        childHolder.mTextChild.setText(info.getResult().get(0).getFuture().get(i + 1).toString());
        //}

        return view;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {

        ViewGroupHolder groupHolder;
        if (view == null) {
            groupHolder = new ViewGroupHolder();
            view = LayoutInflater.from(context).inflate(R.layout.item_weather_other, null);
            groupHolder.mTextGroup = (TextView) view.findViewById(R.id.id_list);
            view.setTag(groupHolder);
        } else {
            groupHolder = (ViewGroupHolder) view.getTag();
        }

        // if(info.getResult().get(0).getFuture().get(i).getWeek().equals("今天")) {
        mText.setText(info.getResult().get(0).getFuture().get(0).getDate());
        mTextInfo.setText(info.getResult().get(0).getFuture().get(0).toString());
        // }//else {
        groupHolder.mTextGroup.setText(info.getResult().get(0).getFuture().get(i + 1).getDate());
        // }

        return view;
    }

    @Override
    public long getCombinedChildId(long l, long l1) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long l) {
        return 0;
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupCollapsed(int i) {

    }

    @Override
    public void onGroupExpanded(int i) {

    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    static class ViewGroupHolder {
        TextView mTextGroup;
    }

    static class VewChildHolder {
        TextView mTextChild;
    }
}
