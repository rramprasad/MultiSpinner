package com.exinnos.multispinner;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.TextViewCompat;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by RAMPRASAD on 7/23/2016.
 */
public class TeamsAdapter extends BaseAdapter implements Filterable{

    private static final String LOG_TAG = TeamsAdapter.class.getSimpleName();
    private ArrayList<Team> teamsArrayList;
    private Context context;
    private ArrayList<Team> filteredData;

    TeamsFilter mFilter = new TeamsFilter();
    //private SparseBooleanArray mSelectedItemIds;

    public TeamsAdapter(Context context, ArrayList<Team> teamsArrayList) {
        this.context = context;
        this.teamsArrayList = teamsArrayList;
        this.filteredData = teamsArrayList;
    }

    @Override
    public int getCount() {

        if(filteredData == null){
            return 0;
        }
        return filteredData.size();
    }


    @Override
    public Team getItem(int position) {
        return filteredData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return filteredData.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder;

        if(convertView == null){
            viewHolder = new ViewHolder();

            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.team_list_item, parent, false);

            viewHolder.teamNameTextView = (TextView) convertView.findViewById(R.id.team_name_textview);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //viewHolder.teamNameTextView.setText(teamsArrayList.get(position).getTeamName());

        Log.d(LOG_TAG,"view string"+getItem(position).getTeamName());
        viewHolder.teamNameTextView.setText(getItem(position).getTeamName());



        /*convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CheckedTextView checkedteamNameTextView = (CheckedTextView) viewHolder.teamNameTextView;
                if((checkedteamNameTextView.isChecked())){

                    checkedteamNameTextView.setChecked(false);
                    //checkedteamNameTextView.setCheckMarkDrawable(null);
                    checkedteamNameTextView.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
                }
                else {
                    checkedteamNameTextView.setChecked(true);
                    //checkedteamNameTextView.setCheckMarkDrawable(R.drawable.ic_tick);
                    checkedteamNameTextView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_tick,0,0,0);
                }
            }
        });*/

        return convertView;
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    private static class ViewHolder {
        private TextView teamNameTextView;
    }

    private class TeamsFilter extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {



            String filterString = charSequence.toString().toLowerCase();

            Log.d(LOG_TAG,"filter string=>"+filterString);

            ArrayList<Team> newList = new ArrayList<>();

            FilterResults filterResults = new FilterResults();


            if(teamsArrayList == null || teamsArrayList.size() <= 0){
                filterResults.values = teamsArrayList;
                filterResults.count = teamsArrayList.size();
            }

            int originalListCount = teamsArrayList.size();

            for (int i = 0; i < originalListCount; i++) {
                if(teamsArrayList.get(i).getTeamName().toLowerCase().contains(filterString)){
                    newList.add(new Team(teamsArrayList.get(i).getId(),teamsArrayList.get(i).getTeamName()));
                    Log.d(LOG_TAG,"newList-"+i+"=>"+teamsArrayList.get(i).getTeamName());
                }
            }

            Log.d(LOG_TAG,"newList.size()"+newList.size());

            filterResults.values = newList;
            filterResults.count = newList.size();

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

            if(filterResults.count <= 0){
                filteredData = teamsArrayList;
            }
            else{
                filteredData = (ArrayList<Team>) filterResults.values;
            }
            notifyDataSetChanged();
        }
    }



   /* public void toggleSelection(int position){
        selectView(position,!mSelectedItemIds.get(position));
    }

    public void selectView(int position,boolean value){
        if(value){
            mSelectedItemIds.put(position,value);
        }
        else {
            mSelectedItemIds.delete(position);
        }
        notifyDataSetChanged();
    }

    public SparseBooleanArray getmSelectedItemIds(){
        return mSelectedItemIds;
    }*/
}
