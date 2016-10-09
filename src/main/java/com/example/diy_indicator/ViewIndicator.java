package com.example.diy_indicator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.diy_indicator.util.ScreenSize;

/**
 * Created by Administrator on 2016/10/8.
 * 自定义的顶部导航栏，底部指示器为一个小三角形
 * 可自定义一屏显示多少个tab标签（通过设置visibleItemCount）    ps：请不要让tab数少于visibleItemCount
 * 多出的部分会布局到屏幕外，通过滑动内容区域或点击tab或滑动导航栏时，会自动滑出其他的tab
 * 已解决点击与滑动的事件冲突
 *
 */
public class ViewIndicator extends LinearLayout {

    private static final int SELECTED_COLOR = 0XFFffffff;
    private static final int NORMAL_COLOR = 0XAAffffff;

    private Context context;

    private int triangleWidth;
    private int itemWidth;
    private int screenWidth;
    private int visibleItemCount;
    private int allItemCount;
    private int triangleTranslate;

    private ViewPager viewPager;
    private Paint paint;
    private Path path;

    public ViewIndicator(Context context) {
        this(context,null);
    }

    public ViewIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.WHITE);
        paint.setDither(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setPathEffect(new CornerPathEffect(3)); //圆角
        path = new Path();

        Log.d("ccy","构造");


    }

    private void scrollTriangle(int position, float positionOffset) {
        triangleTranslate = (int)(itemWidth*(position + positionOffset));
        if(allItemCount > visibleItemCount && position >= visibleItemCount-2 && position < allItemCount-2){
            scrollTo((int) (itemWidth*(position-(visibleItemCount-2)+positionOffset)),0);
        }
        invalidate();
    }

    public void setViewPager(ViewPager v){
        viewPager = v;

        if(viewPager != null){
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    scrollTriangle(position , positionOffset);
                }

                @Override
                public void onPageSelected(int position) {
                    resetItems();
                    View v =getChildAt(position);
                    ((TextView)v).setTextColor(SELECTED_COLOR);
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }
    }

    public void resetItems(){
        for (int i = 0; i < getChildCount(); i++) {
            View v = getChildAt(i);
            if(v instanceof TextView){
                ((TextView)v).setTextColor(NORMAL_COLOR);
            }
        }
    }

    private void setChildItemClick() {
        final int count =getChildCount();
        for(int i =0 ; i < count ;i++){
            final int j = i;
            View v = getChildAt(i);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(v instanceof TextView){
                        resetItems();
                        ((TextView) v).setTextColor(SELECTED_COLOR);
                        viewPager.setCurrentItem(j);
//                        Toast.makeText(context,"click"+j,Toast.LENGTH_SHORT).show();
                        Log.d("ccy2","onclick");
                    }
                }
            });
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.save();
        canvas.translate(itemWidth/2 - triangleWidth/2 + triangleTranslate  ,getMeasuredHeight()+5);
        canvas.drawPath(path,paint);
        canvas.restore();
        Log.d("ccy","dispatchDraw");

        super.dispatchDraw(canvas);

    }

    private void initPath() {
        path.moveTo(0,0);
        path.lineTo(triangleWidth,0);
        path.lineTo(triangleWidth/2 , -triangleWidth/2);
        path.close();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        triangleTranslate = 0;
        allItemCount = getChildCount();
        visibleItemCount = 4;
        screenWidth = ScreenSize.getScreenSize(context).x;
        itemWidth = screenWidth / visibleItemCount;
        triangleWidth = itemWidth / 6 ;
        getLayoutParams().width = itemWidth *allItemCount;
        requestLayout();
        initPath();
        Log.d("ccy","onsizechanged"+w+"-"+h+"--"+oldw+"-"+oldh);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        setChildItemClick();
        Log.d("ccy","onfinishinflate");

    }
    int lastx;
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercepted = false;
        int x = (int) ev.getX();

        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                intercepted = false;
                break;
            case MotionEvent.ACTION_MOVE:
                int touchSlop =ViewConfiguration.get(context).getScaledTouchSlop();
                Log.d("ccy","touchSlop="+touchSlop);
                //如果x轴滑动大于最小滑动  则拦截点击事件 允许滑动
                if(Math.abs(x - lastx ) >=touchSlop){
                    intercepted = true;
                }else{
                    intercepted = false;
                }
                break;
            case MotionEvent.ACTION_UP:
                intercepted = false;
                break;
        }
        lastx = x;
        return intercepted;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - lastx;
                if(getScrollX()<0 || getScrollX()>getLayoutParams().width-screenWidth) {
                    scrollBy(-deltaX/3, 0); //如果滑动超出两边边界则加点阻力感并在UP时恢复到边界
                }else {
                    scrollBy(-deltaX, 0);
                }
                Log.d("ccy1",""+getScrollX() );
                Log.d("ccy1","----"+getLayoutParams().width);
                break;
            case MotionEvent.ACTION_UP:
                if(getScrollX()<0) {
                    scrollTo(0, 0);
                }else if(getScrollX()>getLayoutParams().width-screenWidth){
                    scrollTo(getLayoutParams().width-screenWidth , 0);
                }
                break;
        }
        lastx =x;

        Log.d("ccy","ontouch");

        return true;
    }
}
