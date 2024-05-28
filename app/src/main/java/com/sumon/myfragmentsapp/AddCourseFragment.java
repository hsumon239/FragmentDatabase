package com.sumon.myfragmentsapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class AddCourseFragment extends Fragment {

    public static final String TAG = "AddCourseFragment";

    private DBHandler dbHandler;
    // creating variables for our edittext, button and dbhandler
    private EditText courseNameEdt, courseTracksEdt, courseDurationEdt, courseDescriptionEdt;
    private Button addCourseBtn;

    public AddCourseFragment() {
        // Required empty public constructor
    }

    public AddCourseFragment(DBHandler dbHandler) {
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

        View view = inflater.inflate(R.layout.fragment_add_course, container, false);
        courseNameEdt = (EditText) view.findViewById(R.id.idEdtCourseName);
        courseNameEdt = (EditText) view.findViewById(R.id.idEdtCourseName);
        courseTracksEdt = (EditText) view.findViewById(R.id.idEdtCourseTracks);
        courseDurationEdt = (EditText) view.findViewById(R.id.idEdtCourseDuration);
        courseDescriptionEdt = (EditText) view.findViewById(R.id.idEdtCourseDescription);
        addCourseBtn = (Button) view.findViewById(R.id.idBtnAddCourse);


        addCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // below line is to get data from all edit text fields.
                String courseName = courseNameEdt.getText().toString();
                String courseTracks = courseTracksEdt.getText().toString();
                String courseDuration = courseDurationEdt.getText().toString();
                String courseDescription = courseDescriptionEdt.getText().toString();

                // validating if the text fields are empty or not.
                if (courseName.isEmpty() && courseTracks.isEmpty() && courseDuration.isEmpty() && courseDescription.isEmpty()) {
                    Toast.makeText(getActivity(), "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }

                // on below line we are calling a method to add new
                // course to sqlite data and pass all our values to it.
                dbHandler.addNewCourse(courseName, courseDuration, courseTracks, courseDescription);

                // after adding the data we are displaying a toast message.
                Toast.makeText(getActivity(), "Course has been added.", Toast.LENGTH_SHORT).show();
                courseNameEdt.setText("");
                courseDurationEdt.setText("");
                courseTracksEdt.setText("");
                courseDescriptionEdt.setText("");
            }
        });


        return view;
    }
}