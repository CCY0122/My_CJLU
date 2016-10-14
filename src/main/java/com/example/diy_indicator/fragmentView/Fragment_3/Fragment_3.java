package com.example.diy_indicator.fragmentView.Fragment_3;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.diy_indicator.R;
import com.example.diy_indicator.bean.BmobComment;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administrator on 2016/10/8.
 */
public class Fragment_3 extends Fragment {

    private View v;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private F3_Adapter adapter;
    private List<BmobComment> data = new ArrayList<>();

    private EditText pushEdit;
    private Button pushBtn;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    Log.d("ccy22","handler excute");
                    if(swipeRefreshLayout !=null && adapter !=null){
                        swipeRefreshLayout.setRefreshing(false);
                        adapter.notifyDataSetChanged();
                    }

                    break;
            }
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.fragment_view_3,container,false);


        initView();

//        initData();


        return v;
    }

    private void initData() {
        refresh();

    }

    private void initView() {
        recyclerView = (RecyclerView) v.findViewById(R.id.f3_recyclerview);
        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipe_refresh);
        pushEdit = (EditText) v.findViewById(R.id.com_edit);
        pushBtn = (Button) v.findViewById(R.id.com_btn);
        adapter = new F3_Adapter(getContext(),data);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout.setColorSchemeColors(0xffff6060,0xff000000);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
               refresh();
            }
        });
        pushBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String c = pushEdit.getText().toString();
                if(!c.isEmpty()){
                    BmobComment b = new BmobComment();
                    b.setContent(c);
                    b.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if(e == null){
//                                Toast.makeText(getContext(),"success",Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(getContext(),"failed",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    data.add(0,b);
                    adapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(getContext(),"内容不要为空",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    private void refresh(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                BmobQuery<BmobComment> query = new BmobQuery<>();
                query.setLimit(30);
                query.order("-createdAt");
                query.findObjects(new FindListener<BmobComment>() {
                    @Override
                    public void done(List<BmobComment> list, BmobException e) {
                        if(e == null){
                            data.clear();
//                            Toast.makeText(getContext(),"refresh sucess",Toast.LENGTH_SHORT).show();
                            for(BmobComment bean : list) {
                                data.add(bean);
                            }
                            handler.sendEmptyMessage(1);
                        }else {
                            Toast.makeText(getContext(),"刷新失败 T_T，请检查网络",Toast.LENGTH_SHORT).show();
                            if (swipeRefreshLayout != null){
                                swipeRefreshLayout.setRefreshing(false);
                            }

                        }

                    }
                });
            }
        }).run();
    }
}
