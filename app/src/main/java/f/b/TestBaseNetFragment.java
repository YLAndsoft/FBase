package f.b;

import android.view.View;

import java.util.HashMap;
import java.util.Map;

import m.b.base.BaseNetFragment;
import m.b.base.Params;

/**
 * @author: FYL
 * @time: 2018/9/3
 * @email:347933430@qq.com
 * @describe: f.b
 */
public class TestBaseNetFragment extends BaseNetFragment {
    @Override
    public Params setParams() {
        //初始化网络请求参数
        Map<String,String> map = new HashMap<String, String>();
        map.put("type","1");
        Params params = new Params("https://...",null);
        //params.setClazz(String.class);//要解析的数据类型，也可以是实体类
        //params.setList(true);//解析的结果是否是List数据集合类型，默认false
        //params.setLoad(true);//是否是加载更多请求，默认false
        //params.setRefresh(true);//是否是刷新请求,默认false
        //注意：如果不需要解析，params.setClazz(null)即可，
        // 最后返回的数据为String，JSON原始数据，需要自己去解析
        return params;
    }
    @Override
    public void onNetResult(Params params) {
        //网络请求结果回调，结果值在params.getObj(),
    }
    @Override
    public void onNetError(String error) {
        //网络请求错误回调，错误信息：error
    }
    //以下的几个方法就是BaseFragment里的几个方法，这里就不多介绍了.

    @Override
    public int bindLayout() {
        return 0;
    }
    @Override
    protected void initView() {
    }
    @Override
    public void widgetClick(View v) {
    }
}
