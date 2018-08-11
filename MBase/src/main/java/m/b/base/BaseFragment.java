package m.b.base;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.baidu.mobstat.StatService;
import m.b.Config;

/**
 * Created by DN on 2017/7/22.
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener{
    protected View mContextView = null;
    protected Context mContext;
    protected  boolean isLoadData;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = getActivity();

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        try{
            mContextView = inflater.inflate(bindLayout(), null, false);//绑定布局
        }catch (Exception ex){
            showToast("绑定布局异常");
            ex.printStackTrace();
            return null;
        }
        initView();//初始化控件
        initData();//加载数据
        return mContextView;
    }

    /**
     * [绑定布局]
     *
     * @return
     */
    public abstract int bindLayout();

    /**
     * 控件的初始化
     */
    protected abstract void initView();

    /**
     * 数据显示
     */
    protected abstract void initData();


    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    /** View点击 **/
    public abstract void widgetClick(View v);

    @Override
    public void onClick(View v) {
        widgetClick(v);
    }

    /**
     * [吐出Toast]
     * @param msg
     */
    protected void showToast(String msg){
            if(null!=getActivity()){
                Toast.makeText(getActivity(),msg, Toast.LENGTH_SHORT).show();
            }
    }

    /**
     * [打印日志]
     * @param msg
     */
    public static final String TAG1 = "FragmentLog信息：";
    protected void showLog(int level,String msg){
        if(!Config.isLog){return;}
        switch (level){
            case Config.LEVEL_1:
                Log.v(TAG1,msg);
                break;
            case Config.LEVEL_2:
                Log.d(TAG1,msg);
                break;
            case Config.LEVEL_3:
                Log.e(TAG1,msg);
                break;
            default:
                Log.i(TAG1,msg);
                break;
        }
    }



    @Override
    public void onStart() {
        super.onStart();
        if(Config.isBaiDuStatistics)StatService.onPageStart(mContext, "BaseFragment");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(Config.isBaiDuStatistics)StatService.onPageEnd(mContext, "BaseFragment");
    }

}
