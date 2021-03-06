package com.example.administrator.bianjian;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import org.litepal.crud.DataSupport;

import java.util.List;

public class Search extends AppCompatActivity implements View.OnClickListener {
    ImageView find_bt,back_bt;
    EditText editText;
    SearchAdapter searchAdapter;
    RecyclerView recyclerView;
    List<DBa> list;
    LinearLayoutManager layoutManager;
    int group;
    static final String TAG ="Find11";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        init();

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                searchAdapter.getFilter().filter(editText.getText().toString());
                //Log.d(TAG,editText.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    void init(){
        find_bt = findViewById(R.id.find);
        editText = findViewById(R.id.search_edit);
        back_bt = findViewById(R.id.back_bt);
        recyclerView = findViewById(R.id.find_list);
        find_bt.setOnClickListener(this);
        back_bt.setVisibility(View.VISIBLE);
        back_bt.setOnClickListener(this);
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            group=bundle.getInt("group");
        }
        list=DataSupport.where("num=?",group+"").find(DBa.class);
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        searchAdapter = new SearchAdapter(this,list);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(searchAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.find:
                break;
            case R.id.back_bt:
                Intent intent =new Intent(this,MainActivity.class);
                startActivity(intent);
                this.finish();
                break;
        }
    }
}
