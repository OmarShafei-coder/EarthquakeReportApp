package com.example.earthquakereport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder>{

    private Context context;
    private ArrayList<Earthquake> data;
    private LayoutInflater layoutInflater;

    public CustomAdapter(Context context, ArrayList<Earthquake> data) {
        this.context = context;
        this.data = data;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_item, parent, false);
        CustomViewHolder customViewHolder = new CustomViewHolder(view);
        return customViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        Earthquake currentElement = data.get(position);
        holder.magnitude.setText(String.valueOf(currentElement.getMagnitude()));
        holder.place.setText(currentElement.getPlace());
        holder.date.setText(currentElement.getDate());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        private TextView magnitude;
        private TextView place;
        private TextView date;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            magnitude = itemView.findViewById(R.id.magnitude);
            place = itemView.findViewById(R.id.location);
            date = itemView.findViewById(R.id.date);
        }
    }
}
