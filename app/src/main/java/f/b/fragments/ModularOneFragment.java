package f.b.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.Button;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import f.b.R;
import f.b.Smart.SmartRefreshActivity;
import f.b.card.CardViewActivity;
import f.base.BaseFragment;
import m.b.widget.AV;
import m.b.widget.FP;

/**
 * Created by DN on 2017/9/6.
 */

public class ModularOneFragment extends BaseFragment {
    @ViewInject(value = R.id.btn1)
    private Button btn1;
    @ViewInject(value = R.id.btn2)
    private Button btn2;
    @ViewInject(value = R.id.btn3)
    private Button btn3;
    @ViewInject(value = R.id.btn4)
    private Button btn4;
    @ViewInject(value = R.id.btn5)
    private Button btn5;
    @ViewInject(value = R.id.btn6)
    private Button btn6;
    @ViewInject(value = R.id.btn7)
    private Button btn7;
    @ViewInject(value = R.id.btn8)
    private Button btn8;
//    @ViewInject(value = R.id.btn9)
//    private Button btn9;
    @Override
    public int bindLayout() {
        return R.layout.modularone_layout;
    }
    @Override
    protected void initView() {
        x.view().inject(this,mContextView);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
//        btn9.setOnClickListener(this);
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
                AV.showPhoto(mContext, new AV.OnSelectClickListener() {
                    @Override
                    public void onSelectListener(int position) {
                        showToast("点击了第" + position + "个");
                    }
                });
                break;
            case R.id.btn6:
                AV.showSucess(mContext, "检查结果","没有任何毛病！");
                break;
            case R.id.btn7:
                AV.showSelectView(mContext,
                        "举报",//标题
                        new String[]{"色情"}, //红色选择字体
                        new String[]{"低俗","诈骗","赌博","与内容无关"},//蓝色选择字体
                        new AV.OnSelectClickListener() { //点击事件回调
                            @Override
                            public void onSelectListener(int position) {
                                showToast("点击了第" + position + "个");
                            }
                        });
                break;
            case R.id.btn8:
                Intent intent = new Intent(mContext, SmartRefreshActivity.class);
                startActivity(intent);
                break;
            case R.id.btn9:
//                startActivity(new Intent(mContext, CardViewActivity.class));
                break;
        }
    }
    @Override
    protected void initData() {

    }



}
