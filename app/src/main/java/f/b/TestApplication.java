package f.b;

import m.b.Config;
import m.b.base.BaseAppLication;

/**
 * @author: FYL
 * @time: 2018/9/3
 * @email:347933430@qq.com
 * @describe:
 */
public class TestApplication extends BaseAppLication {
    /**
     * 如果有使用BaseNetActivity,带有Net的Base封装，
     * 或者有使用Xutils3的，必须继承BaseAppLication,否则会出现应用崩溃现象
     */
    @Override
    protected void init() {
        //初始化自己需要的逻辑
        Config.setBaiDuStatistics(false);//是否需要百度统计，默认false
        Config.setLog(true);//是否需要打印出日志，默认true
    }
}
