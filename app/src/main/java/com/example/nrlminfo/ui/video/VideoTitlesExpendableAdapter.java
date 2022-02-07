package com.example.nrlminfo.ui.video;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.nrlminfo.R;
import com.example.nrlminfo.ui.video.beans.VideoCategoryBean;
import com.example.nrlminfo.ui.video.beans.VideoDetailsBean;

import java.util.HashMap;
import java.util.List;


public class VideoTitlesExpendableAdapter extends BaseExpandableListAdapter {

    private Context context;
    List<VideoCategoryBean> videoCategoryBeanList;
    HashMap<String, List<VideoDetailsBean>> videoDetailsChildList;

    public VideoTitlesExpendableAdapter(Context context, List<VideoCategoryBean> videoCategoryBeanList,
                                        HashMap<String, List<VideoDetailsBean>> videoDetailsChildList) {
        this.context = context;
        this.videoCategoryBeanList = videoCategoryBeanList;
        this.videoDetailsChildList = videoDetailsChildList;
    }

    @Override
    public int getGroupCount() {
        return this.videoCategoryBeanList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.videoDetailsChildList.get(this.videoCategoryBeanList.get(groupPosition).getTitleName()).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.videoCategoryBeanList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.videoDetailsChildList.get(this.videoCategoryBeanList.get(groupPosition).getTitleName()).get(childPosition);
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

    @Nullable
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, @Nullable View convertView, ViewGroup parent) {
        VideoCategoryBean listTitle = (VideoCategoryBean) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.child_list_view, null);
        }
        TextView listTitleTextView = (TextView) convertView
                .findViewById(R.id.listTitle);
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle.getTitleName());
        return convertView;
    }

    @Nullable
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, @Nullable View convertView, ViewGroup parent) {
        final VideoDetailsBean expandedListText = (VideoDetailsBean) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_item_view, null);
        }
        TextView expandedListTextView = (TextView) convertView
                .findViewById(R.id.expandedListItem);
        expandedListTextView.setText(expandedListText.getVideoTitle());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}

