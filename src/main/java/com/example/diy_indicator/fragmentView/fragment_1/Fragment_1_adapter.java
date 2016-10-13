package com.example.diy_indicator.fragmentView.fragment_1;

import android.content.Context;
import android.os.Message;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.diy_indicator.MyDataBase;
import com.example.diy_indicator.R;
import com.example.diy_indicator.bean.Fragment_1_bean;

import java.util.List;

/**
 * Created by Administrator on 2016/10/12.
 */

public class Fragment_1_adapter extends RecyclerView.Adapter<mViewHolder> {

    private static final int COLOR_DONE = 0xff7FFFD4;
    private static final int COLOR_NO_DONE = 0XFFFFAEB9;

    private List<Fragment_1_bean> mdata;
    private Context context;
    private LayoutInflater mInflater;
    private boolean sureDelete;
    private Fragment_1_db fragment_1_db;

    private android.os.Handler handler = new android.os.Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    sureDelete = false;
                    break;
            }
        }
    };

    public Fragment_1_adapter(Context context, List<Fragment_1_bean> data) {
        super();
        this.context = context;
        this.mdata = data;
        mInflater = LayoutInflater.from(context);
        fragment_1_db = new Fragment_1_db(context);
    }

    @Override
    public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mInflater.inflate(R.layout.item_1,parent,false);
        return new mViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final mViewHolder holder, int position) {
        holder.grade.setText(mdata.get(position).getGrade());
        holder.name.setText(mdata.get(position).getName());
        holder.content.setText(mdata.get(position).getContent());
        int x = mdata.get(position).getPos();
        holder.cardView.setCardBackgroundColor(x==1? COLOR_DONE:COLOR_NO_DONE);
//            holder.itemView.setBackgroundColor(x==1? COLOR_DONE:COLOR_NO_DONE);
        holder.delete.setOnClickListener(new View.OnClickListener() {  //点两下才删除，利用handler
            @Override
            public void onClick(View v) {
                int pos = holder.getLayoutPosition();
                if(sureDelete == false){
                    sureDelete = true;
                    Toast.makeText(context,"再按一次删除~",Toast.LENGTH_SHORT).show();
                    handler.sendEmptyMessageDelayed(1,1500);
                    return;
                }else {
                      Fragment_1_bean b = mdata.get(pos);
                       fragment_1_db.removeFromDB(b);
                        mdata.remove(pos);
                        notifyItemRemoved(pos);
                }
            }
        });
        holder.isDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    holder.itemView.setBackgroundColor(COLOR_DONE);
                holder.cardView.setCardBackgroundColor(COLOR_DONE);
                Fragment_1_bean bean = mdata.get(holder.getLayoutPosition());
                bean.setPos(1);
                fragment_1_db.updataPos(bean);
            }
        });
        holder.noDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    holder.itemView.setBackgroundColor(COLOR_NO_DONE);
                holder.cardView.setCardBackgroundColor(COLOR_NO_DONE);
                Fragment_1_bean bean = mdata.get(holder.getLayoutPosition());
                bean.setPos(0);
                fragment_1_db.updataPos(bean);
            }
        });
//            mdata.get(position).setPos(position);
//            Log.d("ccy",mdata.get(position).getPos()+"");
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public void addData(Fragment_1_bean bean){
        mdata.add(0,bean);
        notifyItemInserted(0);
//            notifyDataSetChanged();
    }

}
class mViewHolder extends RecyclerView.ViewHolder{

    TextView grade,name,content;
    TextView delete,isDone,noDone;
    CardView cardView;

    public mViewHolder(View itemView) {
        super(itemView);
        cardView = (CardView) itemView.findViewById(R.id.card_view);
        grade = (TextView) itemView.findViewById(R.id.grade);
        name = (TextView) itemView.findViewById(R.id.name);
        content = (TextView) itemView.findViewById(R.id.content);
        delete = (TextView) itemView.findViewById(R.id.delete);
        isDone = (TextView) itemView.findViewById(R.id.is_done);
        noDone = (TextView) itemView.findViewById(R.id.no_done);
    }

}
