package com.sumon.myfragmentsapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class CourseListFragment extends Fragment {

    public static final String TAG = "CourseListFragment";

    private DBHandler dbHandler;

    public CourseListFragment() {
        // Required empty public constructor
    }

    public CourseListFragment(DBHandler dbHandler) {
        this.dbHandler = dbHandler;
        Log.i(TAG, "onCreate: dbHandler = " + dbHandler.toString());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (dbHandler == null) {
            dbHandler = new DBHandler(getActivity());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_course_list, container, false);
    }
}