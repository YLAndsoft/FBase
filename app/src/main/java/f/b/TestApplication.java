package f.b;

import f.base.BaseConfig;
import m.b.base.BaseAppLication;
import m.b.db.DBManager;

/**
 * @author: FYL
 * @time: 2018/9/3
 * @email:347933430@qq.com
 * @describe:
 */
public class TestApplication extends BaseAppLication {
    /**
     * 有使用BaseNetActivity,带有Net的Base封装,要继承此类
     * 有使用Xutils3的相关的，必须继承BaseAppLication,否则会出现应用崩溃现象
     * 如果即使用了Xutils3,不想继承此类，记得自己初始化Xutils3
     */
    @Override
    protected void init() {
        //初始化自己需要的逻辑
        BaseConfig.setBaiDuStatistics(false);//是否需要百度统计，默认false
        BaseConfig.setLog(true);//是否需要打印出日志，默认true
        DBManager.initDB();//初始化数据库
    }
}
