package com.hsv.activity;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hvs.utils.ContentFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;
@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    /**
     * HorizontalScrollView:只有一个跟布局
     *
     * 1：固定
     * 2：每次从网络获取
     * 3：头一次从网络获取，以后从本地
     *
     * 1，横向滑动条
     * 2.viewpager+fragment
     * 3.联动
     */

    /**
     * 横向滑动栏
     */
    @ViewInject(R.id.hs_s)
    private HorizontalScrollView hsvTitle;
    /**
     * 横向标题布局。盛放一个个的textview
     */
@ViewInject(R.id.lytitle)
private LinearLayout llTitle;
    /**
     * 内容显示的控件
     */
    @ViewInject(R.id.vpcontent)
    private ViewPager vpContent;

    //存放所有textview的内容
    private List<String> titles;
    private List<Fragment> fragments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initDate();
        addTitles();
        vpContent.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });

        vpContent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //滑动改变颜色
                for(int i = 0;i<titles.size();i++){
                    TextView textview = (TextView) llTitle.getChildAt(i);
                    if(position==i){
                        textview.setTextColor(Color.RED);
                    }else{
                        textview.setTextColor(Color.BLACK);
                    }
                }
                int margins = 20 * position;
                int textwinth = 0;
                for (int j = 0;j<position;j++){
                    TextView textview = (TextView) llTitle.getChildAt(j);
                    textwinth += textview.getWidth();
                }
                //计算位置联动
                hsvTitle.scrollTo(margins+textwinth,0);



            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });





    }
    /*
    * 添加每个textview的内容并添加到LinearLayout中
    * */

    private void addTitles() {
        for(int i = 0;i<titles.size();i++){
            TextView text = new TextView(this);
            text.setText(titles.get(i));
            text.setTextSize(14);
            //给字体设置默认颜色
            if(i==0){
                text.setTextColor(Color.RED);
            }else{
                text.setTextColor(Color.BLACK);
            }

            text.setId(i+1000);
            //textview的点击事件
            text.setOnClickListener(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(10,5,10,5);
            text.setLayoutParams(params);

            llTitle.addView(text);

            Fragment fragment = ContentFragment.newInstance(titles.get(i));
            fragments.add(fragment);
        }
    }
/*
* 往每个textview中添加数据
* */
    private void initDate() {
        titles = new ArrayList<>();
        fragments = new ArrayList<>();

        titles.add("推荐");
        titles.add("热点");
        titles.add("科技");
        titles.add("视频");
        titles.add("数码");
        titles.add("社会");
        titles.add("汽车");
        titles.add("娱乐");
        titles.add("电影");
        titles.add("问答");
        titles.add("北京");
        titles.add("图片");
        titles.add("体育");
        titles.add("财经");
        titles.add("军事");
        titles.add("国际");
    }


    @Override
    public void onClick(View v) {
        //把选中textview的id的下标赋值给viewpager
        int id = v.getId();
       int  position = id-1000;
        vpContent.setCurrentItem(position);

        //点击改变颜色
        for(int i = 0;i<titles.size();i++){
            TextView textview = (TextView) llTitle.getChildAt(i);
            if(position==i){
                textview.setTextColor(Color.RED);
            }else{
                textview.setTextColor(Color.BLACK);
            }
        }


    }
}
