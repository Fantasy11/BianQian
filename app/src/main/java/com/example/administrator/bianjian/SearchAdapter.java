package com.example.administrator.bianjian;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter <SearchAdapter.ViewHolder>implements Filterable {
    private List<DBa> list ;
    private List<DBa> slist;
    private Context context;
    private MyFilter filter;


    SearchAdapter(Context context,List<DBa> list){
        this.context=context;
        this.list=list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        private View tView;
        private TextView time_tv;
        public ViewHolder(View itemView) {
            super(itemView);
            tView=itemView;
            textView=itemView.findViewById(R.id.tv_item);
            time_tv=itemView.findViewById(R.id.time_tv);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        final ViewHolder viewHolder =new ViewHolder(view);
        setClickListener(viewHolder,parent);
        return viewHolder;
    }

    private void setClickListener(final ViewHolder viewHolder, final ViewGroup parent) {
        viewHolder.tView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = viewHolder.getAdapterPosition();
                Intent intent =new Intent(parent.getContext(),Content.class);
                intent.putExtra("Id",slist.get(position).getId());
                context.startActivity(intent);
                //Toast.makeText(parent.getContext(),""+list.get(position),Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(slist.get(position).getContent());
        holder.time_tv.setText(slist.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return slist  == null ? 0 :slist.size();
    }

    @Override
    public Filter getFilter() {
        if(filter==null){
            filter = new SearchAdapter.MyFilter();
        }
        return filter;
    }

    class MyFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults filterResults = new FilterResults();
            List<DBa> fList = new ArrayList<>();
            if (charSequence == null || charSequence.length() == 0) {
                filterResults.count = 0;
                filterResults.values = null;
            } else {
                String s = charSequence.toString();
                for (DBa dBa : list) {
                    if (dBa.getContent().contains(s)) {
                        fList.add(dBa);
                    }
                }
                filterResults.values = fList;
                filterResults.count = fList.size();
            }
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

            if (filterResults.values != null && filterResults.count > 0) {
                slist = (List<DBa>) filterResults.values;
                notifyDataSetChanged();
            } else {
                slist = null;
                notifyDataSetChanged();
            }
        }

    }
}
