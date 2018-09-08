package f.b;

import android.content.Context;
import android.view.View;

import f.base.BaseFragmentActivity;

/**
 * @author: FYL
 * @time: 2018/9/3
 * @email:347933430@qq.com
 * @describe: f.b
 */
public class TestBaseFragmentActivity extends BaseFragmentActivity {
    @Override
    public int bindLayout() {
        //绑定布局
        return 0;
    }
    @Override
    public void initView(View view) {
        //初始化控件
    }
    @Override
    public void initListener() {
        //绑定监听
    }
    @Override
    public void initData(Context mContext) {
        //绑定数据
    }
    @Override
    public void widgetClick(View v) {
        //监听逻辑处理
    }
}
