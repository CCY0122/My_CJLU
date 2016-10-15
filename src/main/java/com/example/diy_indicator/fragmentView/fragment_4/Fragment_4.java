package com.example.diy_indicator.fragmentView.fragment_4;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.diy_indicator.R;
import com.example.diy_indicator.bean.AuthorBean;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administrator on 2016/10/15.
 */

public class Fragment_4 extends Fragment {

    private View v;
    private EditText editText1;
    private Button button1;
    private EditText editText2;
    private Button button2;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       v = inflater.inflate(R.layout.author,container,false);

        editText1 = (EditText) v.findViewById(R.id.author_edit_1);
        editText2 = (EditText) v.findViewById(R.id.author_edit_2);
        button1 = (Button) v.findViewById(R.id.author_btn_1);
        button2 = (Button) v.findViewById(R.id.author_btn_2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = editText1.getText().toString();
                push(a);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = editText2.getText().toString();
                push(a);
            }
        });

        return v;
    }
    private void push(String content){
        if(content.isEmpty()){
            return;
        }
        AuthorBean bean = new AuthorBean();
        bean.setContent(content);
        bean.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                Toast.makeText(getContext(),"谢谢",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
