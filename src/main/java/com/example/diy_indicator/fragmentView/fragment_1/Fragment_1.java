package com.example.diy_indicator.fragmentView.fragment_1;

import android.os.Bundle;
//import android.os.Message;

import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.diy_indicator.MyDataBase;
import com.example.diy_indicator.R;
import com.example.diy_indicator.bean.Fragment_1_bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/8.
 */
public class Fragment_1 extends Fragment{

    private FloatingActionButton fab;
    private View view;
    private RecyclerView recyclerView;
    private List<Fragment_1_bean> data;
    private Fragment_1_adapter adapter;
    private Fragment_1_dialog dialog;
    private Fragment_1_db fragment_1_db;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_view_1,container,false);

        initData();
        initView();


        return view;
    }

    private void initView() {

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        dialog = new Fragment_1_dialog();
        dialog.setOnClick(new Fragment_1_dialog.OnClick() {
            @Override
            public void click(Fragment_1_bean bean) {
               addNewNote(bean);
                dialog.dismiss();

            }
        });

        fab = (FloatingActionButton) view.findViewById(R.id.fab_1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show(getFragmentManager(),"1");
            }
        });

        adapter = new Fragment_1_adapter(getContext(),data);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());


    }

    private void initData(){
        fragment_1_db = new Fragment_1_db(getContext());
        data =fragment_1_db.qureyFromDB();
//        Log.d("db","data size = "+data.size());
    }

    public void addNewNote(Fragment_1_bean bean){
        recyclerView.smoothScrollToPosition(0);
        adapter.addData(fragment_1_db.addToDB(bean)); //db操作后返回的bean是有被修改的(加了id)
    }



}
