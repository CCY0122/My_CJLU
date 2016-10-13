package com.example.diy_indicator.fragmentView.fragment_1;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.example.diy_indicator.R;
import com.example.diy_indicator.bean.Fragment_1_bean;

/**
 * Created by Administrator on 2016/10/12.
 */

public class Fragment_1_dialog extends DialogFragment {

    private View v;
    private EditText grade,name,content;
    private Button button;
    private OnClick onClick;
    private Fragment_1_bean initBean;


    public interface OnClick{
        void click(Fragment_1_bean bean);
    }
    public void  setOnClick(OnClick onClick){
        this.onClick = onClick;
    }

    public Fragment_1_dialog() {

        super();
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_1_dialog,container);
        initView();
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        Bundle bundle = getArguments();
        if(bundle != null){
            initBean = (Fragment_1_bean) bundle.getSerializable("note");
        }
        if(initBean != null && initBean.getName()!=null) { //任意内容不为空就说明是从f_2传来的
            Log.d("ccy","null?? ="+initBean.toString());
            grade.setText(initBean.getGrade());
            name.setText(initBean.getName());
            content.setText(initBean.getContent());
        }
    }

    private void initView() {
        grade = (EditText) v.findViewById(R.id.edit_grade);
        name = (EditText) v.findViewById(R.id.edit_name);
        content = (EditText) v.findViewById(R.id.edit_content);
        button = (Button) v.findViewById(R.id.edit_btn);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment_1_bean bean = new Fragment_1_bean(
                        grade.getText().toString(),name.getText().toString(),content.getText().toString(),0);
                grade.setText("");name.setText("");content.setText("");
                if(onClick != null){
                    onClick.click(bean);
                }
            }
        });
    }
    public void initText(Fragment_1_bean bean){
        initBean = bean;
    }
}
