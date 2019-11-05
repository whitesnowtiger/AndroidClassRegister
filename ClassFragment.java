package com.example.wpls;

import android.app.AlertDialog;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ClassFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ClassFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClassFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ClassFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ClassFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ClassFragment newInstance(String param1, String param2) {
        ClassFragment fragment = new ClassFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    private ArrayAdapter yearAdapter;
    private Spinner yearSpinner;
    private ArrayAdapter termAdapter;
    private Spinner termSpinner;
    private ArrayAdapter gradeAdapter;
    private Spinner gradeSpinner;

    private String classCampus;
    private String classYear;
    private String classTerm;
    private String classGrade;

    private ListView classListView;
    private ClassListAdapter adapter;
    private List<Class> classList;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final RadioGroup classCamupsGroup = (RadioGroup)getView().findViewById(R.id.classCampusGroup);
        yearSpinner = (Spinner)getView().findViewById(R.id.yearSpinner);
        termSpinner = (Spinner)getView().findViewById(R.id.termSpinner);
        gradeSpinner = (Spinner)getView().findViewById(R.id.gradeSpinner);

        classCamupsGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton campusButton = (RadioButton)getView().findViewById(checkedId);
                classCampus = campusButton.getText().toString();


            }
        });

        yearAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.year, android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(yearAdapter);
        yearSpinner.setSelection(3);
        classYear = yearSpinner.getSelectedItem().toString();

        termAdapter = ArrayAdapter.createFromResource(getContext(), R.array.term, android.R.layout.simple_spinner_dropdown_item);
        termSpinner.setAdapter(termAdapter);
        classTerm = termSpinner.getSelectedItem().toString();

        gradeAdapter = ArrayAdapter.createFromResource(getContext(), R.array.grade, android.R.layout.simple_spinner_dropdown_item);
        gradeSpinner.setAdapter(gradeAdapter);

        classListView = (ListView)getView().findViewById(R.id.classListView);
        classList = new ArrayList<Class>();
        adapter = new ClassListAdapter(getContext().getApplicationContext(), classList, this);
        classListView.setAdapter(adapter);


        Button searchButton = (Button)getView().findViewById(R.id.searchButton);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new BackgroundTask().execute();

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_class, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    class BackgroundTask extends AsyncTask<Void, Void, String> {

        String target;



        @Override
        protected void onPreExecute() {


            try {
                target = "http://whitesnowtiger.com/android/ClassList.php?classCampus=" + URLEncoder.encode(classCampus, "UTF-8") + "&classYear=" + URLEncoder.encode(classYear, "UTF-8") + "&classTerm=" + URLEncoder.encode(classTerm, "UTF-8");
            }catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                /*AlertDialog dialog;
                AlertDialog.Builder builder = new AlertDialog.Builder(ClassFragment.this.getContext());
                dialog = builder.setMessage(s)
                        .setPositiveButton("OK", null)
                        .create();
                dialog.show();*/
                if(s != null) {
                classList.clear();
                JSONObject jsonObject = new JSONObject(s);

                    JSONArray jsonArray = jsonObject.getJSONArray("response");
                    int count = 0;
                    int classID, classYear, classMax, classCredit;
                    String classCampus, classTerm, classGrade, classTitle, classRoom, classTime, classFee, classContact;
                    while (count < jsonArray.length()) {
                        JSONObject object = jsonArray.getJSONObject(count);
                        classID = object.getInt("classID");
                        classCampus = object.getString("classCampus");
                        classYear = object.getInt("classYear");
                        classTerm = object.getString("classTerm");
                        classGrade = object.getString("classGrade");
                        classTitle = object.getString("classTitle");
                        classRoom = object.getString("classRoom");
                        classTime = object.getString("classTime");
                        classMax = object.getInt("classMax");
                        classFee = object.getString("classFee");
                        classContact = object.getString("classContact");
                        classCredit = object.getInt("classCredit");
                        Class classes = new Class(classID, classCampus, classYear, classTerm, classGrade, classTitle, classRoom, classTime, classMax, classFee, classContact, classCredit);
                        classList.add(classes);
                        count++;
                    }

                    if (count == 0) {
                        AlertDialog dialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(ClassFragment.this.getActivity());
                        dialog = builder.setMessage("There are no classes you are looking for. Please check your input.")
                                .setNegativeButton("Try again.", null)
                                .create();
                        dialog.show();
                    }
                    adapter.notifyDataSetChanged();
                }


            } catch (Exception e){
                e.printStackTrace();
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(Void... voids) {
            try
            {
                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while((temp = bufferedReader.readLine()) != null) {
                    stringBuilder.append(temp+'\n');
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                 return stringBuilder.toString().trim();


            } catch (Exception e) {
                e.printStackTrace();
            }
             return null;
        }
    }

}
