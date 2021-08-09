package com.mlj.practicesrep.fragmentt;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.mlj.practicesrep.R;

import java.util.ArrayList;

public class ViewpagerFragmentActivity extends AppCompatActivity {

    private ViewPager mViewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager_fragment);
        mViewpager = findViewById(R.id.viewpager);
//      mViewpager.setAdapter(new FragmentAdapter(getSupportFragmentManager()));
//      mViewpager.setAdapter(new FragmentStateAdapter(getSupportFragmentManager()));
        mViewpager.setAdapter(new FragmentStateAdapterX(getSupportFragmentManager()));
    }
}

class Adapter extends PagerAdapter {

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return false;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
    }
}

class FragmentAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> fragmentArrayList = new ArrayList<Fragment>() {
        {
            add(FragmentOne.newInstance("页面1", ""));
            add(FragmentOne.newInstance("页面2", ""));
            add(FragmentOne.newInstance("页面3", ""));
            add(FragmentOne.newInstance("页面4", ""));
            add(FragmentOne.newInstance("页面5", ""));
        }
    };

    public FragmentAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return 5;
    }
}

class FragmentStateAdapter extends FragmentStatePagerAdapter {

    ArrayList<Fragment> fragmentArrayList = new ArrayList<Fragment>() {
        {
            add(FragmentOne.newInstance("页面1", ""));
            add(FragmentOne.newInstance("页面2", ""));
            add(FragmentOne.newInstance("页面3", ""));
            add(FragmentOne.newInstance("页面4", ""));
            add(FragmentOne.newInstance("页面5", ""));
        }
    };

    public FragmentStateAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return 5;
    }
}

// 就是为了实现懒加载
class FragmentStateAdapterX extends FragmentStatePagerAdapter {
    /*
    //第一个页面展示 offset=1 behavior statePageAdapter
    D/docker:FragmentOne{第一个}: onAttach:
    D/docker:FragmentOne{第一个}: onCreate:
    D/docker:FragmentOne{第一个}: onCreateView:
    D/docker:FragmentOne{第一个}: onViewCreated:
    D/docker:FragmentOne{第一个}: onActivityCreated:
    D/docker:FragmentOne{第一个}: onViewStateRestored:
    D/docker:FragmentOne{第一个}: onStart:
    D/docker:FragmentOne{第二个}: onAttach:
    D/docker:FragmentOne{第二个}: onCreate:
    D/docker:FragmentOne{第二个}: onCreateView:
    D/docker:FragmentOne{第二个}: onViewCreated:
    D/docker:FragmentOne{第二个}: onActivityCreated:
    D/docker:FragmentOne{第二个}: onViewStateRestored:
    D/docker:FragmentOne{第二个}: onStart:
    D/docker:FragmentOne{第一个}: onResume:

// 展示第二个页面
    D/docker:FragmentOne{第三个}: onAttach:
    D/docker:FragmentOne{第三个}: onCreate:
    D/docker:FragmentOne{第三个}: onCreateView:
    D/docker:FragmentOne{第三个}: onViewCreated:
    D/docker:FragmentOne{第三个}: onActivityCreated:
    D/docker:FragmentOne{第三个}: onViewStateRestored:
    D/docker:FragmentOne{第三个}: onStart:
    D/docker:FragmentOne{第一个}: onPause:
    D/docker:FragmentOne{第二个}: onResume:

// 展示第三个页面
    D/docker:FragmentOne{第一个}: onSaveInstanceState:
    D/docker:FragmentOne{第一个}: onStop:
    D/docker:FragmentOne{第四个}: onAttach:
    D/docker:FragmentOne{第四个}: onCreate:
    D/docker:FragmentOne{第四个}: onCreateView:
    D/docker:FragmentOne{第四个}: onViewCreated:
    D/docker:FragmentOne{第四个}: onActivityCreated:
    D/docker:FragmentOne{第四个}: onViewStateRestored:
    D/docker:FragmentOne{第四个}: onStart:
    D/docker:FragmentOne{第二个}: onPause:
    D/docker:FragmentOne{第三个}: onResume:
    D/docker:FragmentOne{第一个}: onDestroyView:
    D/docker:FragmentOne{第一个}: onDestroy:
    D/docker:FragmentOne{第一个}: onDetach:
     */
    ArrayList<Fragment> fragmentArrayList = new ArrayList<Fragment>() {
        {
            add(FragmentOne.newInstance("页面1", ""));
            add(FragmentOne.newInstance("页面2", ""));
            add(FragmentOne.newInstance("页面3", ""));
            add(FragmentOne.newInstance("页面4", ""));
            add(FragmentOne.newInstance("页面5", ""));
        }
    };

