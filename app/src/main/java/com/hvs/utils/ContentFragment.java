package com.hvs.utils;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hsv.activity.R;

/**
 * 作者: 段晓红
 * 时间:2017/6/7
 * 描述:
 */
public class ContentFragment extends Fragment {
    private View view;
    private static final String KEY = "arg";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragments,container,false);


        return view;
    }
/*
* 当activity创建时赋值*/
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TextView textShow = (TextView) view.findViewById(R.id.textShow);
       String str = (String) getArguments().get(KEY);
        textShow.setText(str);
    }
    /*
    * 静态fragment与activity传值*/

    public static Fragment newInstance(String str){

        ContentFragment fragment = new ContentFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY,str);
        fragment.setArguments(bundle);
        return fragment;
    }
}
