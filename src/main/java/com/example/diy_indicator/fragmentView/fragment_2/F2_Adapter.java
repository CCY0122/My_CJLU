package com.example.diy_indicator.fragmentView.fragment_2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.diy_indicator.R;
import com.example.diy_indicator.bean.Fragment_1_bean;
import com.example.diy_indicator.fragmentView.fragment_1.Fragment_1_dialog;

import java.util.List;

/**
 * Created by Administrator on 2016/10/13.
 */

public class F2_Adapter extends RecyclerView.Adapter<mViewHolder> {

    private View v;
    private Context context;
    private List<Fragment_1_bean> data;
    private LayoutInflater inflater;
    private OnClick onClick;


    public F2_Adapter(Context context, List<Fragment_1_bean> data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    public interface OnClick{
        void Click(Fragment_1_bean bean);
    }
    public void setOnClick(OnClick onClick){
        this.onClick = onClick;
    }

    @Override
    public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        v = inflater.inflate(R.layout.item_2,parent,false);

        return new mViewHolder(v);
    }

    @Override
    public void onBindViewHolder(mViewHolder holder, int position) {
        final Fragment_1_bean bean =data.get(position);
//        Log.d("ccy",bean.toString());
        holder.grade.setText(bean.getGrade());
        holder.name.setText(bean.getName());
        holder.content.setText(bean.getContent());
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onClick != null){
                    onClick.Click(bean);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
class mViewHolder extends RecyclerView.ViewHolder{
     TextView grade,name,content;
     Button btn;

    public mViewHolder(View itemView) {
        super(itemView);
        grade = (TextView) itemView.findViewById(R.id.this_grade);
        name = (TextView) itemView.findViewById(R.id.this_name);
        content = (TextView) itemView.findViewById(R.id.this_content);
        btn = (Button) itemView.findViewById(R.id.this_btn);
    }
}
