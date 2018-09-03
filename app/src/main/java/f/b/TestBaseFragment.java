package f.b;

import android.view.View;

import m.b.base.BaseFragment;

/**
 * @author: FYL
 * @time: 2018/9/3
 * @email:347933430@qq.com
 * @describe: f.b
 */
public class TestBaseFragment extends BaseFragment {
    @Override
    public int bindLayout() {
        //绑定布局
        return 0;
    }
    @Override
    protected void initView() {
        //初始化控件，绑定监听
    }
    @Override
    protected void initData() {
        //绑定数据
//      showLog(3,"打印日志");//参数1：日志等级， 参数2：日志信息
//      showToast("显示吐司");//吐司
    }
    @Override
    public void widgetClick(View v) {
        //监听逻辑处理
    }
}
