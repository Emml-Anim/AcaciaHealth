package net.syynclab.acaciahealth.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import net.syynclab.acaciahealth.R;
import net.syynclab.acaciahealth.model.HealthProvider;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements Filterable {

    Context context;
    List<HealthProvider> sourceItems;
    List<HealthProvider> filteredItems;


    public MyAdapter(Context context, List<HealthProvider> sourceItems) {
        this.context = context;
        this.sourceItems = sourceItems;
        this.filteredItems = sourceItems;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View healthProviderView = LayoutInflater.from(context).inflate(R.layout.health_provider_accra, viewGroup, false);
        return new MyViewHolder(healthProviderView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.title.setText(filteredItems.get(i).getTitle());
        myViewHolder.url.setText(filteredItems.get(i).getUrl());

    }

    @Override
    public int getItemCount() {
        return filteredItems.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String searchString = charSequence.toString();
                if (searchString.isEmpty())
                    filteredItems = sourceItems;  //restore filtered list to full data
                else
                {
                    List<HealthProvider> resultList = new ArrayList<>();
                    for(HealthProvider healthProvider : sourceItems){
                        if (healthProvider.getTitle().toLowerCase().contains(searchString.toLowerCase()))
                            resultList.add(healthProvider);
                    }

                    filteredItems = resultList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredItems;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults filterResults) {
                filteredItems = (ArrayList<HealthProvider>)filterResults.values;
                notifyDataSetChanged();

            }
        };
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView title, url;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            title = (TextView)itemView.findViewById(R.id.title);
            url = (TextView)itemView.findViewById(R.id.url);
        }
    }
}
