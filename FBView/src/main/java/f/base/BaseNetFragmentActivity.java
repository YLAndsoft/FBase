package f.base;

import android.content.Context;

import java.util.List;

import f.base.Params;
import m.b.utils.GsonUtils;
import m.b.utils.NetworkUtils;
import m.b.utils.StringUtils;
import m.b.utils.XutilsHttp;
/**
 * @author: FYL
 * @time: 2018/9/3
 * @email:347933430@qq.com
 * @describe: base相关
 */
public abstract class BaseNetFragmentActivity extends BaseFragmentActivity {
    /**
     * 获取参数
     * @return
     */
    public abstract Params setParams();

    /**
     * 网络请求结果,最终结果会保存在Params中，和配置参数一起返回
     * @param params
     */
    public abstract void onNetResult(Params params);

    /**
     * 网络请求错误结果
     * @param error
     */
    public abstract void onNetError(String error);
    @Override
    public void initData(Context mContext) {
        final Params params = setParams();
        if(null==params){
            onNetError("未配置参数!");
            return;
        }
        if(!NetworkUtils.isConnected(mContext)){
            onNetError("请检查网络是否连接!");
            return;
        }
        XutilsHttp.xUtilsPost(params.getURL(), params.getMap(), new XutilsHttp.XUtilsCallBack() {
            @Override
            public void onResponse(String result) {
                if(StringUtils.isBlank(result)){
                    onNetError("请求结果:"+result);
                    return;
                }
                if(params.getClazz()==null){ //解析类型为空，自己去解析
                    params.setObj(result);
                    onNetResult(params);
                    return;
                }
                if(params.isList()){ //解析的返回结果类型是List
                    //解析错误，会返回解析前的数据
                    List gsonList = GsonUtils.getGsonList(result,params.getClazz());
                    if(gsonList==null){
                        params.setObj(result);
                        onNetError("解析结果:gsonList=null>>:"+result);
                        return;
                    }
                    params.setObj(gsonList);
                }else{ //解析的返回结果类型是bean
                    //解析错误，会返回解析前的数据
                    Object obj = GsonUtils.getGsonObject(result,params.getClazz());
                    if(obj==null){
                        params.setObj(result);
                        onNetError("解析结果:obj=null>>:"+result);
                        return;
                    }
                    params.setObj(obj);
                }
                onNetResult(params);
            }
            @Override
            public void onFail(String result) {
                onNetError("网络错误:"+result);
            }
        });
    }
}
