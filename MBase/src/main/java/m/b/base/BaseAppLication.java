package m.b.base;

import android.app.Application;

import org.xutils.x;

/**
 * @author: FYL
 * @time: 2018/9/3
 * @email:347933430@qq.com
 * @describe: base相关
 */

public  abstract class BaseAppLication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this); //关闭错误日志统计,默认开启
        x.Ext.setDebug(false); // 是否输出debug日志, BuildConfig.DEBUG开启debug会影响性能.
        init();
    }
    protected abstract void init();//初始抽象，抛给用户初始化自己的逻辑


}
