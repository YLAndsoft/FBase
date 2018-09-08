package f.b.fragments;
import android.content.Context;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import f.b.R;
import m.b.base.BaseMainActivity;


/**
 * @author: FYL
 * @time: 2018/9/6
 * @email:347933430@qq.com
 * @describe: m.b.base
 */
public class TestBaseMainActivity extends BaseMainActivity {
    /**模块1*/
    ModularOneFragment softwareFragment;
    /**模块2*/
    ModularTwoFragment gamesFragment;
    /**模块3*/
    ModularThreeFragment mianFragment;
    /**模块4*/
    ModularFourFragmentn manageFragmentn;
    /**模块5*/
    ModularFiveFragment pleasureFragment;
    @Override
    public void initData(Context mContext) {
        //这里做自己的逻辑处理等
    }
    //设置选中图标
    @Override
    public int[] selectImags() {
        int[] imgsHovers = {
                R.mipmap.ic_home_actionbar_select0,
                R.mipmap.ic_home_actionbar_select1,
                R.mipmap.ic_home_actionbar_select2,
                R.mipmap.ic_home_actionbar_select3,
                R.mipmap.ic_home_actionbar_select4};
        return imgsHovers;
    }
    //设置未选中图标
    @Override
    public int[] unSelectImags() {
        int[] imageNormals = {
                R.mipmap.ic_home_actionbar0,
                R.mipmap.ic_home_actionbar1,
                R.mipmap.ic_home_actionbar2,
                R.mipmap.ic_home_actionbar3,
                R.mipmap.ic_home_actionbar4};
        return imageNormals;
    }
    //设置要绑定的模块
    @Override
    public List<Fragment> setFragments() {
        return getListFragments();
    }

    private List<Fragment> getListFragments(){
        List<Fragment> list = new ArrayList<>();
        if (mianFragment == null) {
            mianFragment = new ModularThreeFragment();
        }
        if (gamesFragment == null) {
            gamesFragment = new ModularTwoFragment();
        }
        if (softwareFragment == null) {
            softwareFragment = new ModularOneFragment();
        }
        if (manageFragmentn == null) {
            manageFragmentn = new ModularFourFragmentn();
        }
        if (pleasureFragment == null) {
            pleasureFragment = new ModularFiveFragment();
        }
        list.add(mianFragment);
        list.add(gamesFragment);
        list.add(softwareFragment);
        list.add(manageFragmentn);
//        list.add(pleasureFragment);
        return list;
    }

}
