package ru.samsung.itschool.dbgame;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultAdapter extends BaseAdapter {

    ArrayList<Result> results = new ArrayList<>();
    Context context;

    public ResultAdapter(Context context, ArrayList<Result> results) {
        this.results = results;
        this.context = context;
    }

    @Override
    public int getCount() {
        return results.size();
    }

    @Override
    public Object getItem(int position) {
        return results.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        @SuppressLint({"InflateParams", "ViewHolder"})
        View view = LayoutInflater.from(context).inflate(R.layout.result_item, null);
        TextView nameTV = view.findViewById(R.id.text1);
        nameTV.setText(results.get(position).name);
        TextView scoreTV = view.findViewById(R.id.text2);
        scoreTV.setText(results.get(position).score+"");
        if(results.get(position).score % 2 == 0){
            scoreTV.setTextColor(Color.RED);
        } else {
            scoreTV.setTextColor(Color.GREEN);
        }
        return view;
    }
}
