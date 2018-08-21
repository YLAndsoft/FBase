package f.b;

import m.b.Config;
import m.b.base.BaseAppLication;
import m.b.db.DBManager;

public class MyAppLication extends BaseAppLication {
    @Override
    protected void init() {
        Config.setBaiDuStatistics(false);//是否开启百度统计
        Config.setLog(true);//设置是否打印日志
        DBManager.initDB();//初始化数据库
    }
}
