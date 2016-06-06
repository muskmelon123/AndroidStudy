package com.example.lvhang.androidstudyproject.GridView;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lvhang.androidstudyproject.R;

/**
 * Created by lv.hang on 2016/6/4.
 */
public class MainActivity extends AppCompatActivity {

    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gridview_main);

        gridView = (GridView) findViewById(R.id.grid_view);

        GroupInfoAdapter groupInfoAdapter = new GroupInfoAdapter(this, R.layout.grid_view_item);

        for(int i = 0; i <= 20; i ++) {
            groupInfoAdapter.add(new GroupMember(R.drawable.ic_menu_search_holo_light, "" + i));
        }

        gridView.setAdapter(groupInfoAdapter);
    }

    private static class GroupMemberViewHolder {
        private ImageView portrait;
        private TextView name;
    }

    private class GroupMember {
        private int portraitUrl;
        private String name;

        GroupMember(int portraitUrl, String name) {
            this.portraitUrl = portraitUrl;
            this.name = name;
        }
    }

    private class GroupInfoAdapter extends ArrayAdapter<GroupMember> {
        private int resourceId;
        private  LayoutInflater inflater = getLayoutInflater();

        public GroupInfoAdapter(Context context, int resourceId) {
            super(context, resourceId);
            this.resourceId = resourceId;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            GroupMemberViewHolder viewHolder = null;
            GroupMember groupMember = getItem(position);

            if (convertView == null) {
                convertView = inflater.inflate(resourceId, null);
                viewHolder = new GroupMemberViewHolder();
                viewHolder.portrait = (ImageView) convertView.findViewById(R.id.group_member_portrait);
                viewHolder.name = (TextView) convertView.findViewById(R.id.group_member_name);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (GroupMemberViewHolder) convertView.getTag();
            }

            viewHolder.portrait.setImageResource(groupMember.portraitUrl);
            viewHolder.name.setText(groupMember.name);
            return convertView;
        }
    }

}
