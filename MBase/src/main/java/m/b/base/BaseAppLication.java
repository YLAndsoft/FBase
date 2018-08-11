package m.b.base;

import android.app.Application;
import org.xutils.x;
import m.b.Config;

/**
 * Created by DN on 2018/5/28.
 */

public  abstract class BaseAppLication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this); //关闭错误日志统计,默认开启
        x.Ext.setDebug(false); // 是否输出debug日志, BuildConfig.DEBUG开启debug会影响性能.
        //Config.setBaiDuStatistics(false);
        //Config.setLog(true);
        //DBManager.initDB();//初始化数据库
    }
    protected abstract void init();//初始抽象，抛给用户初始化自己的逻辑

}
