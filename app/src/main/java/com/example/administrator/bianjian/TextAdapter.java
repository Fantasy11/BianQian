package com.example.administrator.bianjian;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class TextAdapter extends RecyclerView.Adapter <TextAdapter.ViewHolder>{
    private List<DBa> list ;
    private Context context;
    TextAdapter(Context context,List<DBa> list){
        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        final ViewHolder viewHolder =new ViewHolder(view);
        viewHolder.tView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = viewHolder.getAdapterPosition();
                Intent intent =new Intent(parent.getContext(),Content.class);
                intent.putExtra("Id",list.get(position).getId());
                context.startActivity(intent);
                //Toast.makeText(parent.getContext(),""+list.get(position),Toast.LENGTH_SHORT).show();
            }
        });
        viewHolder.tView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                final int position = viewHolder.getAdapterPosition();
                new AlertDialog.Builder(context)
                        .setTitle("删除")
                        .setMessage("是否删除该标签")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                DBa dBa =list.get(position);
                                dBa.delete();
                                list.remove(position);
                                notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .create().show();
                //Toast.makeText(parent.getContext(),"长按"+list.get(position),Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(list.get(position).getContent());
        holder.time_tv.setText(list.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return list  == null ? 0 :list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        View tView;
        TextView time_tv;
        public ViewHolder(View itemView) {
            super(itemView);
            tView=itemView;
            textView=itemView.findViewById(R.id.tv_item);
            time_tv=itemView.findViewById(R.id.time_tv);
        }
    }
}
