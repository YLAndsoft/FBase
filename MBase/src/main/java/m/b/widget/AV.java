package m.b.widget;

import android.content.Context;
import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;

/**
 * @author: FYL
 * @time: 2018/9/4
 * @email:347933430@qq.com
 * @describe: m.b.widget
 */
public class AV {

    public interface OnSelectClickListener{
        void onSelectListener(int position);
    }

    /**
     * 选择拍照视图
     * @param mContext
     * @param listener
     */
    private static AlertView alertView ;
    public static void showPhoto(Context mContext, final OnSelectClickListener listener){
        if(alertView==null){
            alertView = new AlertView("选择操作", null, "取消", null,
                    new String[]{"拍照", "从相册中选择"},
                    mContext, AlertView.Style.ActionSheet, new OnItemClickListener(){
                public void onItemClick(Object o,int position){
                    listener.onSelectListener(position);
                }
            });

        }
        if(!alertView.isShowing())alertView.show();
    }

    /**
     * 显示结果视图
     * @param mContext
     * @param title
     * @param content
     */
    public static void showSucess(Context mContext,String title,String content){
        new AlertView(title, content, null, new String[]{"确定"}, null, mContext, AlertView.Style.Alert, new OnItemClickListener() {
            @Override
            public void onItemClick(Object o, int position) {
            }
        }).show();
    }

    /**
     * 选择按钮视图
     * @param mContext
     * @param title
     * @param str1
     * @param str2
     * @param listener
     */
    public static void showSelectView(Context mContext,String title,String [] str1,String [] str2,final OnSelectClickListener listener){
        new AlertView(title, null, "取消", str1, str2, mContext, AlertView.Style.ActionSheet, new OnItemClickListener() {
            @Override
            public void onItemClick(Object o, int position) {
                listener.onSelectListener(position);
            }
        }).show();
    }


}
