package com.example.wpls;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

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

public class ClassListAdapter extends BaseAdapter {

    private Context context;
    private List<Class> classList;
    private Fragment parent;
    private String userID = MainActivity.userID;
    private Schedule schedule = new Schedule();
    private List<Integer> classIDList;
    private static int totalCredit;
    private static int totalStudents;

    public ClassListAdapter(Context context, List<Class> classList, Fragment parent ) {
        this.context = context;
        this.classList = classList;
        this.parent = parent;
        schedule = new Schedule();
        classIDList = new ArrayList<Integer>();
        totalCredit = 0;

        new BackgroundTask().execute();

    }

    @Override
    public int getCount() {
        return classList.size();
    }

    @Override
    public Object getItem(int position) {
        return classList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.class_adapter, null);
        TextView classTitle = (TextView)v.findViewById(R.id.classTitle);
        TextView classGrade = (TextView)v.findViewById(R.id.classGrade);
        TextView classRoom = (TextView)v.findViewById(R.id.classRoom);
        final TextView classMax = (TextView)v.findViewById(R.id.classMax);
        TextView classTime = (TextView)v.findViewById(R.id.classTime);

        classTitle.setText(classList.get(position).getClassTitle());
        classGrade.setText(classList.get(position).getClassGrade());

        if(classList.get(position).getClassRoom().equals("") || classList.get(position).getClassRoom().equals(null)) {
            classRoom.setText("Room: TBD");
        } else {
            classRoom.setText("Room: " + classList.get(position).getClassRoom());
        }

        if(classList.get(position).getClassMax() == 0) {
            classMax.setText("Max: unknown");
        } else {
            classMax.setText("Max: " + classList.get(position).getClassMax());
        }

        classTime.setText(classList.get(position).getClassTime());

        v.setTag(classList.get(position).getClassID());

        final Button addButton = (Button)v.findViewById(R.id.addButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean validate = false;
                validate = schedule.validate(classList.get(position).getClassTime());
                if (!alreadyIn(classIDList, classList.get(position).getClassID())) {
                    AlertDialog dialog;
                    AlertDialog.Builder builder = new AlertDialog.Builder(parent.getActivity());
                    dialog = builder.setMessage("You've already added this class.")
                            .setPositiveButton("Try again.", null)
                            .create();
                    dialog.show();
                } else if (validate == false) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(parent.getActivity());
                    AlertDialog dialog = builder.setMessage("Your schedule is overlapping.")
                            .setNegativeButton("Try again.", null)
                            .create();
                    dialog.show();

                } /*else if (totalStudents +1 > classMax) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(parent.getActivity());
                    AlertDialog dialog = builder.setMessage("This class is full.")
                            .setNegativeButton("Add other classes.", null)
                            .create();
                    dialog.show();

                } */ else {


                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                boolean success = jsonObject.getBoolean("success");
                                if (success) {
                                    AlertDialog dialog;
                                    AlertDialog.Builder builder = new AlertDialog.Builder(parent.getActivity());
                                    dialog = builder.setMessage("You've added a class.")
                                            .setPositiveButton("OK", null)
                                            .create();
                                    dialog.show();
                                    classIDList.add(classList.get(position).getClassID());
                                    schedule.addSchedule(classList.get(position).getClassTime());
                                    totalCredit += classList.get(position).getClassCredit();


                                    notifyDataSetChanged();

                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(parent.getActivity());
                                    AlertDialog dialog = builder.setMessage("Failed.")
                                            .setNegativeButton("Try again.", null)
                                            .create();
                                    dialog.show();

                                }


                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }

                    };

                    AddRequest addRequest = new AddRequest(userID, classList.get(position).getClassID() + "", responseListener);
                    RequestQueue queue = Volley.newRequestQueue(parent.getActivity());
                    queue.add(addRequest);
                }
            }
        });




        return v;
    }

    class BackgroundTask extends AsyncTask<Void, Void, String> {

        String target;

        @Override
        protected void onPreExecute() {
            try{
                target = "http://whitesnowtiger.com/android/ScheduleList.php?userID="+ URLEncoder.encode(userID, "UTF-8");
            } catch (Exception e) {
                e.printStackTrace();
            }

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
                while ((temp = bufferedReader.readLine()) != null) {
                    stringBuilder.append(temp + '\n');
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



        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {


            try {
                if (s != null) {
                    JSONObject jsonObject = new JSONObject(s);
                    totalCredit = 0;

                    JSONArray jsonArray = jsonObject.getJSONArray("response");
                    int count = 0;
                    int classID, classCredit = 0;
                    String classTime, classTitle;
                    while (count < jsonArray.length()) {
                        JSONObject object = jsonArray.getJSONObject(count);
                        classID = object.getInt("classID");
                        classTime = object.getString("classTime");
                        classTitle = object.getString("classTitle");
                        classCredit = object.getInt("classCredit");
                        totalCredit += classCredit;
                        classIDList.add(classID);
                        schedule.addSchedule(classTime);


                        count++;
                    }
                }

                } catch(Exception e){
                    e.printStackTrace();
                }


        }
    }

    public boolean alreadyIn(List<Integer> classIDList, int item) {
        for(int i = 0; i<classIDList.size(); i++){
            if(classIDList.get(i) == item) {return false;}

        }
        return true;
    }
}
