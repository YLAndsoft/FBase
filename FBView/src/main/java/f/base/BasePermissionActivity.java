package f.base;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.view.View;

import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;
/**
 * @author: FYL
 * @time: 2018/9/3
 * @email:347933430@qq.com
 * @describe: base相关
 */
public abstract class BasePermissionActivity extends BaseActivity {


    private static final int PHOTO_PERMISS = 0x001;

    /**
     * 获取请求权限的集合
     * @return
     */
    public abstract String[] requestPermissions();

    /**
     * 权限请求成功
     * @return
     */
    public abstract void onPermissionsSuccess();
    
    @Override
    public void initData(Context mContext) {
        initPermission(requestPermissions());
    }

    private void initPermission(String[] permissions) {
        if(null==permissions||permissions.length<=0) return;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            PermissionGen.with(this)
                    .addRequestCode(PHOTO_PERMISS)
                    .permissions(permissions)
                    .request();
        }else{
            onPermissionsSuccess();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }
    @PermissionSuccess(requestCode = PHOTO_PERMISS)
    public void requestPerSuccess(){
        //成功之后的处理
        onPermissionsSuccess();
    }

    @PermissionFail(requestCode = PHOTO_PERMISS)
    public void requestPerFail(){
        //失败之后的处理，我一般是跳到设置界面
        goToSetting(this);
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
