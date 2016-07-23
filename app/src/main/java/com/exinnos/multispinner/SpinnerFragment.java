package com.exinnos.multispinner;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SpinnerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SpinnerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SpinnerFragment extends Fragment {
    /*private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;*/

    private OnFragmentInteractionListener mListener;
    private View rootView;
    private SearchView teamsSearchView;
    private PopupWindow popupMessage;
    private ArrayAdapter<String> adapter;

    public SpinnerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SpinnerFragment.
     */
    public static SpinnerFragment newInstance(String param1, String param2) {
        SpinnerFragment fragment = new SpinnerFragment();
        /*Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);*/
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_spinner, container, false);

        TextView textView = new TextView(getActivity());
        textView.setText("Testing");

        View popup_layout = inflater.inflate(R.layout.popup_layout,container,false);
        ListView teamsListView = (ListView) popup_layout.findViewById(R.id.teams_list_view);

        ArrayList<Team> teamsArrayList = new ArrayList<Team>();
        teamsArrayList.add(new Team(1,"Sharks"));
        teamsArrayList.add(new Team(2,"Android"));
        teamsArrayList.add(new Team(3,"Google"));
        teamsArrayList.add(new Team(4,"Yahoo"));
        teamsArrayList.add(new Team(5,"Facebook"));
        teamsArrayList.add(new Team(6,"Twitter"));
        teamsArrayList.add(new Team(7,"Apple"));
        teamsArrayList.add(new Team(8,"Amazon"));
        teamsArrayList.add(new Team(9,"Udacity"));
        teamsArrayList.add(new Team(10,"Bosch"));

        //adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line,sortList);

        final TeamsAdapter teamsAdapter = new TeamsAdapter(getActivity(), teamsArrayList);

        teamsListView.setAdapter(teamsAdapter);

        popupMessage = new PopupWindow(popup_layout, ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        //popupMessage.setContentView(textView);

        teamsSearchView = (SearchView) rootView.findViewById(R.id.teams_search_view);
        teamsSearchView.setQueryHint("Select Team/s");

        teamsSearchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                Toast.makeText(getActivity(), String.valueOf(hasFocus),
                        Toast.LENGTH_SHORT).show();

            }
        });

        teamsSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                Toast.makeText(getActivity(), query,
                        Toast.LENGTH_SHORT).show();

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                Toast.makeText(getActivity(), "newText=>"+newText,
                        Toast.LENGTH_SHORT).show();


                if(newText.isEmpty()){
                    popupMessage.dismiss();
                    return false;
                }

                //popupMessage.dismiss();
                if(!popupMessage.isShowing()){
                    popupMessage.showAsDropDown(teamsSearchView);
                }

                //teamsAdapter.getFilter().filter("");
                teamsAdapter.getFilter().filter(newText);

                /*teamsAdapter.getFilter().filter(newText, new Filter.FilterListener() {
                    @Override
                    public void onFilterComplete(int i) {
                        teamsAdapter.notifyDataSetChanged();
                    }
                });*/
                return true;
            }
        });
        return rootView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction();
    }
}
