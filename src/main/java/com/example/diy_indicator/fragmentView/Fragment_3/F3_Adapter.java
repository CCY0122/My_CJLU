package com.example.diy_indicator.fragmentView.Fragment_3;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.diy_indicator.R;
import com.example.diy_indicator.bean.BmobComment;

import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by Administrator on 2016/10/14.
 */

public class F3_Adapter extends RecyclerView.Adapter<mmViewHolder>{

    private List<BmobComment> data;
    private Context context;
    private LayoutInflater inflater;
    private View v;


    public F3_Adapter(Context context,List<BmobComment> data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public mmViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        v = inflater.inflate(R.layout.item_3,parent,false);
        return new mmViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final mmViewHolder holder, final int position) {
        BmobComment bean = data.get(position);
        Log.d("test",bean.getContent()+bean.getCreatedAt());
        holder.content.setText(bean.getContent()+"");
        holder.time.setText(bean.getCreatedAt()+"");
        holder.agreecount.setText(bean.getAgreeCount()+"");
        boolean flag = bean.isAgreeFlag();
        holder.agree.setImageResource(flag? R.drawable.agree_1 : R.drawable.agree);
        holder.agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobComment bmobComment = data.get(position);
                String id = bmobComment.getObjectId();
                int agreecount = bmobComment.getAgreeCount() + 1;
//                bmobComment.setValue("agreeCount",agreecount);
                bmobComment.setAgreeFlag(true);
                bmobComment.setAgreeCount(agreecount);
                bmobComment.update(id, new UpdateListener() {
                    @Override
                    public void done(BmobException e) {

                    }
                });
                holder.agreecount.setText("");
                holder.agree.setImageResource(R.drawable.agree_1);
                notifyDataSetChanged();
//                notifyItemChanged(position);
            }
        });
        holder.comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"即将推出",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
class mmViewHolder extends RecyclerView.ViewHolder{

    TextView content,time,agreecount;
    ImageView agree,comment;

    public mmViewHolder(View itemView) {
        super(itemView);
        content = (TextView) itemView.findViewById(R.id.com_content);
        time = (TextView) itemView.findViewById(R.id.com_time);
        agreecount = (TextView)itemView.findViewById(R.id.com_agree_count);
        agree = (ImageView) itemView.findViewById(R.id.com_agree);
        comment = (ImageView) itemView.findViewById(R.id.com_comment);

    }
}
