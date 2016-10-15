package com.example.diy_indicator.fragmentView.Fragment_3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.diy_indicator.R;
import com.example.diy_indicator.bean.BmobComment;
import com.example.diy_indicator.bean.UserComment;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by Administrator on 2016/10/15.
 */

public class CommentActivity extends AppCompatActivity {

    private String id;
    private BmobComment bmobComment;
    private ListView listView;
    private List<UserComment> data = new ArrayList<>();
    private mAdapter adapter;
    private EditText commentEdit;
    private Button commentBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment_layout);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        bmobComment = new BmobComment();
        bmobComment.setObjectId(id);

        commentEdit = (EditText) findViewById(R.id.comment_edit);
        commentBtn = (Button) findViewById(R.id.comment_btn);
        listView = (ListView) findViewById(R.id.comment_list_view);
        adapter = new mAdapter(this,data);
        listView.setAdapter(adapter);

        commentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = commentEdit.getText().toString();
                if(!content.isEmpty()){
                    pushComment(content);
                }else {
                    Toast.makeText(getApplicationContext(),"内容不要为空啦",Toast.LENGTH_SHORT).show();
                }
            }
        });


        query();


    }
    private void pushComment(String content){



        UserComment userComment = new UserComment();
        userComment.setComment(content);
        userComment.setBmobComment(bmobComment); //设置一对多关联 （一个帖子对应多条评论
        userComment.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if(e == null){
                    Toast.makeText(getApplicationContext(),"发表成功",Toast.LENGTH_SHORT).show();
                    commentEdit.setText("");

                }else {
                    Toast.makeText(getApplicationContext(),"发表失败T_T",Toast.LENGTH_SHORT).show();
                }
            }
        });
        data.add(userComment);
        if(adapter != null){
            adapter.notifyDataSetChanged();
        }
    }
    private void query(){
        BmobQuery<UserComment> query = new BmobQuery<>();

        query.addWhereEqualTo("bmobComment",new BmobPointer(bmobComment));
        query.findObjects(new FindListener<UserComment>() {
            @Override
            public void done(List<UserComment> list, BmobException e) {
                if(e==null){
                    for (UserComment comment1 : list){
                        data.add(comment1);
                    }
                    adapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(getApplicationContext(),"评论查询失败",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    public class mAdapter extends BaseAdapter{

        private List<UserComment> mData;
        private mViewHolder holder;
        private Context context;

        public mAdapter(Context context,List<UserComment> mData) {
            super();
            this.mData = mData;
            this.context = context;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null){
                convertView = LayoutInflater.from(context).inflate(R.layout.item_comment,null);
                holder = new mViewHolder();
                holder.comment_content = (TextView) convertView.findViewById(R.id.comment_content);
                holder.comment_tiem = (TextView) convertView.findViewById(R.id.comment_time);
                convertView.setTag(holder);
            }else {
                holder = (mViewHolder) convertView.getTag();
            }
            holder.comment_tiem.setText(mData.get(position).getCreatedAt()+"");
            holder.comment_content.setText(mData.get(position).getComment()+"");
            return convertView;
        }
    }
    class mViewHolder{
        TextView comment_tiem,comment_content;
    }
}
