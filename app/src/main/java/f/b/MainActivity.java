package f.b;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;
import m.b.base.BaseActivity;
import m.b.widget.FP;
public class MainActivity extends BaseActivity {

    @ViewInject(value = R.id.btn1)
    Button btn1;
    @ViewInject(value = R.id.btn2)
    Button btn2;
    @ViewInject(value = R.id.btn3)
    Button btn3;
    @ViewInject(value = R.id.btn4)
    Button btn4;
    @ViewInject(value = R.id.btn5)
    Button btn5;
    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }
    @Override
    public void initView(View view) {
        x.view().inject(this);
    }
    @Override
    public void initListener() {
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
    }

    @Override
    public void initData(Context mContext) {
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.btn1:
                //加载中类型
                FP.showWithStatus(mContext,"加载中...");
                //使用自定义
                FP.showWithStatus(mContext,"加载中...", FP.FProgressHUDMaskType.Black);
                break;
            case R.id.btn2://提示框
                FP.showInfoWithStatus(mContext,"没有检测到网络!");
                //使用自定义
                FP.showInfoWithStatus(mContext,"没有检测到网络!", FP.FProgressHUDMaskType.Black);
                break;
            case R.id.btn3:
                //加载成功显示的视图
                FP.showSuccessWithStatus(mContext,"加载成功!");
                //使用自定义
                FP.showSuccessWithStatus(mContext,"加载成功!", FP.FProgressHUDMaskType.Black);
                break;
            case R.id.btn4:
                //加载失败显示的视图
                FP.showErrorWithStatus(mContext,"加载失败,网络错误!");
                //使用自定义
                FP.showErrorWithStatus(mContext,"加载失败,网络错误!", FP.FProgressHUDMaskType.Black);
                break;
            case R.id.btn5:
                break;
        }
    }

}
