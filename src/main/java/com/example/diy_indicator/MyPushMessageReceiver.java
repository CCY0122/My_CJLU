package com.example.diy_indicator;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import cn.bmob.push.PushConstants;

/**
 * Created by Administrator on 2016/10/14.
 */

public class MyPushMessageReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d("bmob","有消息");
        if(intent.getAction().equals(PushConstants.ACTION_MESSAGE)){
            Log.d("bmob", "客户端收到推送内容："+intent.getStringExtra("msg"));

//            Toast.makeText(context,"push",Toast.LENGTH_SHORT).show();
            String jsonString = intent.getStringExtra("msg");
            String content = null;
            try {
                JSONObject jsonObject = new JSONObject(jsonString);
                content= jsonObject.getString("alert");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            Notification.Builder builder = new Notification.Builder(context);
            Intent intent1 = new Intent(context,MainActivity.class);
            intent1.putExtra("new",content);
            PendingIntent pendingIntent =PendingIntent.getActivity(context,0,intent1,PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setSmallIcon(R.drawable.edit);
            builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.drawable.edit));
            builder.setContentText(content);
            builder.setContentTitle("新消息~");
            builder.setAutoCancel(true);
            builder.setContentIntent(pendingIntent);
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            manager.notify(1,builder.build());
        }
    }

}
