package m.b;

/**
 * 工程配置文件
 * Created by DN on 2018/6/11.
 */

public class Config {
    private Config() {
        throw new AssertionError();
    }

    /**
     * 日志级别：verbose
     */
    public static final int LEVEL_1=1;//正常输出级别verbose
    /**
     * 日志级别：debug
     */
    public static final int LEVEL_2=2;//debug输出日志级别
    /**
     * 日志级别：error
     */
    public static final int LEVEL_3=3;//错误级别error


    public static boolean isBaiDuStatistics;
    public static boolean isLog=true;

    /**
     * 是否开启百度统计
     * @param baiDuStatistics
     */
    public static void setBaiDuStatistics(boolean baiDuStatistics) {
        isBaiDuStatistics = baiDuStatistics;
    }
    /**
     * 是输出打印日志
     * @param log
     */
    public static void setLog(boolean log) {
        isLog = log;
    }



}
