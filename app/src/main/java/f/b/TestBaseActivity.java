package f.b;

import android.content.Context;
import android.view.View;

import f.base.BaseActivity;

/**
 * @author: FYL
 * @time: 2018/9/3
 * @email:347933430@qq.com
 * @describe: f.b
 */
public class TestBaseActivity extends BaseActivity {
    @Override
    public int bindLayout() {//绑定布局
        //设置状态栏颜色
        setSetActionBarColor(true,R.color.colorPrimary);
        //是否允许屏幕旋转
        setScreenRoate(false);
        //是否设置沉浸状态栏
        setSteepStatusBar(true);
        //是否允许全屏
        setAllowFullScreen(true);
        return R.layout.activity_main;
    }
    @Override
    public void initView(View view) { //初始化控件
    }
    @Override
    public void initListener() { //绑定监听事件
    }
    @Override
    public void initData(Context mContext) { //初始化，设置数据
//        showLog(1,"打印日志");//参数1：日志等级， 参数2：日志信息
//        showToast("显示吐司");//吐司
    }
    @Override
    public void widgetClick(View v) {
        //点击事件，必须初始化绑定监听事件
        // if(v.getId()==R.id.toBlack){
            //点击返回
    }

}