    public FragmentStateAdapterX(@NonNull FragmentManager fm) {
        super(fm, FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return 5;
    }
}


/*

参考 https://www.cnblogs.com/lianghui66/p/3607091.html  viewpager notify不能更新页面，只能更新添加和删除的处理

// ViewPager +  FragmentPageAdapter（超过offset范围的 开始执行onPause只会执行到destroyView，重新进入范围onCreateView） offset=1
// FragmentStatePageAdapter（会执行到onDetech，重新进入范围重新attach）
// 都还是原来的对象地址，只是重新走生命周期了

下面是FragmentPageAdapter
// 展示第一个页面
        D/docker:FragmentOne{第一个} : setUserVisibleHint() isVisibleToUser = [false]
        D/docker:FragmentOne{第二个} : setUserVisibleHint() isVisibleToUser = [false] //创建实例的时候，首先调用每个Fragment，设置为false
        D/docker:FragmentOne{第一个} : setUserVisibleHint() isVisibleToUser = [true] // 再次设置数据 为true
        D/docker:FragmentOne{第一个} : onAttach:
        D/docker:FragmentOne{第一个} : onCreate:
        D/docker:FragmentOne{第一个} : onCreateView:
        D/docker:FragmentOne{第一个} : onViewCreated:
        D/docker:FragmentOne{第一个} : onActivityCreated:
        D/docker:FragmentOne{第一个} : onViewStateRestored:
        D/docker:FragmentOne{第一个} : onStart:
        D/docker:FragmentOne{第二个} : onAttach:
        D/docker:FragmentOne{第二个} : onCreate:
        D/docker:FragmentOne{第二个} : onCreateView:
        D/docker:FragmentOne{第二个} : onViewCreated:
        D/docker:FragmentOne{第二个} : onActivityCreated:
        D/docker:FragmentOne{第二个} : onViewStateRestored:
        D/docker:FragmentOne{第一个} : onResume:
        D/docker:FragmentOne{第二个} : onStart:
        D/docker:FragmentOne{第二个} : onResume:

// 展示第二个页面
        D/docker:FragmentOne{第三个} : setUserVisibleHint() isVisibleToUser = [false]
        D/docker:FragmentOne{第一个} : setUserVisibleHint() isVisibleToUser = [false]
        D/docker:FragmentOne{第二个} : setUserVisibleHint() isVisibleToUser = [true] // 后续切换的时候，每个页面都调用该方法
        D/docker:FragmentOne{第三个} : onAttach:
        D/docker:FragmentOne{第三个} : onCreate:
        D/docker:FragmentOne{第三个} : onCreateView:
        D/docker:FragmentOne{第三个} : onViewCreated:
        D/docker:FragmentOne{第三个} : onActivityCreated:
        D/docker:FragmentOne{第三个} : onViewStateRestored:
        D/docker:FragmentOne{第三个} : onStart:
        D/docker:FragmentOne{第三个} : onResume:

// 展示第三个页面
        D/docker:FragmentOne{第四个} : setUserVisibleHint() isVisibleToUser = [false]
        D/docker:FragmentOne{第二个} : setUserVisibleHint() isVisibleToUser = [false]
        D/docker:FragmentOne{第三个} : setUserVisibleHint() isVisibleToUser = [true]
        D/docker:FragmentOne{第一个} : onPause:  //注意这里的方法，是在Fragment销毁的时候，才走的pause stop onDestroyView
        D/docker:FragmentOne{第一个} : onStop:
        D/docker:FragmentOne{第四个} : onAttach:
        D/docker:FragmentOne{第四个} : onCreate:
        D/docker:FragmentOne{第四个} : onCreateView:
        D/docker:FragmentOne{第四个} : onViewCreated:
        D/docker:FragmentOne{第四个} : onActivityCreated:
        D/docker:FragmentOne{第四个} : onViewStateRestored:
        D/docker:FragmentOne{第一个} : onDestroyView:  // 滑到最左边界的时候，第三个会destroyView，不会重新调用第三个的setUserVisibleHint
        D/docker:FragmentOne{第四个} : onStart:
        D/docker:FragmentOne{第四个} : onResume:

// home 然后 回来
        D/docker:FragmentOne{927a706} (b3b2ac92-e694-41f9-8d46-7e046a0bbb34): onPause:
        D/docker:FragmentOne{f6a21c7} (6272bfcd-586f-46ac-a18e-10683c10f9ab): onPause:
        D/docker:FragmentOne{d6533f4} (ef728344-2b0d-4f81-927e-e3729fd00d55): onPause:
        D/docker:FragmentOne{927a706} (b3b2ac92-e694-41f9-8d46-7e046a0bbb34): onStop:
        D/docker:FragmentOne{f6a21c7} (6272bfcd-586f-46ac-a18e-10683c10f9ab): onStop:
        D/docker:FragmentOne{d6533f4} (ef728344-2b0d-4f81-927e-e3729fd00d55): onStop:
        D/docker:FragmentOne{927a706} (b3b2ac92-e694-41f9-8d46-7e046a0bbb34): onSaveInstanceState:
        D/docker:FragmentOne{f6a21c7} (6272bfcd-586f-46ac-a18e-10683c10f9ab): onSaveInstanceState:
        D/docker:FragmentOne{第一个} : onSaveInstanceState:
        D/docker:FragmentOne{d6533f4} (ef728344-2b0d-4f81-927e-e3729fd00d55): onSaveInstanceState:

        D/docker:FragmentOne{927a706} (b3b2ac92-e694-41f9-8d46-7e046a0bbb34): onStart:
        D/docker:FragmentOne{f6a21c7} (6272bfcd-586f-46ac-a18e-10683c10f9ab): onStart:
        D/docker:FragmentOne{d6533f4} (ef728344-2b0d-4f81-927e-e3729fd00d55): onStart:
        D/docker:FragmentOne{927a706} (b3b2ac92-e694-41f9-8d46-7e046a0bbb34): onResume:
        D/docker:FragmentOne{f6a21c7} (6272bfcd-586f-46ac-a18e-10683c10f9ab): onResume:
        D/docker:FragmentOne{d6533f4} (ef728344-2b0d-4f81-927e-e3729fd00d55): onResume:

// 退出ACTIVITY
        D/docker:FragmentOne{927a706} (b3b2ac92-e694-41f9-8d46-7e046a0bbb34): onPause:
        D/docker:FragmentOne{f6a21c7} (6272bfcd-586f-46ac-a18e-10683c10f9ab): onPause:
        D/docker:FragmentOne{d6533f4} (ef728344-2b0d-4f81-927e-e3729fd00d55): onPause:
        D/docker:FragmentOne{927a706} (b3b2ac92-e694-41f9-8d46-7e046a0bbb34): onStop:
        D/docker:FragmentOne{f6a21c7} (6272bfcd-586f-46ac-a18e-10683c10f9ab): onStop:
        D/docker:FragmentOne{d6533f4} (ef728344-2b0d-4f81-927e-e3729fd00d55): onStop:
        D/docker:FragmentOne{第一个} : onDestroy:
        D/docker:FragmentOne{第一个} : onDetach:  //！！！！！ FragmentPageAdapter 时候所以第一个（前面所有）fragement还没有走销毁的方法，只是销毁的View。
        D/docker:FragmentOne{927a706} (b3b2ac92-e694-41f9-8d46-7e046a0bbb34): onDestroyView:
        D/docker:FragmentOne{927a706} (b3b2ac92-e694-41f9-8d46-7e046a0bbb34): onDestroy:
        D/docker:FragmentOne{927a706} (b3b2ac92-e694-41f9-8d46-7e046a0bbb34): onDetach:
        D/docker:FragmentOne{f6a21c7} (6272bfcd-586f-46ac-a18e-10683c10f9ab): onDestroyView:
        D/docker:FragmentOne{f6a21c7} (6272bfcd-586f-46ac-a18e-10683c10f9ab): onDestroy:
        D/docker:FragmentOne{f6a21c7} (6272bfcd-586f-46ac-a18e-10683c10f9ab): onDetach:
        D/docker:FragmentOne{d6533f4} (ef728344-2b0d-4f81-927e-e3729fd00d55): onDestroyView:
        D/docker:FragmentOne{d6533f4} (ef728344-2b0d-4f81-927e-e3729fd00d55): onDestroy:
        D/docker:FragmentOne{d6533f4} (ef728344-2b0d-4f81-927e-e3729fd00d55): onDetach:
 */