package com.juniar.nostraassessment.listnumber;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.juniar.nostraassessment.R;

import java.util.List;

public class ListNumberAdapter extends ArrayAdapter<String> {

    private List<String> listNumber;
    private Activity activity;

    public ListNumberAdapter(Activity activity, List<String> listNumber) {
        super(activity, R.layout.viewholder_number, listNumber);
        this.activity = activity;
        this.listNumber = listNumber;
    }

    static class ViewHolder {
        public TextView tvNumber;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater layoutInflater = activity.getLayoutInflater();
            view = layoutInflater.inflate(R.layout.viewholder_number, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.tvNumber = view.findViewById(R.id.tv_number);
            view.setTag(viewHolder);
        }

        ViewHolder viewHolder = (ViewHolder) view.getTag();
        viewHolder.tvNumber.setText(listNumber.get(position));

        return view;
    }
}
