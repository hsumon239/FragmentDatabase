package com.sumon.myfragmentsapp;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class CourseListFragment extends Fragment {

    public static final String TAG = "CourseListFragment";
    private TableLayout tableLayout;
    private ArrayList<CourseModel> courseModelArrayList;

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

        View view = inflater.inflate(R.layout.fragment_course_list, container, false);
        tableLayout = (TableLayout) view.findViewById(R.id.course_table);

        courseModelArrayList = dbHandler.readCourses();

        for (CourseModel c : courseModelArrayList) {
            TableRow tbrow = new TableRow(getActivity());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.weight = 1f;
            tbrow.setLayoutParams(params);
            tbrow.setBackgroundColor(Color.parseColor("#DAE8FC"));
//            tbrow.setPadding((int) getPixelToDp(5f), (int) getPixelToDp(2f), (int) getPixelToDp(5f), (int) getPixelToDp(2f));

            Log.i(TAG, "onCreateView: Course List " + c.getId());
            Log.i(TAG, "onCreateView: Course List " + c.getCourseName());
            Log.i(TAG, "onCreateView: Course List " + c.getCourseTracks());
            Log.i(TAG, "onCreateView: Course List " + c.getCourseDuration());
            Log.i(TAG, "onCreateView: Course List " + c.getCourseDescription());

            TextView id = new TextView(getActivity());
            TextView name = new TextView(getActivity());
            TextView duration = new TextView(getActivity());
            TextView tracks = new TextView(getActivity());
            TextView description = new TextView(getActivity());

            id.setText(Integer.toString(c.getId()));
            id.setGravity(Gravity.CENTER);
            name.setText(c.getCourseName());
            name.setGravity(Gravity.CENTER);
            duration.setText(c.getCourseDuration());
            duration.setGravity(Gravity.CENTER);
            tracks.setText(c.getCourseTracks());
            tracks.setGravity(Gravity.CENTER);
            description.setText(c.getCourseDescription());
            description.setGravity(Gravity.CENTER);

            tbrow.addView(id);
            tbrow.addView(name);
            tbrow.addView(duration);
            tbrow.addView(tracks);
            tbrow.addView(description);
            tableLayout.addView(tbrow);
        }
        return view;

    }

    public float getPixelToDp(float dip) {
        return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dip,
                getResources().getDisplayMetrics()
        );
    }

}