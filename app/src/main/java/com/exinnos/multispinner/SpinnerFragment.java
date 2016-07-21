package com.exinnos.multispinner;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;


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

        popupMessage = new PopupWindow(rootView, ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        popupMessage.setContentView(textView);

        teamsSearchView = (SearchView) rootView.findViewById(R.id.teams_search_view);
        teamsSearchView.setQueryHint("Select Team/s");

        teamsSearchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                Toast.makeText(getActivity(), String.valueOf(hasFocus),
                        Toast.LENGTH_SHORT).show();
                popupMessage.dismiss();
                popupMessage.showAsDropDown(teamsSearchView);
            }
        });

        teamsSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                Toast.makeText(getActivity(), query,
                        Toast.LENGTH_SHORT).show();

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                Toast.makeText(getActivity(), "newText=>"+newText,
                        Toast.LENGTH_SHORT).show();

                return false;
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
