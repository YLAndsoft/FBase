package f.base;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import java.util.List;
import m.b.R;

/**
 * @author: FYL
 * @time: 2018/9/6
 * @email:347933430@qq.com
 * @describe: m.b.base
 */
public abstract class BaseMainActivity extends MenuFragmentActivity {
    private int flResId = R.id.fl_menu_container;
    private int[] tabResIds = { R.id.iv_menu_0, R.id.iv_menu_1,R.id.iv_menu_2, R.id.iv_menu_3, R.id.iv_menu_4 };
    private int[] imgIds = { R.id.iv_menu_0, R.id.iv_menu_1, R.id.iv_menu_2,R.id.iv_menu_3,R.id.iv_menu_4};
    private ImageView[] imgBtn = new ImageView[imgIds.length];
    private int[] imageNormals;//未选中图标集合
    private int[] imgsHovers;//选中图标集合
    private List<Fragment> fragments;
    @Override
    public int bindLayout() {
        setSetActionBarColor(true,R.color.colorPrimary);
        return R.layout.base_main_activity;
    }
    @Override
    public void initView(View view) {
        super.initTab(tabResIds);
        imgsHovers = selectImags();
        imageNormals = unSelectImags();
        fragments = setFragments();
        if(null==fragments||fragments.size()<4){
            showLog(3,"tab对应的界面不能少于4个");
            return;
        }
        if(null==imgsHovers||imgsHovers.length<4){
            showLog(3,"选中图标不能少于4个");
            return;
        }
        if(null==imageNormals||imageNormals.length<4){
            showLog(3,"未选中图标不能少于4个");
            return;
        }
        for (int i = 0; i < fragments.size(); i++) {
            imgBtn[i] = (ImageView) findViewById(imgIds[i]);
            imgBtn[i].setImageResource(imageNormals[i]);
            imgBtn[i].setVisibility(View.VISIBLE);
        }
        imgBtn[0].setImageResource(imgsHovers[0]);
        //首次加载第一个Fragment
        switchFragment(flResId, fragments.get(0));
    }
    public abstract int[] selectImags();//图标
    public abstract int[] unSelectImags();//未选中图标
    public abstract List<Fragment> setFragments();//Fragment集合
    @Override
    protected boolean onTabClick(int tabId) {
        for (int i = 0; i < fragments.size(); i++) {
            imgBtn[i].setImageResource(imageNormals[i]);
        }
        super.onTabClick(tabId);
        if(tabId==imgIds[0]){
            imgBtn[0].setImageResource(imgsHovers[0]);
            switchFragment(flResId, fragments.get(0));
        }else if(tabId==imgIds[1]){
            imgBtn[1].setImageResource(imgsHovers[1]);
            switchFragment(flResId, fragments.get(1));
        }else if(tabId==imgIds[2]){
            imgBtn[2].setImageResource(imgsHovers[2]);
            switchFragment(flResId, fragments.get(2));
        }else if(tabId==imgIds[3]){
            imgBtn[3].setImageResource(imgsHovers[3]);
            switchFragment(flResId, fragments.get(3));
        }else if(tabId==imgIds[4]){
            imgBtn[4].setImageResource(imgsHovers[4]);
            switchFragment(flResId, fragments.get(4));
        }
        return true;
    }
    @Override
    public void initListener() {
    }

    @Override
    public void widgetClick(View v) {
    }

}
