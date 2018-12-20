package com.example.administrator.bianjian;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Switch;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private ImageView add_bt,back_bt,search_bt,change_bt;
    private static int userGroup;
    private List<String> list;
    private SharedPreferences sharedPreferences;
    private final static String TAG ="userGroupTest";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //LitePal.getDatabase();
        init();

    }

    private List<DBa> data(){
        list =new ArrayList<>();
        List<DBa> cont = DataSupport.where("num=?",userGroup+"").find(DBa.class);
        //Log.d(TAG,userGroup+"");
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
                intent.putExtra("group",userGroup);
                startActivity(intent);
                break;
            case R.id.search_bt:
                Intent intent1 = new Intent(this,Search.class);
                intent1.putExtra("group",userGroup);
                startActivity(intent1);
                break;
            case R.id.change_bt:
                PopupMenu popupMenu =new PopupMenu(this,view);
                popupMenu.getMenuInflater().inflate(R.menu.user,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.userA:
                                userGroup=1;
                                adapter = new TextAdapter(MainActivity.this,data());
                                recyclerView.setAdapter(adapter);
                                setLocalUser(userGroup);
                                break;
                            case R.id.userB:
                                userGroup=2;
                                adapter = new TextAdapter(MainActivity.this,data());
                                recyclerView.setAdapter(adapter);
                                setLocalUser(userGroup);
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();

        }
    }
    private void setLocalUser(int user){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt("user",user);
        editor.apply();
    }
    private void init(){
        sharedPreferences =getSharedPreferences("user",Context.MODE_PRIVATE);
        userGroup=sharedPreferences.getInt("user",1);
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        adapter = new TextAdapter(this,data());
        recyclerView = findViewById(R.id.rcView);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        add_bt=findViewById(R.id.add_bt);
        back_bt=findViewById(R.id.back_bt);
        search_bt=findViewById(R.id.search_bt);
        change_bt = findViewById(R.id.change_bt);
        add_bt.setOnClickListener(this);
        back_bt.setOnClickListener(this);
        search_bt.setOnClickListener(this);
        change_bt.setOnClickListener(this);
        add_bt.setVisibility(View.VISIBLE);
        search_bt.setVisibility(View.VISIBLE);
        change_bt.setVisibility(View.VISIBLE);
    }
}
