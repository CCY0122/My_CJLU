package com.example.diy_indicator.fragmentView.fragment_2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.diy_indicator.MainActivity;
import com.example.diy_indicator.R;
import com.example.diy_indicator.bean.Fragment_1_bean;
import com.example.diy_indicator.fragmentView.fragment_1.Fragment_1;
import com.example.diy_indicator.fragmentView.fragment_1.Fragment_1_adapter;
import com.example.diy_indicator.fragmentView.fragment_1.Fragment_1_db;
import com.example.diy_indicator.fragmentView.fragment_1.Fragment_1_dialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/8.
 */
public class Fragment_2 extends Fragment {

    private Fragment_1_dialog dialog;
    private Fragment_1 fragment_1;
    private Fragment_1_db fragment_1_db;

    private F2_Adapter f2_adapter;
    private RecyclerView recyclerView;
    private List<Fragment_1_bean> data = new ArrayList<>();
    private View v;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_view_2,container,false);

        initData();

        initView();


        return v;
    }

    private void initView() {
        fragment_1_db = new Fragment_1_db(getContext());
        f2_adapter = new F2_Adapter(getContext(),data);
        dialog = new Fragment_1_dialog();

        recyclerView = (RecyclerView) v.findViewById(R.id.f2_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(f2_adapter);

        f2_adapter.setOnClick(new F2_Adapter.OnClick() {
            @Override
            public void Click(Fragment_1_bean bean) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("note",bean);
                dialog.setArguments(bundle);
                dialog.show(getFragmentManager(),"2");
            }
        });
        dialog.setOnClick(new Fragment_1_dialog.OnClick() {
            @Override
            public void click(Fragment_1_bean bean) {
//                fragment_1 = (Fragment_1) getActivity().getSupportFragmentManager().findFragmentById(R.id.fragment_1);
                fragment_1 = (Fragment_1) ((MainActivity)getActivity()).getFragment(0);
                Log.d("ccy",""+fragment_1==null? "isnull":"notnull");
                fragment_1.addNewNote(bean);
                dialog.dismiss();
            }
        });
    }

    private void initData() {
        for (int i = 0; i <F2_Data.f2_Data.length; i++) {
                Fragment_1_bean bean = new Fragment_1_bean(
                        F2_Data.f2_Data[i][0], F2_Data.f2_Data[i][1], F2_Data.f2_Data[i][2]);
                data.add(bean);

        }
    }
}
