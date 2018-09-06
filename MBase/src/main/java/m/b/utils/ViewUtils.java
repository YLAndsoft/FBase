package m.b.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.xutils.common.util.LogUtil;

import m.b.R;

/**
 * @author: FYL
 * @time: 2018/9/3
 * @email:347933430@qq.com
 * @describe: 界面操作相关
 */
public class ViewUtils {
    /**
     * 获取垂直布局管理器
     * @param mContext
     * @return
     */
    public static LinearLayoutManager getLayoutManager(Context mContext){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        return linearLayoutManager;
    }

    /**
     * 获取水平布局管理器
     * @param mContext
     * @return
     */
    public static RecyclerView.LayoutManager getHorManager(Context mContext){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager (mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        return linearLayoutManager;
    }
    /**
     * 获取瀑布流布局管理器
     * @return
     */
    public static RecyclerView.LayoutManager getStaggeredGridManager(int num){
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(num, StaggeredGridLayoutManager.VERTICAL);
        //RecyclerView滑动过程中不断请求layout的Request，不断调整item见的间隙，并且是在item尺寸显示前预处理，因此解决RecyclerView滑动到顶部时仍会出现移动问题
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        //内容随高度变化
        layoutManager.setAutoMeasureEnabled(true);
        return layoutManager;
    }

    /**
     * 设置文本
     * @param view
     * @param result
     * @param
     */
    public static void setTextView(View view, String result){
        if(view instanceof EditText){
            if(result!=null&&!StringUtils.isBlank(result+"")){
                ((EditText) view).setHint(result);
            }else{
                ((EditText) view).setHint("");
            }
        }else if(view instanceof Button){
            if(result!=null&&!StringUtils.isBlank(result)){
                ((Button) view).setText(result+"");
            }else{
                ((Button) view).setText("");
            }
        }else if(view instanceof TextView){
            if(result!=null&&!StringUtils.isBlank(result)){
                ((TextView) view).setText(result+"");
            }else{
                ((TextView) view).setText("");
            }
        }
    }


    private static RequestOptions requestOptions;
    private static RequestOptions getRequestOptions(Context mContext){
        if(requestOptions==null){
            requestOptions = new RequestOptions()
                    .placeholder(mContext.getResources().getDrawable(R.drawable.default_error))
                    .error(mContext.getResources().getDrawable(R.drawable.default_error));
        }
        return requestOptions;
    }
    /**
     * 设置图片地址
     * @param context
     * @param view
     * @param result
     */
    public static void setImagePT(Context context,View view, String result){
        if(view instanceof ImageView){
            try{
                if(result!=null){
                    Glide.with(context)
                            .load(result+"")
                            .apply(getRequestOptions(context))
                            .thumbnail(0.1f)
                            .into((ImageView) view);
                }else{
                    ((ImageView) view).setImageDrawable(context.getResources().getDrawable(R.drawable.default_error));
                }
            }catch (Exception ex){
                LogUtil.e("设置图片异常！");
                ex.printStackTrace();
            }
        }
    }
    /**
     * 设置图片地址
     * @param context
     * @param view
     * @param result
     */
    public static void setImageT(Context context,View view, String result){
        if(view instanceof ImageView){
            try{
                if(result!=null){
                    Glide.with(context)
                            .load(result+"")
                            .apply(getRequestOptions(context))
                            .thumbnail(0.1f)
                            .into((ImageView) view);
                }else{
                    ((ImageView) view).setImageDrawable(context.getResources().getDrawable(R.drawable.default_error));
                }
            }catch (Exception ex){
                LogUtil.e("设置图片异常！");
                ex.printStackTrace();
            }
        }
    }

    /**
     * 设置图片
     * @param context
     * @param view
     * @param result
     */
    public static void setImageUrl(Context context,View view, String result){
        if(view instanceof ImageView){
            try{
                if(result!=null){
                    Glide.with(context).load(result+"")
                            .apply(getRequestOptions(context)).into((ImageView) view);
                }else{
                    ((ImageView) view).setImageDrawable(context.getResources().getDrawable(R.drawable.default_error));
                }
            }catch (Exception ex){
                LogUtil.e("设置图片异常！");
                ex.printStackTrace();
            }
        }
    }
    /**
     * 设置bitmap图片
     * @param context
     * @param view
     * @param result
     */
    public static void setImageBitmap(Context context,View view, Bitmap result){
        if(view instanceof ImageView){
            try{
                if(result!=null){
                    Glide.with(context).load(result)
                            .apply(getRequestOptions(context)).into((ImageView) view);
                }else{
                    ((ImageView) view).setImageDrawable(context.getResources().getDrawable(R.drawable.default_error));
                }
            }catch (Exception ex){
                LogUtil.e("设置图片异常！");
                ex.printStackTrace();
            }
        }
    }

}
