package com.example.administrator.bianjian;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FilterAdapter extends BaseAdapter implements Filterable {
    private MyFilter filter;
    private Context context;
    private List<DBa> list;
    private List<DBa> slist=null;
    private int group;

    public FilterAdapter(Context context, List<DBa> list, int group) {
        this.context = context;
        this.list = list;
        this.group=group;
    }

    static class ViewHolder {

        TextView content_tv;
        TextView time_tv;
    }

    @Override
    public int getCount() {
        if(slist==null){
            return 0;
        }

        return slist.size();

    }

    @Override
    public Object getItem(int i) {
        if(slist==null){
            return null;
        }

        return slist.get(i);

    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_layout,null);
            holder = new ViewHolder();
            holder.content_tv =view.findViewById(R.id.tv_item);
            holder.time_tv=view.findViewById(R.id.time_tv);
            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }
        if(slist==null){
            return view;
        }
        holder.content_tv.setText(slist.get(i).getContent());
        holder.time_tv.setText(slist.get(i).getDate());
        setClickListen(i,holder);

        return view;
    }

    private void setClickListen(final int i, ViewHolder holder) {
        holder.content_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(context,Content.class);
                intent.putExtra("Id",slist.get(i).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public Filter getFilter() {
        if(filter==null){
            filter = new MyFilter();
        }
        return filter;
    }

    class MyFilter extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults filterResults =new FilterResults();
            List<DBa> fList = new ArrayList<>();
            if (charSequence==null || charSequence.length()==0){
                filterResults.count=0;
                filterResults.values=null;
            }
            else {
                String s = charSequence.toString();
                for (DBa dBa : list){
                    if (dBa.getContent().contains(s)){
                        fList.add(dBa);
                    }
                }
                filterResults.values=fList;
                filterResults.count=fList.size();
            }
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

            if(filterResults.values!=null && filterResults.count>0){
                slist = (List<DBa>)filterResults.values;
                notifyDataSetChanged();
            }
            else {
                slist=null;
                notifyDataSetChanged();
            }
        }
    }
}
