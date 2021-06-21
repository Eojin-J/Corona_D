package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<item> mList;

    public  class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView_title, textView_facility, textView_name, textView_address , textView_visit, textView_disinfection;

        public ViewHolder(View itemView) {
            super(itemView);

            textView_title = (TextView) itemView.findViewById(R.id.textView_title);
            textView_facility =  (TextView) itemView.findViewById(R.id.textView_facility);
            textView_name = (TextView) itemView.findViewById(R.id.textView_name);
            textView_visit = (TextView) itemView.findViewById(R.id.textView_visit);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("Recyclerview", "position = "+ getAdapterPosition());
                    int pos =getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        Intent intent = new Intent(mContext,DetailActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//#content > div > div > div:nth-child(1) > div > div.table_scroll.scroll_x > table > tbody > tr:nth-child(8) > td:nth-child(5) > p:nth-child(2)
                        intent.putExtra("title", String.valueOf(mList.get(pos)));
                        intent.putExtra("name", String.valueOf(mList.get(pos).getName()));
                        intent.putExtra("address", String.valueOf(mList.get(pos).getAddress()));
                        intent.putExtra("visitTime", String.valueOf(mList.get(pos).getVisitTime2()));

                        mContext.startActivity(intent);

                    }
                }

            });

        }
    }

    //생성자
    public MyAdapter(Context context, ArrayList<item> list) {
        this.mContext = context;
        this.mList = list;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {


        if (mList.get(position).getName() == ""){
            System.out.println("1111111111111111");
        }
        else {
            holder.textView_title.setText(String.valueOf(mList.get(position).getTitle()));
            holder.textView_facility.setText(String.valueOf(mList.get(position).getFacility()));
            holder.textView_name.setText(String.valueOf(mList.get(position).getName()));

            holder.textView_visit.setText(String.valueOf(mList.get(position).getVisitTime()));

        }


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
