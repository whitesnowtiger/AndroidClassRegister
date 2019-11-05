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

public class RankListAdapter extends BaseAdapter {

    private Context context;
    private List<Class> classList;
    private Fragment parent;


    public RankListAdapter(Context context, List<Class> classList, Fragment parent) {
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
        View v = View.inflate(context, R.layout.rank, null);
        TextView rankTextView = (TextView) v.findViewById(R.id.rankTextView);
        TextView classTitle = (TextView) v.findViewById(R.id.classTitle);
        TextView classGrade = (TextView) v.findViewById(R.id.classGrade);
        TextView classRoom = (TextView) v.findViewById(R.id.classRoom);
        TextView classMax = (TextView) v.findViewById(R.id.classMax);
        TextView classTime = (TextView) v.findViewById(R.id.classTime);


        rankTextView.setText("No." + (position + 1));
        if (position != 0) {
            rankTextView.setBackgroundColor(parent.getResources().getColor(R.color.colorPrimary));

        }
        classTitle.setText(classList.get(position).getClassTitle());
        classGrade.setText(classList.get(position).getClassGrade());
        classRoom.setText("Classroom: " + classList.get(position).getClassRoom());
        classMax.setText("Class Max: " + classList.get(position).getClassMax());
        classTime.setText(classList.get(position).getClassTime());


            v.setTag(classList.get(position).getClassID());

            return v;



    }
}
