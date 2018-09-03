package f.b;

import android.content.Context;
import android.view.View;

import m.b.base.BaseActivity;

/**
 * @author: FYL
 * @time: 2018/9/3
 * @email:347933430@qq.com
 * @describe: f.b
 */
public class TestBaseActivity extends BaseActivity {
    @Override
    public int bindLayout() {
        //绑定布局
        return R.layout.activity_main;
    }
    @Override
    public void initView(View view) {
        //初始化控件
    }
    @Override
    public void initListener() {
        //绑定监听事件
    }
    @Override
    public void initData(Context mContext) {
        //初始化，设置数据
    }
    @Override
    public void widgetClick(View v) {
        //点击事件，必须初始化绑定监听事件
        // if(v.getId()==R.id.toBlack){
            //点击返回
        //}
    }

}
