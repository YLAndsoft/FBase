package f.b.fragments;

import android.view.View;
import org.xutils.x;

import f.b.R;
import m.b.base.BaseFragment;

/**
 * Created by DN on 2017/9/6.
 */

public class ModularOneFragment extends BaseFragment {

    @Override
    public int bindLayout() {
        return R.layout.modularone_layout;
    }
    @Override
    protected void initView() {
        x.view().inject(this,mContextView);

    }
    @Override
    public void widgetClick(View v) {
    }
    @Override
    protected void initData() {

    }




}
