package com.example.earthquakereport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import android.graphics.drawable.GradientDrawable;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder>{

    private Context context;
    private ArrayList<Earthquake> data;
    private LayoutInflater layoutInflater;
    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_item, parent, false);
        CustomViewHolder customViewHolder = new CustomViewHolder(view, mListener);
        return customViewHolder;
    }

    public CustomAdapter(Context context, ArrayList<Earthquake> data) {
        this.context = context;
        this.data = data;
        layoutInflater = LayoutInflater.from(context);
    }

    //perform onclick in recyclerView
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        Earthquake currentElement = data.get(position);
        holder.magnitude.setText(String.valueOf(currentElement.getMagnitude()));
        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) holder.magnitude.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentElement.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        holder.primaryLocation.setText(currentElement.getPrimaryLocation());
        holder.locationOffset.setText(currentElement.getLocationOffset());
        holder.date.setText(currentElement.getDate());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        private TextView magnitude;
        private TextView primaryLocation;
        private TextView locationOffset;
        private TextView date;

        public CustomViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            magnitude = itemView.findViewById(R.id.magnitude);
            primaryLocation = itemView.findViewById(R.id.primary_location);
            locationOffset = itemView.findViewById(R.id.location_offset);
            date = itemView.findViewById(R.id.date);

            //onCLick for recyclerView
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
    private int getMagnitudeColor(double magnitude){
        int magnitudeColor;
        switch ((int) magnitude){
            case 0:
            case 1:
                magnitudeColor = ContextCompat.getColor(context, R.color.magnitude1);
                break;
            case 2:
                magnitudeColor = ContextCompat.getColor(context, R.color.magnitude2);
                break;
            case 3:
                magnitudeColor = ContextCompat.getColor(context, R.color.magnitude3);
                break;
            case 4:
                magnitudeColor = ContextCompat.getColor(context, R.color.magnitude4);
                break;
            case 5:
                magnitudeColor = ContextCompat.getColor(context, R.color.magnitude5);
                break;
            case 6:
                magnitudeColor = ContextCompat.getColor(context, R.color.magnitude6);
                break;
            case 7:
                magnitudeColor = ContextCompat.getColor(context, R.color.magnitude7);
                break;
            case 8:
                magnitudeColor = ContextCompat.getColor(context, R.color.magnitude8);
                break;
            case 9:
                magnitudeColor = ContextCompat.getColor(context, R.color.magnitude9);
                break;
            case 10:
            default:
                magnitudeColor = ContextCompat.getColor(context, R.color.magnitude10plus);
        }

        return magnitudeColor;
    }
}
