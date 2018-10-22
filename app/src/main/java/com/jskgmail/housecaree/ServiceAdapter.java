package com.jskgmail.housecaree;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ServiceAdapter extends ArrayAdapter<Service>{

    ArrayList<Service> services=new ArrayList<>();

    public ServiceAdapter(Context context, ArrayList<Service> services) {
        super(context,0, services);
        this.services=services;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_row, parent, false);
        }

        Service service=services.get(position);
        TextView textService=listItemView.findViewById(R.id.textService);
        textService.setText(service.getServiceName());

        ImageView imageService=listItemView.findViewById(R.id.imageService);
        imageService.setImageResource(service.getImage());

        return listItemView;
    }
}
