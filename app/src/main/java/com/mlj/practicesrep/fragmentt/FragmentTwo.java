package com.mlj.practicesrep.fragmentt;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mlj.practicesrep.R;

public class FragmentTwo extends BaseFragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private TextView mFragementTwoTv;

    public FragmentTwo() {
    }

    public static FragmentTwo newInstance(String param1, String param2) {
        FragmentTwo fragment = new FragmentTwo();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.doCreateView();
        View rootView = inflater.inflate(R.layout.fragment_two, container, false);
        mFragementTwoTv = rootView.findViewById(R.id.fragementTwo_tv);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mFragementTwoTv.getParent();
        // 可以一直找到activity的原始布局
    }
}