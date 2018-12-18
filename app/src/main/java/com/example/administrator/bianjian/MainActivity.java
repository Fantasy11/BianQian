package com.example.administrator.bianjian;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private ImageView add_bt,back_bt,search_bt;

    private List<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //LitePal.getDatabase();

        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        adapter = new TextAdapter(this,data());
        recyclerView = findViewById(R.id.rcView);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        add_bt=findViewById(R.id.add_bt);
        back_bt=findViewById(R.id.back_bt);
        search_bt=findViewById(R.id.search_bt);
        add_bt.setOnClickListener(this);
        back_bt.setOnClickListener(this);
        search_bt.setOnClickListener(this);
        add_bt.setVisibility(View.VISIBLE);
        search_bt.setVisibility(View.VISIBLE);
    }

    private List<DBa> data(){
        list =new ArrayList<>();
        List<DBa> cont = DataSupport.findAll(DBa.class);
        if(cont!=null){
            return cont;

        }
        else {
            return null;
        }

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.add_bt:
                Intent intent =new Intent(this,Content.class);
                intent.putExtra("Id",0);
                startActivity(intent);
                break;
            case R.id.search_bt:
                Intent intent1 = new Intent(this,Search.class);
                startActivity(intent1);
                break;
        }
    }
}
