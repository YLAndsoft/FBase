package f.b.fragments;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import f.b.MainActivity;
import f.b.R;
import f.base.BaseMainActivity;
import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;


/**
 * @author: FYL
 * @time: 2018/9/6
 * @email:347933430@qq.com
 * @describe: m.b.base
 */
public class TestBaseMainActivity extends BaseMainActivity {
    /**模块1*/
    ModularOneFragment mianFragment;
    /**模块2*/
    ModularTwoFragment gamesFragment;
    /**模块3*/
    ModularThreeFragment softwareFragment;
    /**模块4*/
    ModularFourFragmentn manageFragmentn;
    /**模块5*/
    ModularFiveFragment pleasureFragment;

    private static final int PHOTO_PERMISS = 111;
    /*if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            PermissionGen.with(TestBaseMainActivity.this)
                    .addRequestCode(PHOTO_PERMISS)
                    .permissions(
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.READ_PHONE_STATE)
                    .request();
        }*/
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
        return getListFragments();//获取Fragment集合
    }

    private List<Fragment> getListFragments(){
        List<Fragment> list = new ArrayList<>();
        if (mianFragment == null) {
            mianFragment = new ModularOneFragment();
        }
        if (softwareFragment == null) {
            softwareFragment = new ModularThreeFragment();
        }
        if (gamesFragment == null) {
            gamesFragment = new ModularTwoFragment();
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }
    @PermissionSuccess(requestCode = PHOTO_PERMISS)
    public void requestPhotoSuccess(){
        //成功之后的处理
    }

    @PermissionFail(requestCode = PHOTO_PERMISS)
    public void requestPhotoFail(){
        //失败之后的处理，我一般是跳到设置界面
        goToSetting(TestBaseMainActivity.this);
    }

    /***
     * 去设置界面
     */
    public static void goToSetting(Context context){
        //go to setting view
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", context.getPackageName(), null);
        intent.setData(uri);
        context.startActivity(intent);
    }
}
