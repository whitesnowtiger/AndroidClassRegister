package com.example.wpls;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.List;

public class InfoClassListAdapter extends BaseAdapter {

    private Context context;
    private List<Class> classList;
    private Fragment parent;
    private String userID = MainActivity.userID;
   
    public InfoClassListAdapter(Context context, List<Class> classList, Fragment parent ) {
        this.context = context;
        this.classList = classList;
        this.parent = parent;
       


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
        View v = View.inflate(context, R.layout.info, null);
        TextView classTitle = (TextView)v.findViewById(R.id.classTitle);
        TextView classTime = (TextView)v.findViewById(R.id.classTime);
        TextView classStudents = (TextView)v.findViewById(R.id.classStudents); 
        TextView classMax = (TextView)v.findViewById(R.id.classMax);

        classTitle.setText(classList.get(position).getClassTitle());
        classTime.setText(classList.get(position).getClassTime());
        classStudents.setText(classList.get(position).getClassStudents() +"/" +  classList.get(position).getClassMax());
        


        final Button deleteButton = (Button)v.findViewById(R.id.deleteButton);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                boolean success = jsonObject.getBoolean("success");
                                if (success) {
                                    AlertDialog dialog;
                                    AlertDialog.Builder builder = new AlertDialog.Builder(parent.getActivity());
                                    dialog = builder.setMessage("You've deleted a class.")
                                            .setPositiveButton("OK", null)
                                            .create();
                                    dialog.show();
                                    InfoFragment.totalCredit -= classList.get(position).getClassCredit();
                                    InfoFragment.totalClass.setText(InfoFragment.totalCredit);
                                    classList.remove(position);
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

                    DeleteRequest DeleteRequest = new DeleteRequest(userID, classList.get(position).getClassID() + "", responseListener);
                    RequestQueue queue = Volley.newRequestQueue(parent.getActivity());
                    queue.add(DeleteRequest);
                }

        });


        v.setTag(classList.get(position).getClassID());

        return v;
    }


}
