package f.b;

import android.view.View;

import m.b.base.BaseLazyLoadFragment;

/**
 * @author: FYL
 * @time: 2018/9/3
 * @email:347933430@qq.com
 * @describe: f.b
 */
public class TestBaseLazyLoadFragment extends BaseLazyLoadFragment {

    /** 标志位，标志已经初始化完成 */
    private boolean isPrepared;
    /** 是否已被加载过一次，第二次就不再去请求数据了 */
    private boolean mHasLoadedOnce;
    @Override
    public int bindLayout() {//绑定布局
        return 0;
    }
    @Override
    protected void initView() {
        //初始化控件
        isPrepared=true;
    }
    @Override
    protected void initData() {
        if(!isVisible || !isPrepared || mHasLoadedOnce){
            return;
        }
        mHasLoadedOnce = true;//标识已经加载过
        //绑定数据
    }

    @Override
    public void widgetClick(View v) {
        //点击事件的逻辑处理
    }

}
