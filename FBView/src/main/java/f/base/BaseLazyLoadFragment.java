package f.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.baidu.mobstat.StatService;

import f.base.BaseConfig;

/**
 * @author: FYL
 * @time: 2018/9/3
 * @email:347933430@qq.com
 * @describe: base相关
 */

public abstract class BaseLazyLoadFragment extends Fragment implements View.OnClickListener {
    protected View mContextView = null;
    protected Context mContext;
    protected boolean isVisible;//界面是否可见
    protected boolean isLoadData;//是否还有数据

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = getActivity();
    }

    /**
     * 此方法是在onCreateView之前调用
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            initData();
        } else {
            isVisible = false;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        try {
            mContextView = inflater.inflate(bindLayout(), null, false);
        } catch (Exception ex) {
            showToast("绑定布局异常！");
            ex.printStackTrace();
            return null;
        }
        initView();//初始化控件
        if (isVisible) {
            initData();
        }
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

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mContext = activity;
    }

    /**
     * View点击
     **/
    public abstract void widgetClick(View v);

    @Override
    public void onClick(View v) {
        widgetClick(v);
    }

    /**
     * [吐出Toast]
     *
     * @param msg
     */
    protected void showToast(String msg) {
        if (null != getActivity()) {
            Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * [打印日志]
     *
     * @param msg
     */
    protected final String TAG = "LazyLoadFragmentLog信息：";

    protected void showLog(int level, String msg) {
        if (!BaseConfig.isLog) {
            return;
        }
        switch (level) {
            case BaseConfig.LEVEL_1:
                Log.v(TAG, msg);
                break;
            case BaseConfig.LEVEL_2:
                Log.d(TAG, msg);
                break;
            case BaseConfig.LEVEL_3:
                Log.e(TAG, msg);
                break;
            default:
                Log.i(TAG, msg);
                break;
        }
    }

    public void setEnableLoadData(boolean isLoadData) {
        this.isLoadData = isLoadData;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (BaseConfig.isBaiDuStatistics) StatService.onPageStart(mContext, "BaseFragment");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (BaseConfig.isBaiDuStatistics) StatService.onPageEnd(mContext, "BaseFragment");
    }

}
