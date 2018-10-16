package f.b.fragments;

import android.view.View;
import android.widget.Button;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import f.b.R;
import f.b.cview.BezierCurve;
import f.base.BaseFragment;


/**
 * Created by DN on 2017/9/6.
 */

public class ModularThreeFragment extends BaseFragment {

    @ViewInject(value = R.id.bezier_curve)
    private BezierCurve bezier_curve;
    @ViewInject(value = R.id.refresh_curve)
    private Button refresh_curve;


    @Override
    public int bindLayout() {
        return R.layout.modularthree_layout;
    }

    @Override
    protected void initView() {
        x.view().inject(this,mContextView);
        refresh_curve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bezier_curve.setOrder(12);//设置阶数
                bezier_curve.start();//启动
            }
        });

    }


    @Override
    protected void initData() {
        bezier_curve.setOrder(6);//设置阶数
        bezier_curve.setLoop(false);//设置是否循环
        bezier_curve.setRate(10);//设置移动速度
        bezier_curve.setTangent(true);//设置是否显示切线
//        bezier_curve.start();//启动
    }

    @Override
    public void widgetClick(View v) {

    }
}
