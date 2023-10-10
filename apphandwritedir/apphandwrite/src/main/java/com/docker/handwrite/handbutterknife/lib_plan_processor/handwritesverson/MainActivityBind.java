package com.docker.handwrite.handbutterknife.lib_plan_processor.handwritesverson;

import com.docker.handwrite.R;
import com.docker.handwrite.handbutterknife.lib_plan_processor.aptuseverson.TestButterKnifeMainActivity;

/**
 * 实际应为生成的中间文件  这是需要反射的类 没有butterkinfer反射 字段 注解那种
 */
public class MainActivityBind {
    // 类之反射一次
    public MainActivityBind(TestButterKnifeMainActivity testButterKnifeMainActivity) {
        testButterKnifeMainActivity.mTextView
                = testButterKnifeMainActivity.findViewById(R.id.test_butter_knife_view);
    }
}


