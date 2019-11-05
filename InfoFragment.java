package com.example.wpls;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

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
 * {@link InfoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link InfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InfoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public InfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InfoFragment newInstance(String param1, String param2) {
        InfoFragment fragment = new InfoFragment();
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

    private ListView infoClassListView;
    private InfoClassListAdapter adapter;
    private List<Class> classList;

    public static int totalCredit;
    public static TextView totalClass;

    private ArrayAdapter rankAdapter;
    private Spinner rankSpinner;

    private ListView rankListView;
    private RankListAdapter rankListAdapter;
    private List<Class> rankList;



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        infoClassListView = (ListView)getView().findViewById(R.id.infoClassListView);
        classList = new ArrayList<Class>();
        adapter = new InfoClassListAdapter(getContext().getApplicationContext(), classList, this);
        infoClassListView.setAdapter(adapter);

        totalCredit = 0;
        totalClass = (TextView)getView().findViewById(R.id.totalClass);

        rankSpinner = (Spinner)getView().findViewById(R.id.rankSpinner);
        rankAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.rank, R.layout.spinner_item);
        rankSpinner.setAdapter(rankAdapter);

        rankListView = (ListView)getView().findViewById(R.id.RankListView);
        rankList = new ArrayList<Class>();
        rankListAdapter = new RankListAdapter(getContext().getApplicationContext(), rankList, this);
        rankListView.setAdapter(rankListAdapter);

        new Overall().execute();


        rankSpinner.setPopupBackgroundResource(R.color.colorPrimary);

        rankSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(rankSpinner.getSelectedItem().equals("Overall")){
                    rankList.clear();
                    new Overall().execute();
                }
                if(rankSpinner.getSelectedItem().equals("Girls Favorites")){
                    rankList.clear();
                    new Girls().execute();

                }
                if(rankSpinner.getSelectedItem().equals("Boys Favorites")){
                    rankList.clear();
                    new Boys().execute();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        new BackgroundTask().execute();



    }

    class Overall extends AsyncTask<Void, Void, String> {

        String target;

        @Override
        protected void onPreExecute() {
            try {
                target = "http://whitesnowtiger.com/android/OverallRank.php";
            }catch(Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            try {

                if(s!= null) {
                JSONObject jsonObject = new JSONObject(s);

                    JSONArray jsonArray = jsonObject.getJSONArray("response");
                    int count = 0;
                    String classRoom, classGrade,  classTitle, classTime;
                    int classMax, classID;
                    while (count < jsonArray.length()) {
                        JSONObject object = jsonArray.getJSONObject(count);
                        classID = object.getInt("classID");
                        classTitle = object.getString("classTitle");
                        classTime = object.getString("classTime");
                        classRoom = object.getString("classRoom");
                        classGrade = object.getString("classGrade");
                        classMax = object.getInt("classMax");

                        rankList.add(new Class(classID, classTitle, classGrade, classRoom, classMax, classTime));
                        count++;
                    }

                    rankListAdapter.notifyDataSetChanged();




                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(Void... voids) {
            try{
                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while ((temp = bufferedReader.readLine())!=null) {
                    stringBuilder.append(temp + "\n");

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

    class Girls extends AsyncTask<Void, Void, String> {

        String target;

        @Override
        protected void onPreExecute() {
            try {
                target = "http://whitesnowtiger.com/android/GirlsRank.php";
            }catch(Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            try {

                if(s!= null) {
                    JSONObject jsonObject = new JSONObject(s);

                    JSONArray jsonArray = jsonObject.getJSONArray("response");
                    int count = 0;
                    String classRoom, classGrade,  classTitle, classTime;
                    int classMax, classID;
                    while (count < jsonArray.length()) {
                        JSONObject object = jsonArray.getJSONObject(count);
                        classID = object.getInt("classID");
                        classTitle = object.getString("classTitle");
                        classTime = object.getString("classTime");
                        classRoom = object.getString("classRoom");
                        classGrade = object.getString("classGrade");
                        classMax = object.getInt("classMax");

                        rankList.add(new Class(classID, classTitle, classGrade, classRoom, classMax, classTime));
                        count++;
                    }

                    rankListAdapter.notifyDataSetChanged();




                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(Void... voids) {
            try{
                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while ((temp = bufferedReader.readLine())!=null) {
                    stringBuilder.append(temp + "\n");

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


    class Boys extends AsyncTask<Void, Void, String> {

        String target;

        @Override
        protected void onPreExecute() {
            try {
                target = "http://whitesnowtiger.com/android/BoysRank.php";
            }catch(Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            try {

                if(s!= null) {
                    JSONObject jsonObject = new JSONObject(s);

                    JSONArray jsonArray = jsonObject.getJSONArray("response");
                    int count = 0;
                    String classRoom, classGrade,  classTitle, classTime;
                    int classMax, classID;
                    while (count < jsonArray.length()) {
                        JSONObject object = jsonArray.getJSONObject(count);
                        classID = object.getInt("classID");
                        classTitle = object.getString("classTitle");
                        classTime = object.getString("classTime");
                        classRoom = object.getString("classRoom");
                        classGrade = object.getString("classGrade");
                        classMax = object.getInt("classMax");

                        rankList.add(new Class(classID, classTitle, classGrade, classRoom, classMax, classTime));
                        count++;
                    }

                    rankListAdapter.notifyDataSetChanged();




                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(Void... voids) {
            try{
                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while ((temp = bufferedReader.readLine())!=null) {
                    stringBuilder.append(temp + "\n");

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




    class BackgroundTask extends AsyncTask<Void, Void, String> {

        String target;

        @Override
        protected void onPreExecute() {
            try {
                target = "http://whitesnowtiger.com/android/InfoClassList.php?userID=" + URLEncoder.encode(MainActivity.userID, "UTF-8");
            }catch(Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void onPostExecute(String s) {
                try {

                    if(s!= null) {
                        JSONObject jsonObject = new JSONObject(s);
                        JSONArray jsonArray = jsonObject.getJSONArray("response");
                        int count = 0;
                        String classTitle, classTime;
                        int classStudents, classMax, classID, classCredit;
                        while (count < jsonArray.length()) {
                            JSONObject object = jsonArray.getJSONObject(count);
                            classID = object.getInt("classID");
                            classTitle = object.getString("classTitle");
                            classTime = object.getString("classTime");
                            classStudents = object.getInt("COUNT(SCHEDULE.classID)");
                            classMax = object.getInt("classMax");
                            classCredit = object.getInt("classCredit");
                            totalCredit += classCredit;
                            classList.add(new Class(classID, classTitle, classTime, classStudents, classMax, classCredit));
                            count++;
                        }

                        adapter.notifyDataSetChanged();
                        totalClass.setText(totalCredit+"");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }


        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(Void... voids) {
            try{
                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while ((temp = bufferedReader.readLine())!=null) {
                    stringBuilder.append(temp + "\n");

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false);
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
}
