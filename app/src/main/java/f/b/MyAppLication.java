package f.b;

import m.b.base.BaseAppLication;
import m.b.db.DBManager;
import f.base.BaseConfig;

public class MyAppLication extends BaseAppLication {
    @Override
    protected void init() {
        BaseConfig.setBaiDuStatistics(true);//是否开启百度统计
        BaseConfig.setLog(true);//设置是否打印日志
        DBManager.initDB();//初始化数据库
    }
}
