package m.b.bean;

import java.io.Serializable;
import java.util.Map;

public class Params<T> implements Serializable {
    /**
     * 地址
     */
    private String URL;//地址
    /**
     * map参数
     */
    private Map<String,String> map;//map参数
    /**
     * 需要解析的数据类型
     */
    private Class<T> clazz;//需要解析的数据类型
    /**
     * 保存网络数据结果
     */
    private Object obj;//保存网络数据结果
    /**
     * 解析出来的结果是否集合数据
     */
    private boolean isList = false;//解析出来的结果是否集合数据
    /**
     * 是否是刷新请求
     */
    private boolean isRefresh = false;//是否是刷新请求
    /**
     * 是否是加载更多
     */
    private boolean isLoad = false;//是否是加载更多

    /**
     * 不需要解析的构造
     * @param url
     * @param map
     */
    public Params(String url,Map<String,String> map){
        this.URL = url;
        this.map=map;
    }

    /**
     * 解析构造
     * @param url
     * @param map
     * @param clazz
     * @param isList
     */
    public Params(String url,Map<String,String> map,Class<T> clazz,boolean isList){
        this.URL = url;
        this.map=map;
        this.clazz=clazz;
        this.isList = isList;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public Class<T> getClazz() {
        return clazz;
    }

    public void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public boolean isList() {
        return isList;
    }

    public void setList(boolean list) {
        isList = list;
    }

    public boolean isRefresh() {
        return isRefresh;
    }

    public void setRefresh(boolean refresh) {
        isRefresh = refresh;
    }

    public boolean isLoad() {
        return isLoad;
    }

    public void setLoad(boolean load) {
        isLoad = load;
    }
}
